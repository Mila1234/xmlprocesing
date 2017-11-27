package domain;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private int Personalnummer;
    private String Vorname;
    private String Nachname;
    private String Abteilung;


    public int getPersonalnummer() {
        return Personalnummer;
    }

    public void setPersonalnummer(int personalnummer) {
        Personalnummer = personalnummer;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getNachname() {
        return Nachname;
    }

    public void setNachname(String nachname) {
        Nachname = nachname;
    }

    public String getAbteilung() {
        return Abteilung;
    }

    public void setAbteilung(String abteilung) {
        Abteilung = abteilung;
    }
}
