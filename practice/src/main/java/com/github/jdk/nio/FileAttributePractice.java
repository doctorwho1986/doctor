package com.github.jdk.nio;

import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.Set;

public class FileAttributePractice {

	public static void main(String[] args) {
		FileSystem fileSystem = FileSystems.getDefault();
		Set<String> supportedFileAttributeViews = fileSystem.supportedFileAttributeViews();
		System.out.println("supportedFileAttributeViews    --------------");
		for (String view : supportedFileAttributeViews) {
			System.out.println(view);
		}
		System.out.println("----------------------------\n\n");
		
		
		Iterable<FileStore> fileStores = fileSystem.getFileStores();
		System.out.println("getFileStores    --------------");
		for (FileStore fileStore : fileStores) {
			System.out.println(fileStore.name() + " is supported "  + fileStore.supportsFileAttributeView(BasicFileAttributeView.class));
		}
		System.out.println("----------- -- --------------\n\n");
		
		
		
	}

}
