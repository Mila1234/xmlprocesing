package database;

import domain.Employee;
import domain.Employees;

import java.io.File;
import java.io.IOException;

public interface DatabaseComunication {

    void  writeEmployees(Employees employees) throws IOException;
}
