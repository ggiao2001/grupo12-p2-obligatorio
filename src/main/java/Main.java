import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws EmptyTreeException, FullHeapException {
        CSV.getDrivers();
        CSV.getCsvInfo();
        FrontEnd frontEnd = new FrontEnd();
        frontEnd.Menu();
    }
}