/**
 * 
 */
package com.d3vlin13.realtorreportgenerator.main;

import com.d3vlin13.realtorreportgenerator.util.Printer;
import com.d3vlin13.realtorreportgenerator.view.Login;

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
			Printer.printTitle("Bienvenido, ha iniciado sesión como el " + Login.currentUser.getType().getValue() + ": " + Login.currentUser.getUsername());
		
		} else {
			Printer.printTitle("No ha logrado iniciar sesión en el sistema.");
		}
	}

}
