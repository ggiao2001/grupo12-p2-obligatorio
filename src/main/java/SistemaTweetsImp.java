import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Piloto;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyList;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.*;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaTweetsImp implements SistemaTweets {
    MyLinkedListImp<User> usuarios = CSV.userLinkedList;
    MyLinkedListImp<Tweet> tweets = CSV.tweetLinkedList;
    MyBinarySearchTreeImp<CSV.LocalDateTimeWrapper, Tweet> tweetsTree = CSV.tweetBST;
    MyLinkedListImp<HashTag> hashtags = CSV.hashTagLinkedList;
    MyLinkedListImp<String> pilotos = CSV.driversLinkedList;

    //Listar los 10 pilotos activos en la temporada 2023 más mencionados en los tweets
    //en un mes (este mes será ingresado como 2 parámetros separados, mes y año, por
    //consola). Este listado debe incluir el nombre de los pilotos y la cantidad de
    //ocurrencias para cada uno de manera ordenada. Se espera que esta operación sea
    //de orden n en notación Big O.
    @Override
    public MyQueueImp<Piloto> pilotosMasMencionadosMes(int month, int year) throws FullHeapException, EmptyTreeException {
        MyQueueImp<Piloto> topPilotos = new MyQueueImp<>();
        MyHeapImp<Piloto> heap = new MyHeapImp<>(true);
        LocalDateTime fecha1 = LocalDate.of(year, month, 1).atStartOfDay();
        LocalDateTime fecha2;
        if (month < 12) {
            fecha2 = LocalDate.of(year, month + 1, 1).atStartOfDay();
        } else {
            fecha2 = LocalDate.of(year + 1, 1, 1).atStartOfDay();
        }
        CSV.LocalDateTimeWrapper fecha1Wrapp = new CSV.LocalDateTimeWrapper(fecha1);
        CSV.LocalDateTimeWrapper fecha2Wrapp = new CSV.LocalDateTimeWrapper(fecha2);
        MyLinkedListImp<Tweet> tweetsFecha = tweetsTree.getRange(fecha1Wrapp,fecha2Wrapp); //recorrer todos los tweets en arbol --> orden log n
        for (int j = 0; j < pilotos.size(); j++) { //cantidad de pilotos fija --> orden 1
            String nombreP = pilotos.get(j);
            Piloto p = new Piloto(nombreP);

            String[] nameParts = nombreP.split(" "); // Split full name into first name and last name
            String firstName = nameParts[0];
            String lastName = nameParts[nameParts.length - 1];
            String regexPattern = "\\b(" + Pattern.quote(nombreP) + "|" + Pattern.quote(firstName) + "|" + Pattern.quote(lastName) + ")\\b";
            Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);

            int mention_count = 0;
            for (int i = 0; i < tweetsFecha.size(); i++) {
                Tweet t = tweetsFecha.get(i);

                String tcontent = t.getContent();
                Matcher matcher = pattern.matcher(tcontent);
                while (matcher.find()) {
                    mention_count++;
                }

            }
            p.setMenciones(mention_count);
            if (p.getMenciones() > 0 && heap.getSize() < 10) {
                heap.agregar(p);
            }
        }
        while (heap.getSize() > 0) {
            topPilotos.enqueue(heap.obtenerYEliminar());
        }
        return topPilotos;
    }

    //listar los 15 usuarios con mas tweets incluyendo  nombre de usuario, la
    //cantidad de tweets, y si el usuario está verificado o no, ordenado por cantidad de
    //tweets en orden descendente. Se espera que esta operación sea de orden n en
    //notación Big O.
    @Override
    public void usuariosMasTweets() throws OutOfBoundsException {
        MyLinkedListImp<User> top15usuarios = new MyLinkedListImp<>();
        for (int i = 0; i < usuarios.size(); i++) { //recorre todos los usuarios --> orden n
            User u = usuarios.get(i);
            for (int j = 0; j < top15usuarios.size(); j++) { //lista de size fijo --> orden 1
                if (top15usuarios.get(j).tweetCount() < u.tweetCount()) {
                    top15usuarios.addIndex(j, u);
                    break;
                }
            }
            if (top15usuarios.size() < 15) {
                top15usuarios.add(u);
            }
            if (top15usuarios.size() > 15) {
                top15usuarios.remove(top15usuarios.getLast().getValue());
            }
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(i + 1 + ". " + top15usuarios.get(i).getName() + " - " + top15usuarios.get(i).tweetCount() + " tweets - verified: " + top15usuarios.get(i).getVerified());
        }
    }

    //Cantidad de hashtags distintos para un día dado. El día será ingresado en el formato
    //YYYY-MM-DD.

    @Override
    public int cantidadHashtags(LocalDate dia) {
        CSV.LocalDateTimeWrapper diaWrapped = new CSV.LocalDateTimeWrapper(dia.atStartOfDay());
        CSV.LocalDateTimeWrapper diaDespuesWrapped = new CSV.LocalDateTimeWrapper(dia.plusDays(1).atStartOfDay());
        MyLinkedListImp<Tweet> tweetsFecha = tweetsTree.getRange(diaWrapped,diaDespuesWrapped);
        MyLinkedListImp<HashTag> hashTagsDia = new MyLinkedListImp<>();
        int contador = 0;
        for (int i = 0; i < tweetsFecha.size(); i++) {
            Tweet t = tweetsFecha.get(i);
            if (t.getDate().toLocalDate().equals(dia)) {
                for (int j = 0; j < t.getHashTags().size(); j++) {
                    HashTag h = t.getHashTags().get(j);
                    if (!hashTagsDia.contains(h)) {
                        hashTagsDia.add(h);
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    //Hashtag más usado para un día dado, sin tener en cuenta #f1. El día será ingresado
    //en el formato YYYY-MM-DD.
    @Override
    public HashTag hashtagMasUsado(LocalDate dia) {
        CSV.LocalDateTimeWrapper diaWrapped = new CSV.LocalDateTimeWrapper(dia.atStartOfDay());
        CSV.LocalDateTimeWrapper diaDespuesWrapped = new CSV.LocalDateTimeWrapper(dia.plusDays(1).atStartOfDay());
        MyLinkedListImp<Tweet> tweetsFecha = tweetsTree.getRange(diaWrapped,diaDespuesWrapped);
        MyLinkedListImp<HashTag> aReset = new MyLinkedListImp<>();
        HashTag hTop = null;
        for (int i = 0; i < tweetsFecha.size(); i++) {
            Tweet t = tweetsFecha.get(i);
            if (t.getDate().isAfter(dia.atStartOfDay()) && t.getDate().isBefore(dia.plusDays(1).atStartOfDay())) {
                for (int j = 0; j < t.getHashTags().size(); j++) {
                    HashTag h = t.getHashTags().get(j);
                    if (!h.getText().toLowerCase().equals("'f1'") && !h.getText().toLowerCase().equals("f1")) {
                        h.setUsosDia(h.getUsosDia() + 1);
                        aReset.add(h);
                        if (hTop == null || h.getUsosDia() > hTop.getUsosDia()) {
                            hTop = h;
                        }
                    }
                }
            }
        }
        //Reseteo los contadores de los hashtags
        for (int i = 0; i < aReset.size(); i++) {
            aReset.get(i).setUsosDia(0);
        }

        return hTop;
    }


    //Top 7 cuentas con más favoritos. Para este listado se deberá retornar el nombre del
    //usuario, junto con la cantidad de favoritos.
    @Override
    public void usuariosMasFavoritos() throws OutOfBoundsException {
        usuarios.quickSort((usuario1, usuario2) -> usuario2.getFavourites().compareTo(usuario1.getFavourites()));
        for (int i = 0; i < 7; i++) {
            System.out.println(usuarios.get(i).getName() + " " + usuarios.get(i).getFavourites());
        }
    }

    //Cantidad de tweets con una palabra o frase específicos (que será ingresado como
    //parámetro)
    @Override
    public int cantidadTweetsPalabra(String frase) {
        int mentionCount = 0;
        for (int i = 0; i < tweets.size(); i++) {
            Tweet t = tweets.get(i);
            String tcontent = t.getContent().toLowerCase();
            if (tcontent.contains(frase)) {
                mentionCount++;
            }
        }
        return mentionCount;
    }



}
