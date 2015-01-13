package com.doctor.lucenetutorial;

import java.io.File;
import java.io.FileReader;
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
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://www.lucenetutorial.com/sample-apps/textfileindexer-java.html
 * @author doctor
 *
 * @time   2015年1月13日 下午1:40:13
 */
public class LuceneTextFileIndexer {
	private static final String docPath = "lucene-tutorial/lucene-text-fileIndexer";
	private static final Logger log = LoggerFactory.getLogger(LuceneTextFileIndexer.class);
	
	public static void main(String[] args) throws Throwable{
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer );
		indexWriterConfig.setOpenMode(OpenMode.CREATE);
		Directory indexDirectory = new RAMDirectory();
		IndexWriter indexWriter = new IndexWriter(indexDirectory , indexWriterConfig);
		
		File docDir = new File(LuceneTextFileIndexer.class.getClassLoader().getResource(docPath).getFile());
		
		if (!docDir.isDirectory()) {
			log.error("{error:'{} is not a directory'}",docDir);
			IOUtils.closeQuietly(indexWriter);
			IOUtils.closeQuietly(indexDirectory);
			IOUtils.closeQuietly(analyzer);
			return;
		}
		
		for (File file : docDir.listFiles()) {
			Document document = new Document();
			document.add(new StringField("fileName", file.getName(), Field.Store.YES));
			document.add(new StringField("filePath", file.getPath(), Field.Store.YES));
			
			FileReader fileReader = new FileReader(file);
			document.add(new TextField("content", fileReader));
			indexWriter.addDocument(document);
			IOUtils.closeQuietly(fileReader);
			log.info("{add:'{}'}",file.getName());
		}
		
		IOUtils.closeQuietly(indexWriter);
		
		IndexReader indexReader = DirectoryReader.open(indexDirectory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);
		
		TopScoreDocCollector results = TopScoreDocCollector.create(10, true);
		Query query = new QueryParser("content", analyzer).parse("see");
		indexSearcher.search(query , results);
		
		ScoreDoc[] scoreDocs = results.topDocs().scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			int id = scoreDoc.doc;
			log.info("{doc id:'{}'}",id);
			Document doc = indexSearcher.doc(id);
			System.out.println(doc.get("fileName")+"\t" + doc.get("filePath") + "\t" + doc.get("content"));
		}
		
		IOUtils.closeQuietly(indexReader);
		IOUtils.closeQuietly(indexDirectory);
		IOUtils.closeQuietly(analyzer);
		
	}

	 
}
