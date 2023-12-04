package com.d3vlin13.realtorreportgenerator.view;

import java.io.IOException;
import java.util.Iterator;

import javax.crypto.SecretKey;

import com.d3vlin13.realtorreportgenerator.model.Enums.EUserType;
import com.d3vlin13.realtorreportgenerator.model.User;
import com.d3vlin13.realtorreportgenerator.util.Crypto;
import com.d3vlin13.realtorreportgenerator.util.FileExplorer;
import com.d3vlin13.realtorreportgenerator.util.Printer;

public final class Login {

	public static EUserType userType = null;
	public static User currentUser = null;
	
	public static boolean showLogin() throws InterruptedException {
		Printer.printInformation("Iniciando sistema...");
		Thread.sleep(2000);
		Printer.printTitle("Bienvenido al RealtorReportGenerator");
		selectUserType();
		
		return (userType != null) ? userAuth() : false;
	}
	
	public static void selectUserType() {
		int option = 0;
		do {
			EUserType[] userOptions = EUserType.values();
			int exitOption = userOptions.length + 1;
			
			for (EUserType userOption : userOptions) {
				if (userOption.getIntValue() == 1) {
					Printer.writeFirstOption(userOption.getIntValue() + ". " + userOption.getValue());
				} else {
					Printer.writeOption(userOption.getIntValue() + ". " + userOption.getValue());
				}
			}

			Printer.writeOption(exitOption + ". Salir");
			Printer.writeAnswer("Seleccione su tipo de usuario");
			
			try {
				option = Printer.readInt();
			} catch (Exception e) {
				option = 0;
			}
			
			if(option != 0 && option != exitOption) {
				userType = userOptions[option - 1];
			
			} else if (option == exitOption) {
				Printer.printInformation("Cerrando sistema...");
			
			} else {
				Printer.printError("Debe elegir una opción valida");
				option = 0;
			}
			
		} while ((option == 0) && (userType == null));
	}
	
	public static boolean userAuth() {
		Printer.printTitle("Bienvenido " + userType.getValue());
		
		currentUser = new User();
		currentUser.setType(userType);
		
		try {
			Printer.writeRequest("Ingrese su usuario");
			currentUser.setUsername(Printer.readMessage());

			Printer.writeRequest("Ingrese su contraseña");
			currentUser.setPassword(Printer.readMessage());
			
		} catch (IOException e) {
			currentUser.setUsername("");
			currentUser.setPassword("");
		}
		
		if ("".equals(currentUser.getUsername()) || "".equals(currentUser.getPassword())) {
			Printer.printError("Debe ingresar usuario y contraseña");
			
		} else {
			if(FileExplorer.existsFile("data/users.txt")) {
				if(FileExplorer.searchInEncryptFile("data/users.txt", userType.getIntValue() + "," + currentUser.getUsername() + "," + currentUser.getPassword())) {
					Printer.printInformation("Iniciando sesion...");
					return true;
				
				} else {
					Printer.printError("Usuario o contraseña incorrectos");
				}
				
			} else {
				Printer.printError("Error al conectar a la base de datos de usuarios");
			}
		}
		
		return false;
	}
}
 