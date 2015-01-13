package com.doctor.lucene_搜索引擎开发宝典;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Chapter1Code {
	private static final String indexDir = "elasticsearchPractice/lucene_1/chapter1/index-dir";
	private static final String docDir = "elasticsearchPractice/lucene_1/chapter1/data-dir";
	
	public static void main(String[] args) throws Throwable {
		index(docDir, indexDir);
	}

	/**
	 * 
	 * @param filePath 被索引的文件路径
	 * @param indexPath 索引存放的路径
	 */
	static void index(String filePath,String indexPath) throws Throwable{
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
		conf.setOpenMode(OpenMode.CREATE);
		
		Directory directory = FSDirectory.open(new File(getFilePathFromClassPath(indexPath)));
		
		IndexWriter indexWriter = new IndexWriter(directory, conf);
		File docFile = new File(getFilePathFromClassPath(filePath));
		if (!docFile.isDirectory()) {
			indexWriter.close();
			return;
		}
		
		for (File file : docFile.listFiles()) {
			Document document = new Document();
			
			//fileName字段
			String fileName = file.getName();
			StringField stringField = new StringField("fileName", fileName, Field.Store.YES);
			document.add(stringField);
			
			//url字段
			String path = file.getPath();
			StringField stringField2 = new StringField("filePath", path, Field.Store.YES);
			document.add(stringField2);
			indexWriter.addDocument(document);
			
		}
		
		indexWriter.close();
		
	}
	
	static String getFilePathFromClassPath(String file){
		return Chapter1Code.class.getClassLoader().getResource(file).getFile();
	}
}
