import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Interfaces.MyList;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SistemaTweetsImp implements SistemaTweets{
    MyList<User> usuarios = CSV.userLinkedList;
    MyLinkedListImp<Tweet> tweets = CSV.tweetLinkedList;
    MyLinkedListImp<HashTag> hashtags = CSV.hashTagLinkedList;

    @Override
    public MyQueueImp<String> pilotosMasMencionadosMes(int month, int year) {

        MyQueueImp<String> topPilotos = new MyQueueImp<>();
        MyLinkedListImp<String> listaPilotos = CSV.driversLinkedList;
        MyHeapImp<NodeBST<Integer, String>> heap = new MyHeapImp<NodeBST<Integer, String>>(true);
        MyLinkedListImp<Tweet> listaTweets = CSV.tweetLinkedList;
        LocalDateTime fecha1 = LocalDateTime.of(year, month, 1, 0,0);
        LocalDateTime fecha2 = LocalDateTime.of(year, month+1, 0,0,0);

        for (int j = 0; j < listaPilotos.size(); j++) {
            String p = listaPilotos.get(j);
            int menciones = 0;
            for (int i = 0; i < listaTweets.size(); i++) {
                Tweet t = listaTweets.get(i);
                if (t.getDate().compareTo(fecha1) >= 0 && t.getDate().compareTo(fecha2) < 0) {
                    if (t.getContent().contains(p)) {
                        menciones++;
                    }
                }
            }

        }
        return null;
    }

    @Override
    public MyQueue<User> usuariosMasTweets() {
        return null;
    }

    @Override
    public int cantidadHashtags(LocalDate dia) {
        return 0;
    }

    @Override
    public HashTag hashtagMasUsado(LocalDate dia) {
        return null;
    }

    @Override
    public MyQueue<User> usuariosMasFavoritos() {
        return null;
    }

    @Override
    public int cantidadTweetsPalabra(String frase) {
        return 0;
    }
}
