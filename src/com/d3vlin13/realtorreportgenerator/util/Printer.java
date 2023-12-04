/**
 * 
 */
package com.d3vlin13.realtorreportgenerator.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.d3vlin13.realtorreportgenerator.util.Font.EColor;

import static java.lang.System.out;

/**
 * 
 */
public final class Printer {

	public static String readMessage() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}

	public static int readInt() throws NumberFormatException, IOException {
		return Integer.parseInt(readMessage());
	}

	public static void printTitle(String prompt) {
		setForeGroundColor(EColor.YELLOW);
		drawTopLine(prompt.length());
		out.println("║" + prompt + "║");
		drawUnderLine(prompt.length());
	}
	
	public static void writeAnswer(String prompt) {
		setForeGroundColor(EColor.WHITE);
		out.println("║\n╚═ " + prompt + " ↓");
	}
	
	public static void writeRequest(String prompt) {
		setForeGroundColor(EColor.WHITE);
		out.println("╚═ " + prompt + " ↓");
	}
	
	public static void writeFirstOption(String prompt) {
		setForeGroundColor(EColor.GREEN);
		out.print("╔═" + prompt);
	}
	
	public static void writeOption(String prompt) {
		setForeGroundColor(EColor.GREEN);
		out.print("╠═" + prompt);
	}

	public static void printInformation(String prompt) {
		setForeGroundColor(EColor.CYAN);
		out.println("*" + "*".repeat(prompt.length()) + "*");
		out.println("*" + prompt + "*");
		out.println("*" + "*".repeat(prompt.length()) + "*");
	}

	public static void printError(String prompt) {
		setForeGroundColor(EColor.RED);
		out.println("*" + prompt + "*");
	}
	
	private static void drawTopLine(int lenght) {
		out.println("╔" + "═".repeat(lenght) + "╗");
	}
	
	private static void drawUnderLine(int lenght) {
		out.println("╚" + "═".repeat(lenght) + "╝");
	}
	
	private static void setForeGroundColor(EColor color) {
		out.println(color.getValue());
	}
}
