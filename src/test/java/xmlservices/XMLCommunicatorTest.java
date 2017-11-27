package xmlservices;

import domain.Employee;
import domain.Employees;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertTrue;

public class XMLCommunicatorTest{

    public static String xmlFile = "testexample.xml";

    XMLCommunicator xmlCommunicator = new XMLCommunicator();



    @Test
    public void readXml(){

        try {
            URL url = XMLCommunicatorTest.class.getClass().getResource("/"+xmlFile);
            File file = new File(url.toURI());

            Employee employee = new Employee();
            employee.setAbteilung("IT");
            employee.setVorname("M");
            employee.setPersonalnummer(1);

            List<Employee> listEmp = new ArrayList<>();listEmp.add(employee);
            Employees emps = new Employees();emps.setEmployees(listEmp);

            Employees result = xmlCommunicator.readXml(file);

            assertTrue(result.getEmployees().size()==14);

        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
