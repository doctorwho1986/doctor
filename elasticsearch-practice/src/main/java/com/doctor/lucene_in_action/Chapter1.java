package com.doctor.lucene_in_action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Chapter1 {

	public static void main(String[] args) throws Throwable {
		String indexDir = "/elasticsearchPractice/lucene-in-action/chapter1/index-dir";// 索引目录存放
		String dataDir = "/elasticsearchPractice/lucene-in-action/chapter1/data-dir";// 需要处理的文件目录

		Instant start = Instant.now();

		System.out.println(Chapter1.class.getResource("/"));
		FSDirectory fsDirectory = FSDirectory.open(new File(Chapter1.class.getResource(indexDir).getFile()));

		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);

		IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

		indexDoc(indexWriter, new File(Chapter1.class.getResource(dataDir).getFile()));

	}

	static void indexDoc(IndexWriter indexWriter, File dataFile) throws Throwable {

		if (!dataFile.canRead()) {
			return;
		}
		
		if (dataFile.isDirectory()) {
			for (File file : dataFile.listFiles()) {
				indexDoc(indexWriter, file);
			}
		}else {
			
			try (FileInputStream fileInputStream = new FileInputStream(dataFile)){
				Document document = new Document();
				
				StringField stringField = new StringField("path", dataFile.getPath(), Field.Store.YES);
				document.add(stringField);
				document.add(new LongField("modified", dataFile.lastModified(), Field.Store.NO));
				document.add(new TextField("contents", new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8))));
				System.out.println("updating" + dataFile);
				indexWriter.updateDocument(new Term("path",dataFile.getPath()), document);
			} catch (Exception e) {
				return;
			}
		}
	}

}
