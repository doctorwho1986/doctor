package com.doctor.lucene_搜索引擎开发宝典;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

public class Chapter1Code {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param filePath 被索引的文件路径
	 * @param indexPath 索引存放的路径
	 */
	static void index(String filePath,String indexPath) throws Throwable{
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_10_3, new StandardAnalyzer());
		Directory d;
//		IndexWriter indexWriter = new IndexWriter(d, conf);
	}
}
