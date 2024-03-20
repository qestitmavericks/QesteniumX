package com.qestit.data;

import org.apache.poi.ss.usermodel.Row;

public interface IExcelDataManager {

	// Excel-related methods
	void setExcelFile(String excelPath, String sheetName);

	String getCellData(int rowNum, int colNum);

	String getCellData(int rowNum, String columnName);

	void setCellData(String text, int rowNumber, int colNumber);

	void setCellData(String text, int rowNumber, String columnName);

	int getRows();

	int getColumns();

	Row getRowData(int rowNum);

	Object[][] getExcelData(String excelPath, String sheetName);

	Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow);

}
