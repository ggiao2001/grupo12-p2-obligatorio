import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) throws EmptyTreeException, FullHeapException, OutOfBoundsException {
        //LocalTime start = LocalTime.now();
        CSV.getDrivers();
        CSV.getCsvInfo();

        //LocalTime end = LocalTime.now();
        //System.out.println("Tiempo de carga del CSV:" + (start.getNano() - end.getNano()) +" Nanosegundos");
        // 876421000 Nanosegundos ~ 0.87 segundos.

        FrontEnd frontEnd = new FrontEnd();
        frontEnd.Menu();
    }
}