import java.time.LocalDate;
import java.util.Scanner;
import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.MyQueueImp;

public class FrontEnd {
    private static SistemaTweets sistemaTweets;
    private static Scanner scanner;

    public FrontEnd() {
        sistemaTweets = new SistemaTweetsImp();
        scanner = new Scanner(System.in);
    }

    public void Menu() throws EmptyTreeException, FullHeapException, OutOfBoundsException {
        int option;
        do {
            System.out.println("------ Menu ------");
            System.out.println("1. Listar los 10 pilotos activos más mencionados en un mes");
            System.out.println("2. Listar los 15 usuarios con más tweets");
            System.out.println("3. Cantidad de hashtags distintos para un día dado");
            System.out.println("4. Hashtag más usado para un día dado");
            System.out.println("5. Top 7 cuentas con más favoritos");
            System.out.println("6. Cantidad de tweets con una palabra o frase específicos");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> listarPilotosMencionadosMes();
                case 2 -> listarUsuariosMasTweets();
                case 3 -> cantidadHashtagsDia();
                case 4 -> hashtagMasUsadoDia();
                case 5 -> listarCuentasMasFavoritos();
                case 6 -> cantidadTweetsPalabra();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (option != 0);
    }

    private void listarPilotosMencionadosMes() throws EmptyTreeException, FullHeapException {
        System.out.println("Ingrese el mes (número): ");
        int month = scanner.nextInt();
        System.out.println("Ingrese el año (número): ");
        int year = scanner.nextInt();

        MyQueueImp<String> pilotosMencionados = (MyQueueImp<String>) sistemaTweets.pilotosMasMencionadosMes(month, year);

        // Display the results
        System.out.println("------ Pilotos más mencionados en el mes " + month + " y año " + year + "------");
        int posicion = 1;
        while (pilotosMencionados != null && pilotosMencionados.size() >= 0) {
            String piloto = null;
            try {
                piloto = pilotosMencionados.dequeue();
            } catch (EmptyQueueException e) {
                System.out.println("Problema con Queue de Pilotos Vacia");
                break;
            }
            System.out.println(posicion+piloto);
            posicion++;
        }
        System.out.println();
    }

    private void listarUsuariosMasTweets() throws OutOfBoundsException {
        MyQueueImp<User> usuariosMasTweets = (MyQueueImp<User>) sistemaTweets.usuariosMasTweets();

        // Display the results
        System.out.println("------ Usuarios con más tweets ------");
        while (usuariosMasTweets!= null && usuariosMasTweets.size()>=0) {
            User usuario = null;
            try {
                usuario = usuariosMasTweets.dequeue();
            } catch (EmptyQueueException e) {
                System.out.println("Problema con Queue de Usuarios Vacia");
            }
            System.out.println(usuario);
        }
        System.out.println();
    }

    private void cantidadHashtagsDia() {
        System.out.println("Ingrese el día (YYYY-MM-DD): ");
        String dateString = scanner.next();
        LocalDate dia = LocalDate.parse(dateString);
        int cantidadHashtags = sistemaTweets.cantidadHashtags(dia);
        System.out.println("Cantidad de hashtags distintos para el día " + dia + ": " + cantidadHashtags);
        System.out.println();
    }

    private void hashtagMasUsadoDia() {
        System.out.println("Ingrese el día (YYYY-MM-DD): ");
        String dateString = scanner.next();
        LocalDate dia = LocalDate.parse(dateString);
        HashTag hashtagMasUsado = sistemaTweets.hashtagMasUsado(dia);
        System.out.println("Hashtag más usado para el día " + dia + ": " + hashtagMasUsado);
        System.out.println();
    }

    private void listarCuentasMasFavoritos() throws OutOfBoundsException {
        MyQueue<User> cuentasMasFavoritos = sistemaTweets.usuariosMasFavoritos();

        // Display the results
        System.out.println("------ Cuentas con más favoritos ------");
        while (cuentasMasFavoritos!= null &&cuentasMasFavoritos.size() >=0) {
            User usuario = null;
            try {
                usuario = cuentasMasFavoritos.dequeue();
            } catch (EmptyQueueException e) {
                System.out.println("Problema con Queue de Cuentas Favoritos");
            }
            System.out.println(usuario);
        }
        System.out.println();
    }

    private void cantidadTweetsPalabra() {
        System.out.println("Ingrese la palabra o frase específica: ");
        String palabra = scanner.next();
        int cantidadTweets = sistemaTweets.cantidadTweetsPalabra(palabra);
        System.out.println("Cantidad de tweets con la palabra o frase '" + palabra + "': " + cantidadTweets);
        System.out.println();
    }
}

