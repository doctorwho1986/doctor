package com.doctor.lucenetutorial;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @see http://www.tutorialspoint.com/lucene/lucene_first_application.htm
 * @author doctor
 *
 * @time 2015年1月13日 下午2:53:16
 */
public class LuceneFirstApplication {
	private static final Logger log = LoggerFactory.getLogger(LuceneFirstApplication.class);
	public static void main(String[] args) throws Throwable{
		String dir = "lucene-tutorial/lucene-first-application";
		dir = LuceneFirstApplication.class.getClassLoader().getResource(dir).getFile();
		
		String indexDir = LuceneFirstApplication.class.getClassLoader().getResource("lucene-tutorial/tempIndexDir").getFile();
		
		LuceneIndexer luceneIndexer = new LuceneIndexer(indexDir);
		luceneIndexer.createIndex(dir, new TextFileFilter());
		
		LuceneSearcher luceneSearcher = new LuceneSearcher(indexDir);
		
		TopDocs topDocs = luceneSearcher.search("Meena");
		
		log.info("--------------serach--------------");
		for (ScoreDoc doc : topDocs.scoreDocs) {
			Document document = luceneSearcher.getDocument(doc);
			System.out.println(document.get(LuceneConstants.file_name) + "\t" + document.get(LuceneConstants.file_path + "\t" + document.get(LuceneConstants.contents)));
		}
		
		luceneIndexer.close();
	}

	private static interface LuceneConstants {
		String contents = "contents";
		String file_name = "fileName";
		String file_path = "filePath";
		int max_search = 10;
	}

	private static class TextFileFilter implements FileFilter {

		@Override
		public boolean accept(File pathname) {
			return pathname.getName().toLowerCase().endsWith(".txt");
		}

	}

	private static class LuceneIndexer implements Closeable {
		private static final Logger log = LoggerFactory.getLogger(LuceneIndexer.class);
		private IndexWriter indexWriter;

		public LuceneIndexer(String indexDir) {
			init(indexDir);
		}

		private void init(String indexDir) {
			try {
				Directory indexDirectory;
				indexDirectory = FSDirectory.open(new File(indexDir));
				Analyzer analyzer = new StandardAnalyzer();
				IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
				conf.setOpenMode(OpenMode.CREATE);
				indexWriter = new IndexWriter(indexDirectory, conf);
			} catch (IOException e) {
				String error = String.format("{open error:'can't find dir %s '}", indexDir);
				log.error(error, e);
			} finally {
			}

		}

		private Document getDocument(File file) throws IOException {
			if (file.isDirectory()) {
				String error = String.format("%s isDirectory ", file);
				throw new IllegalStateException(error);
			}

			if (!file.canRead()) {
				String error = String.format("%s can't read ", file);
				throw new IllegalStateException(error);
			}

			Document document = new Document();
			Reader reader = new FileReader(file);
			document.add(new TextField(LuceneConstants.contents, reader));
			document.add(new StringField(LuceneConstants.file_name, file.getName(), Field.Store.YES));
			document.add(new StringField(LuceneConstants.file_path, file.getCanonicalPath(), Field.Store.YES));
			return document;
		}

		private void indexFile(File file) throws IOException{
			log.info("indexFile:'{}'",file.getCanonicalPath());
			Document document = getDocument(file);
			indexWriter.addDocument(document);
		}
		
		public void createIndex(String dataDir,FileFilter fileFilter) throws IOException {
			File file = new File(dataDir);
			if (!file.isDirectory()) {
				throw new IllegalArgumentException(dataDir + " is not directory");
			}
			File[] files = file.listFiles(fileFilter);
			for (File f : files) {
				if (!f.isDirectory() && f.canRead() && f.exists() && !f.isHidden()) {
					indexFile(f);
				}
			}
			
			indexWriter.commit();//解决异常：Exception in thread "main" org.apache.lucene.index.IndexNotFoundException: no segments* file found in MMapDirector
		}
		@Override
		public void close() throws IOException {
			IOUtils.closeQuietly(indexWriter);
		}

	}
	
	private static class LuceneSearcher{
		private IndexSearcher indexSearcher;
		private QueryParser queryParser;
		public LuceneSearcher(String indexDir) throws IOException{
			Directory directory = FSDirectory.open(new File(indexDir));
			IndexReader indexReader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(indexReader );
			queryParser = new QueryParser(LuceneConstants.contents, new StandardAnalyzer());
		}
		
		public TopDocs search(String sarch) throws ParseException, IOException {
			Query query = queryParser.parse(sarch);
			return indexSearcher.search(query, LuceneConstants.max_search);
		}
		
		public Document getDocument(ScoreDoc scoreDoc) throws IOException{
			return indexSearcher.doc(scoreDoc.doc);
		}
	}
}
