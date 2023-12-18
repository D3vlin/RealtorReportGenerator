/**
 * 
 */
package com.d3vlin13.realtorreportgenerator.main;

import com.d3vlin13.realtorreportgenerator.util.Printer;
import com.d3vlin13.realtorreportgenerator.view.Login;
import com.d3vlin13.realtorreportgenerator.view.MenuAdmin;

/**
 * 
 */
public final class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		if (Login.showLogin()) {
			switch (Login.currentUser.getType().getIntValue()) {
				case 1: {
						MenuAdmin.showLogin();
					break;
				}
				
				default:
					Printer.printError("Menu de usuario no implementado aún");
			}

			Printer.printInformation("Cerrando sistema...");
		}
	}

}
