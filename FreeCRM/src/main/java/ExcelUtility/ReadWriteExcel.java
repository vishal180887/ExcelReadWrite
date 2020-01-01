package ExcelUtility;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.util.Vector;

public class ReadWriteExcel {
    private Vector<String> columnNames;
    private Vector<Vector<String>> data;
    private DataFormatter dataFormatter = new DataFormatter();

    public ReadWriteExcel(Sheet sheet) {
        this.columnNames = new Vector<String>();
        this.data = new Vector<Vector<String>>();

        int firstRow = sheet.getFirstRowNum();
        Row colNamesRow = sheet.getRow(firstRow);
        int firstCol = colNamesRow.getFirstCellNum();
        int lastColP1 = colNamesRow.getLastCellNum();

        for (int c = firstCol; c < lastColP1; c++) {
            Cell cell = colNamesRow.getCell(c);
            String columnName = dataFormatter.formatCellValue(cell);
            this.columnNames.add(columnName);
        }

        int lastRow = sheet.getLastRowNum();
        for (int r = firstRow + 1; r < lastRow + 1; r++) {
            Vector<String> cells = new Vector<String>();
            Row row = sheet.getRow(r);
            if (row == null) {
                row = sheet.createRow(r);
            }
            for (int c = firstCol; c < lastColP1; c++) {
                Cell cell = row.getCell(c);
                String cellValue = dataFormatter.formatCellValue(cell);
                cells.add(cellValue);
            }
            this.data.add(cells);
        }
    }

    public int getColumnCount() {
        return this.columnNames.size();
    }

    public int getRowCount() {
        return this.data.size();
    }

    public String getColumnName(int columnIndex) {
        return this.columnNames.get(columnIndex);
    }

    public String getValueAt(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex).get(columnIndex);
    }

    public Vector getColumnNames() {
        return this.columnNames;
    }

    public Vector getData() {
        return this.data;
    }

    // More getters and setters...

    public static void main(String[] args) throws Exception {
        Workbook workbook = WorkbookFactory.create(new FileInputStream("D:\\JavaProgram\\FreeCRM\\src\\main\\resources\\Data.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        ReadWriteExcel excelTableModel = new ReadWriteExcel(sheet);

        System.out.println(excelTableModel.getColumnCount());
        System.out.println(excelTableModel.getRowCount());

        System.out.println(excelTableModel.getColumnNames());
        System.out.println(excelTableModel.getData());

        //System.out.println(excelTableModel.getColumnName(0));
        System.out.println(excelTableModel.getValueAt(6, 4));

        workbook.close();
    }
}