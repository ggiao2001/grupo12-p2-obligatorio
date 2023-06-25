import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Piloto;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;


import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import java.time.LocalDate;

public class SistemaTweetsImpTest {

    private long startTime;
    private final SistemaTweets sistemaTweets = new SistemaTweetsImp();
    private MemoryUsage memoryBefore;
    private MemoryMXBean memoryBean;

    @BeforeClass
    public static void initialize(){
        CSV.getDrivers();
        CSV.getCsvInfo();
    }
    @Before
    public void Start(){
        System.out.println("");
        memoryBean = ManagementFactory.getMemoryMXBean();
        memoryBefore = memoryBean.getHeapMemoryUsage();
        startTime = System.currentTimeMillis();
    }


    @After
    public void end(){
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " milliseconds");
        MemoryUsage memoryAfter = memoryBean.getHeapMemoryUsage();
        long memoryUsed = memoryAfter.getUsed() - memoryBefore.getUsed();
        System.out.println("Memory used: " + memoryUsed + " bytes");
        System.out.println();
    }

    /// VELOCIDAD Y RAM
    @Test
    public void testVelocidadCarga(){
        System.out.println("Test de Velocidad y Memoria - Carga de Datos");
        CSV.getDrivers();
        CSV.getCsvInfo();
    }

    @Test
    public void testVelocidadMetodo1() throws EmptyTreeException, FullHeapException {
        System.out.println("Test de Velocidad y Memoria - Metodo 1");

        //MyQueue<Piloto> pilotosMasMencionados = sistemaTweets.pilotosMasMencionadosMes(12, 2021);

    }

    @Test
    public void testVelocidadMetodo2(){
        System.out.println("Test de Velocidad y Memoria - Metodo 2");
        try {
            sistemaTweets.usuariosMasTweets();
        } catch (OutOfBoundsException e) {
            fail();
        }

    }

    @Test
    public void testVelocidadMetodo3(){
        System.out.println("Test de Velocidad y Memoria - Metodo 3");
        LocalDate dia = LocalDate.of(2021,12,12);
        int cantidadHashtags = sistemaTweets.cantidadHashtags(dia);

    }

    @Test
    public void testVelocidadMetodo4(){
        System.out.println("Test de Velocidad y Memoria - Metodo 4");
        LocalDate dia = LocalDate.of(2021,12,12);
        HashTag hashtagMasUsado = sistemaTweets.hashtagMasUsado(dia);

    }

    @Test
    public void testVelocidadMetodo5(){
        System.out.println("Test de Velocidad y Memoria - Metodo 5");
        try {
            sistemaTweets.usuariosMasFavoritos();
        } catch (OutOfBoundsException e) {
            fail();
        }

    }

    @Test
    public void testVelocidadMetodo6(){
        System.out.println("Test de Velocidad y Memoria - Metodo 6");
        String f1 = "f1";
        int cantidadTweetsPalabra = sistemaTweets.cantidadTweetsPalabra(f1);

    }
}
