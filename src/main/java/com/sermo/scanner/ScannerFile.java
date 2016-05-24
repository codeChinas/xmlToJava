package com.sermo.scanner;

import java.io.File;
import java.io.FileFilter;

public class ScannerFile {
	public static File[] getFileName(String path){
		
		File directory  = new File(path);
		
		File[] files = directory.listFiles(new FileFilter() {
			public boolean accept(File file) {
				if (file.getName().endsWith(".xml")) {
					return true;
				}
				return false;
			}
		});
		return files;
	}
}
