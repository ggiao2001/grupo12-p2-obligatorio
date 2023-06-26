import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Piloto;
import uy.edu.um.prog2.adt.Exceptions.EmptyQueueException;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FrontEnd {
    private static SistemaTweets sistemaTweets;
    private static Scanner scanner;

    public FrontEnd() {
        sistemaTweets = new SistemaTweetsImp();
        scanner = new Scanner(System.in);
    }

    public void Menu() throws EmptyTreeException, FullHeapException, OutOfBoundsException {
        int option = 0;

        do {
            System.out.println("----------------------");
            System.out.println("   Sistema de Tweets  ");
            System.out.println("----------------------");
            System.out.println("-------- Menu --------");
            System.out.println("1. Listar los 10 pilotos activos más mencionados en un mes ");
            System.out.println("2. Listar los 15 usuarios con más tweets ");
            System.out.println("3. Cantidad de hashtags distintos para un día dado ");
            System.out.println("4. Hashtag más usado para un día dado");
            System.out.println("5. Top 7 cuentas con más favoritos ");
            System.out.println("6. Cantidad de tweets con una palabra o frase específicos ");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un valor numérico válido.");
                scanner.nextLine(); // Consume the invalid input
                continue; // Restart the loop
            }

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
        System.out.println("Ingrese el año (número): ");
        int year = scanner.nextInt();
        System.out.println("Ingrese el mes (número): ");
        int month = scanner.nextInt();

        MyQueue<Piloto> pilotosMencionados = sistemaTweets.pilotosMasMencionadosMes(month, year);

        // Display the results
        System.out.println("------ Pilotos más mencionados en el mes " + month + " y año " + year + "------");
        int posicion = 1;
        boolean flag = true;
        while (pilotosMencionados != null && pilotosMencionados.size() > 0) {
            Piloto piloto = null;
            try {
                piloto = pilotosMencionados.dequeue();
            } catch (EmptyQueueException e) {
                System.out.println("Problema con Queue de Pilotos Vacia");
                break;
            }

            if (piloto.getMenciones() > 1) {
                flag = false;
                System.out.println(posicion + " " + piloto.getNombre() + ", mencionado: " + piloto.getMenciones() + " veces");
            } else if (piloto.getMenciones() == 1) {
                flag = false;
                System.out.println(posicion + " " + piloto.getNombre() + ", mencionado: " + piloto.getMenciones() + " vez");
            } else {
                System.out.println("Ningun otro piloto fue mencionado en este mes");
                break;
            }

            posicion++;
        }
        if (flag) {
            System.out.println("Ningun piloto mencionado este mes");
        }
        System.out.println();
    }

    private void listarUsuariosMasTweets() throws OutOfBoundsException {
        // Display the results
        System.out.println("------ Usuarios con más tweets ------");
        sistemaTweets.usuariosMasTweets();
    }

    private void cantidadHashtagsDia() {
        System.out.println("Ingrese el día (YYYY-MM-DD): ");
        String dateString = scanner.next();
        LocalDate dia;
        try {
            dia = LocalDate.parse(dateString);
            int cantidadHashtags = sistemaTweets.cantidadHashtags(dia);
            System.out.println("Cantidad de hashtags distintos para el día " + dia + ": " + cantidadHashtags);

        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Asegúrese de ingresar en el formato YYYY-MM-DD.");
        }

        System.out.println();
    }

    private void hashtagMasUsadoDia() {
        System.out.println("Ingrese el día (YYYY-MM-DD): ");
        String dateString = scanner.next();
        LocalDate dia;
        try {
            dia = LocalDate.parse(dateString);
            HashTag hashtagMasUsado = sistemaTweets.hashtagMasUsado(dia);
            if (hashtagMasUsado != null) {
                System.out.println("Hashtag más usado para el día " + dia + " fue : " + hashtagMasUsado.getText());
            } else {
                System.out.println("No hubo hashtags usados ese dia, o el dia selecionado escapa el rango de la base de datos.");
            }

        } catch (DateTimeParseException e) {
            System.out.println("Error: Formato de fecha incorrecto. Asegúrese de ingresar en el formato YYYY-MM-DD.");
        }
        System.out.println();
    }

    private void listarCuentasMasFavoritos() throws OutOfBoundsException {
        sistemaTweets.usuariosMasFavoritos();
    }

    private void cantidadTweetsPalabra() {
        System.out.println("Ingrese la palabra o frase específica: ");
        String palabra = scanner.next();
        int cantidadTweets = sistemaTweets.cantidadTweetsPalabra(palabra);
        System.out.println("Cantidad de tweets con la palabra o frase '" + palabra + "': " + cantidadTweets);
        System.out.println();
    }
}

