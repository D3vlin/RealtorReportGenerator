package com.d3vlin13.realtorreportgenerator.model;

public final class Enums {	

	public enum EUserType {
		ADMIN(1, "Administrador"),
		EMPLOYED(2, "Empleado"),
		CLIENT(3, "Cliente");
		
		private int intValue;
		private String value;
		
		private EUserType(int intValue, String value) {
			this.intValue = intValue;
			this.value = value;
		}
		
		public int getIntValue() {
			return intValue;
		}
		
		public String getValue() {
			return value;
		}
	}
}
