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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaTweetsImp implements SistemaTweets{
    MyList<User> usuarios = CSV.userLinkedList;
    MyLinkedListImp<Tweet> tweets = CSV.tweetLinkedList;
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
        MyHeapImp<Piloto> heap = new MyHeapImp<Piloto>(true);
        MyLinkedListImp<Tweet> listaTweets = CSV.tweetLinkedList;
        LocalDate fecha1 = LocalDate.of(year, month, 1);
        LocalDate fecha2;
        if(month < 12){
            fecha2 = LocalDate.of(year, month+1, 1);
        }else{
            fecha2 = LocalDate.of(year+1, 1, 1);
        }


        for (int j = 0; j < pilotos.size(); j++) { //cantidad de pilotos fija --> orden 1
            String nombreP = pilotos.get(j);
            Piloto p = new Piloto(nombreP);


            String[] nameParts = nombreP.split(" "); // Split full name into first name and last name
            String firstName = nameParts[0];
            String lastName = nameParts[nameParts.length - 1];
            String regexPattern = "\\b(" + Pattern.quote(nombreP) + "|" + Pattern.quote(firstName) + "|" + Pattern.quote(lastName) + ")\\b";
            Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);

            int mention_count =0;
            for (int i = 0; i < listaTweets.size(); i++) { //recorrer todos los tweets --> orden n
                Tweet t = listaTweets.get(i);
                if (t.getDate().toLocalDate().isAfter(fecha1) && t.getDate().toLocalDate().isBefore(fecha2)) {
                    String tcontent = t.getContent();
                    Matcher matcher = pattern.matcher(tcontent);
                    while (matcher.find()) {
                        mention_count++;
                    }

                    //if (tcontent.contains(nombreP)) {
                    //    mention_count++;
                    //}
                }
            }
            p.setMenciones(mention_count);
            heap.agregar(p);
        }
        for (int i = 0; i < 10; i++) {
            topPilotos.enqueue(heap.obtenerYEliminar());
        }
        return topPilotos;
    }

    //listar los 15 usuarios con mas tweets incluyendo  nombre de usuario, la
    //cantidad de tweets, y si el usuario está verificado o no, ordenado por cantidad de
    //tweets en orden descendente. Se espera que esta operación sea de orden n en
    //notación Big O.
    @Override
    public MyQueueImp<User> usuariosMasTweets() throws OutOfBoundsException {
        MyLinkedListImp<User> top15usuarios = new MyLinkedListImp<>();
        MyQueueImp<User> top15queue = new MyQueueImp<>();
        User userMin = new User();
        for (int i = 0; i < usuarios.size(); i++) { //recorre todos los usuarios --> orden n
            User u = usuarios.get(i);
            boolean userInserted = false;
            if (top15usuarios.size() < 15) {
                top15usuarios.add(u);
                userInserted = true;
            } else {
                for (int j = 0; j < top15usuarios.size(); j++) { //lista de size fijo --> orden 1
                    if (userInserted = false && top15usuarios.get(j).tweetCount() < u.tweetCount()) {
                        top15usuarios.addIndex(j,u);
                        userMin = top15usuarios.get(15);
                        top15usuarios.remove(userMin);
                    }

                }
            }
        }
        for (int i = 0; i < 15; i++) {
            top15queue.enqueue(top15usuarios.get(i));
        }
        return top15queue;
    }

    //Cantidad de hashtags distintos para un día dado. El día será ingresado en el formato
    //YYYY-MM-DD.
    @Override
    public int cantidadHashtags(LocalDate dia) {
        MyLinkedListImp<HashTag> hashTagsDia = new MyLinkedListImp<>();
        int contador = 0;
        for (int i = 0; i < tweets.size(); i++) {
            Tweet t = tweets.get(i);
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

        MyLinkedListImp<HashTag> aReset = new MyLinkedListImp<>();
        HashTag hTop = null;
        for (int i = 0; i < tweets.size(); i++) {
            Tweet t = tweets.get(i);
            if (t.getDate().isAfter(dia.atStartOfDay()) && t.getDate().isBefore(dia.plusDays(1).atStartOfDay())) {
                for (int j = 0; j < t.getHashTags().size(); j++) {
                    HashTag h = t.getHashTags().get(j);
                    if (!h.getText().toLowerCase().equals("'f1'") && !h.getText().toLowerCase().equals("f1")) {
                        h.setUsosDia(h.getUsosDia()+1);
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
    public MyQueue<User> usuariosMasFavoritos() throws OutOfBoundsException {
        MyLinkedListImp<User> top7usuarios = new MyLinkedListImp<>();
        MyQueueImp<User> top7queue = new MyQueueImp<>();
        User userMin = new User();
        for (int i = 0; i < usuarios.size(); i++) { //recorre todos los usuarios --> orden n
            User u = usuarios.get(i);
            boolean userInserted = false;
            if (top7usuarios.size() < 15) {
                top7usuarios.add(u);
                userInserted = true;
            } else {
                for (int j = 0; j < top7usuarios.size(); j++) { //lista de size fijo --> orden 1
                    if (userInserted = false && top7usuarios.get(j).getFavourites() < u.getFavourites()) {
                        top7usuarios.addIndex(j,u);
                        userMin = top7usuarios.get(7);
                        top7usuarios.remove(userMin);
                    }
                }
            }
        }
        for (int i = 0; i < 7; i++) { //size fijo --> orden 1
            top7queue.enqueue(top7usuarios.get(i));
        }
        return top7queue;
    }

    //Cantidad de tweets con una palabra o frase específicos (que será ingresado como
    //parámetro)
    @Override
    public int cantidadTweetsPalabra(String frase) {
        return 0;
    }
}
