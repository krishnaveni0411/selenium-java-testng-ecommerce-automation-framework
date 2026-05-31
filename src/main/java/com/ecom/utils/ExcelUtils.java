package com.ecom.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {

    private static final DataFormatter formatter = new DataFormatter();

    private ExcelUtils() {}

    public static Object[][] getExcelData(String excelPath) throws IOException {
        try (FileInputStream file = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
             
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            XSSFRow row = sheet.getRow(0);
            int columnCount = row.getLastCellNum();

            Object[][] data = new Object[rowCount - 1][columnCount];

            for (int i = 0; i < rowCount - 1; i++) {
                XSSFRow currentRow = sheet.getRow(i + 1);
                for (int j = 0; j < columnCount; j++) {
                    XSSFCell cell = currentRow.getCell(j);
                    data[i][j] = formatter.formatCellValue(cell);
                }
            }
            return data;
        }
    }
}
