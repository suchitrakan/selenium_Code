package Utility;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtily {
    private Workbook workbook;
    private Sheet sheet;
    int usernameColumn;
    int testCaseIdColumn;

    // Constructor: load workbook and sheet
    public ExcelUtily(String excelPath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(excelPath)) {
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file: " + excelPath, e);
        }
    }

    // Get total rows
    public int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Get total columns
    public int getColCount() {
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }

    // Get cell data by row/col index
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    // Return all sheet data as 2D Object array (for DataProvider)
    public Object[][] getSheetData() {
        int rowCount = getRowCount();
        int colCount = getColCount();
        Object[][] data = new Object[rowCount - 1][colCount]; // skip header row

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(i, j);
            }
        }
        return data;
    }
    
    public Object[][] getRowData(String testName) {
        List<Object[]> dataList = new ArrayList<>();
        int rowCount = getRowCount();
        int colCount = getColCount();

            for (Row row : sheet) {
                Cell cell = row.getCell(0); // assume first column has testName
                if (cell.getStringCellValue().equalsIgnoreCase(testName)) {
                    Object[] rowData = new Object[colCount];
                    for (int i = 0; i < colCount; i++) {
                        Cell c = row.getCell(i);
                        if (c != null) {
                            rowData[i] = c.toString(); // convert to string
                        } else {
                            rowData[i] = ""; // handle blank cells
                        }
                    }
                    dataList.add(rowData);
                }
            }
        return dataList.toArray(new Object[0][]);
    }

    
    public String getUsername(String testCaseId) {
        int rowCount = getRowCount();
        int colCount = getColCount();

        int usernameColumn = -1;
        int testCaseIdColumn = -1;

        // Step 1: Find column indices from header row
        for (int j = 0; j < colCount; j++) {
            String header = getCellData(0, j);
            if (header.equalsIgnoreCase("username")) {
                usernameColumn = j;
            } else if (header.equalsIgnoreCase("TestCaseId")) {
                testCaseIdColumn = j;
            }
        }

        if (usernameColumn == -1 || testCaseIdColumn == -1) {
            throw new RuntimeException("Required columns not found in sheet");
        }

        // Step 2: Loop through rows to find matching TestCaseId
        for (int i = 1; i < rowCount; i++) {
            String currentTestCaseId = getCellData(i, testCaseIdColumn);
            if (currentTestCaseId.equalsIgnoreCase(testCaseId)) {
                return getCellData(i, usernameColumn);
            }
        }

        // Step 3: If not found
        throw new RuntimeException("TestCaseId '" + testCaseId + "' not found in sheet");
    }
    
    public String getPassword(String testCaseId) {
        int rowCount = getRowCount();
        int colCount = getColCount();

        int passwordColumn = -1;
        int testCaseIdColumn = -1;

        // Step 1: Find column indices from header row
        for (int j = 0; j < colCount; j++) {
            String header = getCellData(0, j);
            if (header.equalsIgnoreCase("password")) {
            	passwordColumn = j;
            } else if (header.equalsIgnoreCase("TestCaseId")) {
                testCaseIdColumn = j;
            }
        }

        if (usernameColumn == -1 || testCaseIdColumn == -1) {
            throw new RuntimeException("Required columns not found in sheet");
        }

        // Step 2: Loop through rows to find matching TestCaseId
        for (int i = 1; i < rowCount; i++) {
            String currentTestCaseId = getCellData(i, testCaseIdColumn);
            if (currentTestCaseId.equalsIgnoreCase(testCaseId)) {
                return getCellData(i, passwordColumn);
            }
        }

        // Step 3: If not found
        throw new RuntimeException("TestCaseId '" + testCaseId + "' not found in sheet");
    }
}
