import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.MyQueueImp;
import uy.edu.um.prog2.adt.TADs.NodeBST;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws EmptyTreeException, FullHeapException, OutOfBoundsException {
        CSV.getDrivers();
        CSV.getCsvInfo();

        FrontEnd frontEnd = new FrontEnd();
        frontEnd.Menu();
    }
}