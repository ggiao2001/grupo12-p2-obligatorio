
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;

public class Main {
    public static void main(String[] args) throws EmptyTreeException, FullHeapException, OutOfBoundsException {
        Thread csvThread = new Thread(new Runnable() {
            @Override
            public void run() {
                CSV.getDrivers();
                CSV.getCsvInfo();
            }
        });
        csvThread.start();
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
