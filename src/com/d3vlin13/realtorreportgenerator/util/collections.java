package com.d3vlin13.realtorreportgenerator.util;

import java.util.List;

public final class collections {
	
	public static String[][] mapListToMatrix(List<String> list) {
        int numRows = list.size();
        int numCols = list.get(0).split(",").length;

        String[][] data = new String[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            String[] row = list.get(i).split(",");
            data[i] = row;
        }

        return data;
    }
}
