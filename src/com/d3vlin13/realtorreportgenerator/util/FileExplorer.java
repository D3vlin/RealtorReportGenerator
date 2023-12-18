package com.d3vlin13.realtorreportgenerator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

public final class FileExplorer {
	public static final String FILE_PATH_USERS = "data/users.txt";

    private interface FileProcessor {
        void process(String line);
    }
	
	public static boolean existsFile(String filePath){
		File file = new File(filePath);
		return file.exists();
	}
	
	public static void readFile(String filePath) {
		readAndPrintFile(filePath, false);
	}
	
	public static void readEncryptFile(String filePath) {
		readAndPrintFile(filePath, true);
	}
	
	private static void readAndPrintFile(String filePath, boolean decrypt) {
		processFile(filePath, decrypt, Printer::printInformation);
    }
	
	public static boolean searchInFile(String filePath, String searchTerm) {
		return searchInFile(filePath, searchTerm, false);
    }
	
	public static boolean searchInEncryptFile(String filePath, String searchTerm) {
		return searchInFile(filePath, searchTerm, true);
    }

    private static boolean searchInFile(String filePath, String searchTerm, boolean decrypt) {
        final boolean[] found = {false};
        processFile(filePath, decrypt, line -> {
            if (line.equals(searchTerm)) {
                found[0] = true;
            }
        });
        return found[0];
    }
	
	private static List<String> getFileLines(String filePath, boolean decrypt) {
		List<String> lines = new ArrayList<>();
        processFile(filePath, decrypt, lines::add);
        return lines;
    }
	
	public static List<String> getFileLines(String filePath) {
        return getFileLines(filePath, false);
    }
	
	public static List<String> getEncryptFileLines(String filePath) {
        return getFileLines(filePath, true);
    }
	
	private static void processFile(String filePath, boolean decrypt, FileProcessor processor) {
		if (existsFile(filePath)) {
	        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
	            String line;

	            while ((line = br.readLine()) != null) {
	                if (decrypt) {
	                    line = Crypto.decrypt(line);
	                }

	                processor.process(line);
	            }
	        
	        } catch (Exception e) {
	            Printer.printError("Error al procesar el archivo: " + e.getMessage());
	        }
		
		} else {
            Printer.printError("No existe el archivo: " + filePath);
		}
    }
}
