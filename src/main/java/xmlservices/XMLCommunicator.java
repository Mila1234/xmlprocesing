package xmlservices;

import domain.Employees;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLCommunicator {

   public Employees readXml(File file){
       Employees emps = null;
       try {

           JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
           Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

           emps = (Employees) jaxbUnmarshaller.unmarshal( file);

       } catch (JAXBException e) {
           e.printStackTrace();
       }
       return  emps;
   }
}
