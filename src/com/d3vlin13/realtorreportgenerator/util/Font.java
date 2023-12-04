/**
 * 
 */
package com.d3vlin13.realtorreportgenerator.util;

/**
 * 
 */
final class Font {
	public enum EColor {
		RESET("\u001B[0m"),
		BLACK("\u001B[30m"),
		RED("\u001B[31m"),
		GREEN("\u001B[32m"),
		YELLOW("\u001B[33m"),
		BLUE("\u001B[34m"),
		MAGENTA("\u001B[35m"),
		CYAN("\u001B[36m"),
		WHITE("\u001B[37m"),
		BKWHITE("\u001B[47m"),
		GREY("\u001B[90m");
		
		private String value;
		
		private EColor(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
}
