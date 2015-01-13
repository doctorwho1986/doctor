package com.doctor.lucenetutorial;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


/**
 * @see http://www.lucenetutorial.com/lucene-in-5-minutes.html
 * @author doctor
 *
 * 运行完程序，资源目录会显示些生成的索引文件。eclipse有可能看不到这些文件。
 * @time 2015年1月13日 上午11:20:46
 */
public class LuceneIn5Minutes {
	private static final String indexPath = "elasticsearchPractice/lucenetutorial/luceneIn5Minutes";
	

	public static void main(String[] args) throws Throwable{
		System.out.println(getFilePathFromClassPath(indexPath));
		String file = LuceneIn5Minutes.class.getClassLoader().getResource(indexPath).getFile();
		
		// 0. Specify the analyzer for tokenizing text.
		// The same analyzer should be used for indexing and searching
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3,analyzer );
		conf.setOpenMode(OpenMode.CREATE);
		
		Directory indexDirectory = FSDirectory.open(new File(file));
		IndexWriter indexWriter = new IndexWriter(indexDirectory, conf);
		
		addDocument(indexWriter, "Lucene in Action", "193398817");
		addDocument(indexWriter, "Lucene for Dummies", "55320055Z");
		addDocument(indexWriter, "Managing Gigabytes", "55063554A");
		addDocument(indexWriter, "The Art of Computer Science", "9900333X");
		indexWriter.close();
		
		// 2. query
		String queryString = "Lucene";
		Query query = new QueryParser("title", analyzer).parse(queryString);
		
		// 3. search
		int hitsPerPage = 10;
		DirectoryReader directoryReader = DirectoryReader.open(indexDirectory);
		IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
		TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
		
		indexSearcher.search(query, collector);
		
		ScoreDoc[] scoreDocs = collector.topDocs().scoreDocs;
		// 4. display results
		System.out.println("Found " + scoreDocs.length + " hits.");
		for (ScoreDoc scoreDoc : scoreDocs) {
			int id = scoreDoc.doc;
			Document doc = indexSearcher.doc(id);
			System.out.println(doc.get("title") + "\t" + doc.get("isbn"));
		}
		directoryReader.close();
		indexWriter.close();
	}

	static void addDocument(IndexWriter indexWriter, String title, String isbn) throws Throwable {
		Document document = new Document();
		document.add(new TextField("title", title, Field.Store.YES));
		document.add(new StringField("isbn", isbn, Field.Store.YES));
		indexWriter.addDocument(document);
	}

	static String getFilePathFromClassPath(String file){
		return LuceneIn5Minutes.class.getClassLoader().getResource(file).getFile();
	}
}
