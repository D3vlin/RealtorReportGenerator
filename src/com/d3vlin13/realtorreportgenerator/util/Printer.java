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

	public static int readIntAnswer() {
		try {
			return Integer.parseInt(readMessage());
		} catch (NumberFormatException | IOException e) {
			return 0;
		}
	}

	public static void printTitle(String prompt) {
		setForeGroundColor(EColor.YELLOW);
		drawTopLine(prompt.length());
		out.println("║" + prompt + "║");
		drawUnderLine(prompt.length());
		out.println("");
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
		out.print("╔═" + prompt + "\n");
	}
	
	public static void writeOption(String prompt) {
		setForeGroundColor(EColor.GREEN);
		out.print("╠═" + prompt + "\n");
	}

	public static void printInformation(String prompt) {
		setForeGroundColor(EColor.CYAN);
		out.println("*" + "*".repeat(prompt.length()) + "*");
		out.println("*" + prompt + "*");
		out.println("*" + "*".repeat(prompt.length()) + "*");
		out.println("");
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
		out.print(color.getValue());
	}
	
	public static void printTable(String[] header, String[][] data) {
		setForeGroundColor(EColor.BKWHITE);
        int[] columnWidths = calculateColumnWidths(header, data);

        printTableTopLine(columnWidths);
        printHeaderRow(header, columnWidths);

        for (String[] row : data) {
            printTableDividerLine(columnWidths);
            printDataRow(row, columnWidths);
        }
        
        printTableUnderLine(columnWidths);
		setForeGroundColor(EColor.RESET);
    }
	
	private static int[] calculateColumnWidths(String[] header, String[][] data) {
        int numColumns = header.length;
        int[] columnWidths = new int[numColumns];

        // Calcular la longitud máxima en cada columna
        for (int i = 0; i < numColumns; i++) {
            columnWidths[i] = header[i].length();

            for (String[] row : data) {
                int cellWidth = row[i].length();
                if (cellWidth > columnWidths[i]) {
                    columnWidths[i] = cellWidth;
                }
            }
        }

        return columnWidths;
    }
	
	private static void printTableTopLine(int[] columnWidths) {
		printTableLine(columnWidths, "╔", "╦", "╗");
    }
	
	private static void printTableDividerLine(int[] columnWidths) {
		printTableLine(columnWidths, "╠", "╬", "╣");
    }
	
	private static void printTableUnderLine(int[] columnWidths) {
		printTableLine(columnWidths, "╚", "╩", "╝");
    }
	
	private static void printTableLine(int[] columnWidths, String cInitial, String cIntermediate, String cFinal) {
		setForeGroundColor(EColor.YELLOW);
        System.out.print(cInitial);

        int numColumns = columnWidths.length;
        for (int i = 0; i < numColumns; i++) {
            int width = columnWidths[i];
            System.out.print("═".repeat(width + 2) + ((i == numColumns - 1) ? cFinal : cIntermediate));
        }

        System.out.println();
    }
	
	private static void printHeaderRow(String[] row, int[] columnWidths) {
		printRow(row, columnWidths, EColor.BLACK);
    }
	
	private static void printDataRow(String[] row, int[] columnWidths) {
		printRow(row, columnWidths, EColor.GREY);
    }
	
	private static void printRow(String[] row, int[] columnWidths, EColor cellColor) {
		setForeGroundColor(EColor.YELLOW);
        System.out.print("║");

        int numColumns = row.length;
        for (int i = 0; i < numColumns; i++) {
            String cellValue = row[i];
            int padding = columnWidths[i] - cellValue.length();            

            cellValue = cellColor.getValue() + cellValue + EColor.YELLOW.getValue();
            System.out.print(" " + cellValue + " ".repeat(padding) + " ║");
        }

        System.out.println();
    }
}
