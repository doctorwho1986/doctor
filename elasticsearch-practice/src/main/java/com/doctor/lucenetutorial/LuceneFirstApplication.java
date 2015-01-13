package com.doctor.lucenetutorial;

import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
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
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
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

	public static void main(String[] args) throws Throwable {
		String dir = "lucene-tutorial/lucene-first-application";
		dir = LuceneFirstApplication.class.getClassLoader().getResource(dir).getFile();

		String indexDir = LuceneFirstApplication.class.getClassLoader().getResource("lucene-tutorial/tempIndexDir").getFile();

		LuceneIndexer luceneIndexer = new LuceneIndexer(indexDir);
		luceneIndexer.createIndex(dir, new TextFileFilter());

		LuceneSearcher luceneSearcher = new LuceneSearcher(indexDir);

		TopDocs topDocs = luceneSearcher.search("Meena");

		log.info("--------------serach--------------");
		printTopDocs(topDocs, luceneSearcher);

		// create a term to search file name
		// TermQuery is the most commonly used query object and is the foundation of many complex queries that lucene can make use of. TermQuery is normally used to retrieve documents based on the key which is case sensitive.
		log.info("create a term to search file name-----------");
		TopDocs topDocs2 = luceneSearcher.search(new TermQuery(new Term(LuceneConstants.file_name, "record10.txt")));
		printTopDocs(topDocs2, luceneSearcher);

		//TermRangeQuery
		//ermRangeQuery is the used when a range of textual terms are to be searched.
		//@see http://www.tutorialspoint.com/lucene/lucene_termrangequery.htm
		log.info("TermRangeQuery-----------");

		TermRangeQuery termRangeQuery = TermRangeQuery.newStringRange(LuceneConstants.file_name, "record3.txt", "record8.txt", true, true);
		TopDocs topDocs3 = luceneSearcher.search(termRangeQuery);
		printTopDocs(topDocs3, luceneSearcher);
		
		
		//PrefixQuery
		//PrefixQuery is used to match documents whose index starts with a specified string.
		//@see http://www.tutorialspoint.com/lucene/lucene_prefixquery.htm
		log.info("PrefixQuery-----------");

		TopDocs topDocs4 = luceneSearcher.search(new PrefixQuery(new Term(LuceneConstants.file_name,"record1")));
		printTopDocs(topDocs4, luceneSearcher);
		
		
		//BooleanQuery is used to search documents which are result of multiple queries using AND, OR or NOT operators.
		log.info("BooleanQuery-----------");

		BooleanQuery booleanQuery = new BooleanQuery();
		booleanQuery.add(new TermQuery(new Term(LuceneConstants.file_name, "record3.txt")), BooleanClause.Occur.MUST_NOT);
		booleanQuery.add(TermRangeQuery.newStringRange(LuceneConstants.file_name, "record3.txt", "record8.txt", true, true),BooleanClause.Occur.MUST);
		TopDocs topDocs5 = luceneSearcher.search(booleanQuery);
		printTopDocs(topDocs5, luceneSearcher);
		
		
		//Phrase query is used to search documents which contain a particular sequence of terms
		log.info("PhraseQuery-----------");

		PhraseQuery phraseQuery = new PhraseQuery();
		phraseQuery.setSlop(0);
		phraseQuery.add(new Term(LuceneConstants.file_name, "rec"));
		phraseQuery.add(new Term(LuceneConstants.file_name, "ord"));
		TopDocs topDocs6 = luceneSearcher.search(phraseQuery);
		printTopDocs(topDocs6, luceneSearcher);
		
		//FuzzyQuery is used to search documents using fuzzy implementation that is an approximate search based on edit distance algorithm.
		log.info("FuzzyQuery-----------");
		FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term(LuceneConstants.file_name, "cord3.txt"));
		TopDocs topDocs7 = luceneSearcher.search(fuzzyQuery);
		printTopDocs(topDocs7, luceneSearcher);
		
		
		//MatchAllDocsQuery as name suggests matches all the documents.
		log.info("MatchAllDocsQuery-----------");
		MatchAllDocsQuery matchAllDocsQuery = new MatchAllDocsQuery();
		TopDocs topDocs8 = luceneSearcher.search(matchAllDocsQuery);
		printTopDocs(topDocs8, luceneSearcher);
		
		//http://www.tutorialspoint.com/lucene/lucene_analysis.htm
		
		// 清理资源
		IOUtils.closeQuietly(luceneIndexer);
		IOUtils.closeQuietly(luceneSearcher);
	}

	private static void printTopDocs(TopDocs topDocs, LuceneSearcher luceneSearcher) throws IOException {
		for (ScoreDoc doc : topDocs.scoreDocs) {
			Document document = luceneSearcher.getDocument(doc);
			System.out.println(document.get(LuceneConstants.file_name) + "\t" + document.get(LuceneConstants.file_path));
		}
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

		private void indexFile(File file) throws IOException {
			log.info("indexFile:'{}'", file.getCanonicalPath());
			Document document = getDocument(file);
			indexWriter.addDocument(document);
		}

		public void createIndex(String dataDir, FileFilter fileFilter) throws IOException {
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

			indexWriter.commit();// 解决异常：Exception in thread "main" org.apache.lucene.index.IndexNotFoundException: no segments* file found in MMapDirector
		}

		@Override
		public void close() throws IOException {
			IOUtils.closeQuietly(indexWriter);
		}

	}

	private static class LuceneSearcher implements Closeable {
		private IndexSearcher indexSearcher;
		private QueryParser queryParser;
		private Directory directory;
		private IndexReader indexReader;
		private StandardAnalyzer analyzer;

		public LuceneSearcher(String indexDir) throws IOException {
			directory = FSDirectory.open(new File(indexDir));
			indexReader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(indexReader);
			analyzer = new StandardAnalyzer();
			queryParser = new QueryParser(LuceneConstants.contents, analyzer);
		}

		public TopDocs search(String sarch) throws ParseException, IOException {
			Query query = queryParser.parse(sarch);
			return indexSearcher.search(query, LuceneConstants.max_search);
		}

		public TopDocs search(Query query) throws IOException {
			return indexSearcher.search(query, LuceneConstants.max_search);
		}

		public Document getDocument(ScoreDoc scoreDoc) throws IOException {
			return indexSearcher.doc(scoreDoc.doc);
		}

		@Override
		public void close() throws IOException {
			IOUtils.closeQuietly(analyzer);
			IOUtils.closeQuietly(directory);
			IOUtils.closeQuietly(indexReader);

		}

	}
}
