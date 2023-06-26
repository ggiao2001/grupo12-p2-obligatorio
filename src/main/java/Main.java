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
        // Create a new thread to run the CSV methods
        Thread csvThread = new Thread(new Runnable() {
            @Override
            public void run() {
                CSV.getDrivers();
                CSV.getCsvInfo();
            }
        });

        // Start the thread
        csvThread.start();

        // Display the loading bar while waiting for the thread to finish
        displayLoadingBar(csvThread);

        FrontEnd frontEnd = new FrontEnd();
        frontEnd.Menu();
    }

    private static void displayLoadingBar(Thread thread) {
        char[] animationChars = {'|', '/', '-', '\\'};

        try {
            System.out.println("Cargando...");

            while (thread.isAlive()) {
                for (char c : animationChars) {
                    System.out.print("\r" + "Procesando " + c);
                    Thread.sleep(100);
                }
            }

            System.out.println("\rCarga completa!     ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
