package main;

import database.DatabaseComunication;
import database.ExcelDatabase;
import domain.Employees;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import xmlservices.XMLCommunicator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class ApplicationXmlProcesingTest {
    public static String xmlFile = "integrTestExample.xml";
    public static String xlsxFile= "integrTestExcelFile.xlsx";

    @Test
    public void main (){

        try {

            XMLCommunicator xmlCommunicator = new XMLCommunicator();

            URL url = ApplicationXmlProcesingTest.class.getClass().getResource("/"+xmlFile);
            File file = new File(url.toURI());
            assertTrue(file!=null);

            Employees employees = xmlCommunicator.readXml(file);
            assertTrue(employees.getEmployees().size()==14);

            File fileExcel = new File(xlsxFile);
            assertTrue(fileExcel!=null);
            DatabaseComunication excelDatabase = new ExcelDatabase(fileExcel);


            excelDatabase.writeEmployees(employees);

            FileInputStream excelFile = new FileInputStream(new File(xlsxFile));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Integer numberOfRowsSheet1 = workbook.getSheetAt(0).getPhysicalNumberOfRows();
            Integer numberOfRowsSheet2 = workbook.getSheetAt(1).getPhysicalNumberOfRows();
            assertTrue(numberOfRowsSheet1.equals(15));
            assertTrue(numberOfRowsSheet2.equals(4));


        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
