package com.d3vlin13.realtorreportgenerator.view;

import java.util.List;

import com.d3vlin13.realtorreportgenerator.util.FileExplorer;
import com.d3vlin13.realtorreportgenerator.util.Printer;
import com.d3vlin13.realtorreportgenerator.util.collections;

public final class MenuAdmin {
	
	public static void showLogin() {
		showMenu();
	}
	
	private static void showMenu() {
		displayWelcomeMessage();
		
		int option = 0;
		
		do {
			displayMainOptions();
			option = Printer.readIntAnswer();			
			handleMainOption(option);
			
			
		} while (option == 0);
		
		if (option != 5) {
			do {
				displaySecondaryOptions();
				option = Printer.readIntAnswer();
				handleSecondaryOption(option);
			} while (option == 0);
		}
	}
	
	private static void displayWelcomeMessage() {
	    Printer.printTitle("Bienvenido, ha iniciado sesión como el " + Login.currentUser.getType().getValue() + ": " + Login.currentUser.getUsername());
	}
	
	private static void displayMainOptions() {
	    Printer.writeFirstOption("1. Ver Usuarios");
	    Printer.writeOption("2. Crear Usuario");
	    Printer.writeOption("3. Actualizar Usuario");
	    Printer.writeOption("4. Eliminar Usuario");
	    Printer.writeOption("5. Cerrar sesión");
	    Printer.writeAnswer("Seleccione una opción del menú");
	}
	
	private static void handleMainOption(int option) {
	    switch (option) {
	        case 1:
	            showUsers();
	            break;
	        case 2:
	            // Lógica para crear usuario
	            break;
	        case 3:
	            // Lógica para actualizar usuario
	            break;
	        case 4:
	            // Lógica para eliminar usuario
	            break;
	        case 5:
	            // Lógica para cerrar sesión
	            break;
	        default:
	            Printer.printError("Debe elegir una opción válida");
	    }
	}
	
	private static void displaySecondaryOptions() {
	    Printer.printTitle("Desea volver al menú principal");
	    Printer.writeFirstOption("1. Volver al menú");
	    Printer.writeOption("2. Cerrar sesión");
	    Printer.writeAnswer("Seleccione una opción del menú");
	}
	
	private static void handleSecondaryOption(int option) {
	    switch (option) {
	        case 1:
	            showMenu();
	            break;
	        case 2:
	            // Lógica para cerrar sesión
	            break;
	        default:
	            Printer.printError("Debe elegir una opción válida");
	    }
	}
	
	private static void showUsers() {
		Printer.printTitle("Lista de usuarios del sistema");
		String[] header = {"Tipo", "Usuario", "Contraseña"};
		
		List<String> userList = FileExplorer.getEncryptFileLines(FileExplorer.FILE_PATH_USERS);		
        String[][] data = collections.mapListToMatrix(userList);
        
        Printer.printTable(header, data);
	}
}
