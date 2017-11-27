package main;

import database.DatabaseComunication;
import database.ExcelDatabase;
import domain.Employees;
import xmlservices.XMLCommunicator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ApplicationXmlProcesing {

    public static String xmlFile = "example.xml";
    public static String xlsxFile= "excelfile.xlsx";

    public static void main (String[] arg) throws URISyntaxException,IOException {

            XMLCommunicator xmlCommunicator = new XMLCommunicator();

            URL url = ApplicationXmlProcesing.class.getClass().getResource("/"+xmlFile);
            File file = new File(url.toURI());

            Employees employees = xmlCommunicator.readXml(file);

            File fileExcel = new File(xlsxFile);
            DatabaseComunication excelDatabase = new ExcelDatabase(fileExcel);

            excelDatabase.writeEmployees(employees);

    }
}
