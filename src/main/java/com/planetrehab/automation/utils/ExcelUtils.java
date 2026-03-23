package com.planetrehab.automation.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;

import com.planetrehab.automation.constants.FrameworkConstants;

public class ExcelUtils {

    // ===================== CORE METHOD =====================

    public static Object[][] getSheetData(String filePath, String sheetName) {

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("❌ Sheet not found: " + sheetName);
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            List<Object[]> filteredData = new ArrayList<>();
            for (int i = 1; i < rowCount; i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                // ✅ FIX: Run column is LAST column
                Cell runCell = row.getCell(colCount - 1);

                if (runCell != null && getCellValue(runCell).equalsIgnoreCase("Y")) {

                    Object[] rowData = new Object[colCount - 1];

                    // ✅ Read all columns EXCEPT last (run column)
                    for (int j = 0; j < colCount - 1; j++) {

                        Cell cell = row.getCell(j);
                        rowData[j] = getCellValue(cell);
                    }

                    filteredData.add(rowData);
                }
            }
            return filteredData.toArray(new Object[0][]);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to read Excel file");
        }
    }

    // ===================== HELPER METHOD =====================

    private static String getCellValue(Cell cell) {

        if (cell == null) return "";

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {

                    // 🔥 FIX: Format date properly
                    java.text.SimpleDateFormat sdf =
                            new java.text.SimpleDateFormat("MM/dd/yyyy");

                    return sdf.format(cell.getDateCellValue()); // ✅ FIXED
                }
                return String.valueOf((long) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            default:
                return "";
        }
    }
    
    // ===================== SHORTCUT METHOD =====================

    public static Object[][] getSheetData(String sheetName) {
        return getSheetData(FrameworkConstants.TESTDATA_FILE_PATH, sheetName);
    }
}