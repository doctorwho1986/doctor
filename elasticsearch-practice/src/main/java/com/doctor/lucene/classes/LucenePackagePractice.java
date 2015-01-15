package com.doctor.lucene.classes;

import org.apache.lucene.LucenePackage;
import org.apache.lucene.index.memory.ExtendedMemoryIndex;

public class LucenePackagePractice {

	public static void main(String[] args) {
		Package p = LucenePackage.get();
		System.out.println(p);
		//package org.apache.lucene, Lucene Search Engine: core, version 4.10.3
		
		
		System.out.println(LucenePackagePractice.class.getPackage());
		//package com.doctor.lucene.classes
		
		System.out.println(ExtendedMemoryIndex.class.getPackage());
	}

}
