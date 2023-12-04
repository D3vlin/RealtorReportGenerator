package com.d3vlin13.realtorreportgenerator.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.crypto.SecretKey;

public final class FileExplorer {
	public static boolean existsFile(String filePath){
		File file = new File(filePath);
		return file.exists();
	}
	
	public static void readFile(String filePath) {
		try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr);) {				
			String line = null;
			while ((line = br.readLine()) != null) {
	            Printer.printInformation(line);
	        }
			
		} catch (Exception e) {
			Printer.printError("Error al leer el archivo: " + e.getMessage());
		}
	}
	
	public static void searchInFile(String filePath, String searchTerm) {
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.contains(searchTerm)) {
                    Printer.printInformation("Encontrado en línea " + lineNumber + ": " + line);
                }
            }
        } catch (Exception e) {
			Printer.printError("Error al buscar en el archivo: " + e.getMessage());
		}
    }
	
	public static boolean searchInEncryptFile(String filePath, String searchTerm) {
        try (FileReader fr = new FileReader(filePath); BufferedReader br = new BufferedReader(fr)) {
            String line;

			SecretKey secretKey = Crypto.generateSecretKey();

            while ((line = br.readLine()) != null) {
                line = Crypto.decrypt(line, secretKey);

                if (line.contains(searchTerm)) {
                	return true;
                	//Printer.printInformation("Encontrado en línea " + lineNumber + ": " + line);
                }
            }
        } catch (Exception e) {
			Printer.printError("Error al buscar en el archivo: " + e.getMessage());
		}
        
        return false;
    }
}
