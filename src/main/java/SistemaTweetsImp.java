import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Piloto;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Interfaces.MyList;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SistemaTweetsImp implements SistemaTweets{
    MyList<User> usuarios = CSV.userLinkedList;
    MyLinkedListImp<Tweet> tweets = CSV.tweetLinkedList;
    MyLinkedListImp<HashTag> hashtags = CSV.hashTagLinkedList;
    MyLinkedListImp<String> pilotos = CSV.driversLinkedList;

    @Override
    public MyQueueImp<String> pilotosMasMencionadosMes(int month, int year) throws FullHeapException, EmptyTreeException {

        MyQueueImp<String> topPilotos = new MyQueueImp<>();
        MyHeapImp<Piloto> heap = new MyHeapImp<Piloto>(true);
        MyLinkedListImp<Tweet> listaTweets = CSV.tweetLinkedList;
        LocalDate fecha1 = LocalDate.of(year, month, 1);
        LocalDate fecha2 = LocalDate.of(year, month+1, 1);

        for (int j = 0; j < pilotos.size(); j++) {
            String nombreP = pilotos.get(j);
            Piloto p = new Piloto(nombreP);
            for (int i = 0; i < listaTweets.size(); i++) {
                Tweet t = listaTweets.get(i);
                if (t.getDate().toLocalDate().compareTo(fecha1) >= 0 && t.getDate().toLocalDate().compareTo(fecha2) < 0) {
                    if (t.getContent().contains(nombreP)) {
                        p.setMenciones(p.getMenciones()+1);
                    }
                }
            }
            heap.agregar(p);
        }
        for (int i = 0; i < 10; i++) {
            topPilotos.enqueue(heap.obtenerYEliminar().getNombre());
        }
        return topPilotos;
    }

    @Override
    public MyQueue<User> usuariosMasTweets() {
        return null;
    }

    @Override
    public int cantidadHashtags(LocalDate dia) {
        MyHashTableImp<Long, HashTag> hashtagsDia = new MyHashTableImp();
        int contador = 0;
        for (int i = 0; i < tweets.size(); i++) {
            Tweet t = tweets.get(i);
            if (t.getDate().toLocalDate().equals(dia)) {
                for (int j = 0; j < t.getHashTags().size(); j++) {
                    HashTag h = t.getHashTags().get(j);
                    if (hashtagsDia.contains(h.getId()) == false) {
                        hashtagsDia.put(h.getId(),h);
                        contador++;
                    }
                }
            }
        }
        return contador;
    }

    @Override
    public HashTag hashtagMasUsado(LocalDate dia) {
        for (int i = 0; i < hashtags.size(); i++) {
            hashtags.get(i).setUsosDia(0);
        }
        HashTag hTop = new HashTag();
        for (int i = 0; i < tweets.size(); i++) {
            Tweet t = tweets.get(i);
            if (t.getDate().toLocalDate().equals(dia)) {
                for (int j = 0; j < t.getHashTags().size(); j++) {
                    HashTag h = t.getHashTags().get(j);
                    if (h.getText() != "f1") {
                        h.setUsosDia(h.getUsosDia()+1);
                    }
                    if (h.getUsosDia() > hTop.getUsosDia()) {
                        hTop = h;
                    }
                }
            }
        }
        return hTop;
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
