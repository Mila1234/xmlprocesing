package database;

import domain.Employee;
import domain.Employees;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelDatabase implements DatabaseComunication {

    private XSSFWorkbook workbook;
    private Sheet  sheet1;
    private Sheet  sheet2;
    private  int rowNumSheet1;
    private  int rowNumSheet2;

    private File file;


    private final static int EMPLOYEE_ID_COLUMN = 0;
    private final static int EMPLOYEE_NAME_COLUMN = 1;
    private final static int EMPLOYEE_SURNAME_COLUMN = 2;
    private final static int EMPLOYEE_DEPARTMENT_COLUMN = 3;


    private final static int DEPARTMENT_NAME_COLUMN = 0;
    private final static int DEPARTMENT_SUM_COLUMN = 1;


    public ExcelDatabase(File file) {
        this.file = file;

        workbook = new XSSFWorkbook();

        CellStyle style = workbook.createCellStyle();
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        boldFont.setItalic(true);
        style.setFont(boldFont);
        style.setAlignment(HorizontalAlignment.CENTER);

        sheet1 = workbook.createSheet();
        rowNumSheet1 = 0;

        Row rowHeaderSheet1 = sheet1.createRow(rowNumSheet1++);
        Cell cell = rowHeaderSheet1.createCell(EMPLOYEE_ID_COLUMN);
        cell.setCellValue("Id");
        cell.setCellStyle(style);

        cell = rowHeaderSheet1.createCell(EMPLOYEE_NAME_COLUMN);
        cell.setCellValue("Name");
        cell.setCellStyle(style);

        cell = rowHeaderSheet1.createCell(EMPLOYEE_SURNAME_COLUMN);
        cell.setCellValue("Surname");
        cell.setCellStyle(style);

        cell = rowHeaderSheet1.createCell(EMPLOYEE_DEPARTMENT_COLUMN);
        cell.setCellValue("Department");
        cell.setCellStyle(style);


        //sheet 2.
        sheet2 = workbook.createSheet();
        rowNumSheet2 = 0;

        Row rowHeaderSheet2 = sheet2.createRow(rowNumSheet2++);
        cell = rowHeaderSheet2.createCell(DEPARTMENT_NAME_COLUMN);
        cell.setCellValue("Department");
        cell.setCellStyle(style);

        cell = rowHeaderSheet2.createCell(DEPARTMENT_SUM_COLUMN);
        cell.setCellValue("Number of employee");
        cell.setCellStyle(style);

    }


    public void  writeEmployees(Employees employees) throws IOException {

        Map<String, Integer> mapDepartentsInfo = new HashMap<>();

        employees.getEmployees().stream().forEach(employee->{
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);

            Row row = sheet1.createRow(rowNumSheet1++);
            Cell cell = row.createCell(EMPLOYEE_ID_COLUMN);
            cell.setCellValue(employee.getPersonalnummer());
            cell.setCellStyle(style);

            cell = row.createCell(EMPLOYEE_NAME_COLUMN);
            cell.setCellValue(employee.getVorname());
            cell.setCellStyle(style);

            cell = row.createCell(EMPLOYEE_SURNAME_COLUMN);
            cell.setCellValue(employee.getNachname());
            cell.setCellStyle(style);

            cell = row.createCell(EMPLOYEE_DEPARTMENT_COLUMN);
            cell.setCellValue(employee.getAbteilung());
            cell.setCellStyle(style);

            if (!mapDepartentsInfo.containsKey(employee.getAbteilung())){
                mapDepartentsInfo.put(employee.getAbteilung(),1);
            }else{
                mapDepartentsInfo.put(employee.getAbteilung(),mapDepartentsInfo.get(employee.getAbteilung())+1);
            }

        });

        mapDepartentsInfo.forEach((departName,numberOfEmp)->{
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);

            Row row = sheet2.createRow(rowNumSheet2++);
            Cell cell = row.createCell(DEPARTMENT_NAME_COLUMN);
            cell.setCellValue(departName);
            cell.setCellStyle(style);

            cell = row.createCell(DEPARTMENT_SUM_COLUMN);
            cell.setCellValue(numberOfEmp);
            cell.setCellStyle(style);
        });


        FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
        workbook.write(fileOut);
        workbook.close();
        fileOut.close();

    }



}
