package database;

import domain.Employee;
import domain.Employees;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class ExcelDatabaseTest {
    public static String FILE_NAME = "testexcelfile.xlsx";

    ExcelDatabase excelDatabase = new ExcelDatabase(new File(FILE_NAME));

    @Test
    public void writeEmployees(){
        try {

            List<Employee> listEmp = new ArrayList<>();
            Employee employee = new Employee();
            employee.setAbteilung("IT");
            employee.setVorname("M");
            employee.setVorname("n");
            employee.setPersonalnummer(1);
            listEmp.add(employee);
            employee = new Employee();
            employee.setAbteilung("SALES");
            employee.setVorname("M");
            employee.setVorname("n");
            employee.setPersonalnummer(2);
            listEmp.add(employee);
            employee = new Employee();
            employee.setAbteilung("SALES");
            employee.setVorname("M");
            employee.setVorname("n");
            employee.setPersonalnummer(3);
            listEmp.add(employee);

            Employees emps = new Employees();
            emps.setEmployees(listEmp);

            excelDatabase.writeEmployees(emps);

            assertTrue(new File(FILE_NAME).exists());

            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Integer numberOfRowsSheet1 = workbook.getSheetAt(0).getPhysicalNumberOfRows();
            Integer numberOfRowsSheet2 = workbook.getSheetAt(1).getPhysicalNumberOfRows();

            assertTrue(numberOfRowsSheet1.equals(4));
            assertTrue(numberOfRowsSheet2.equals(3));

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
