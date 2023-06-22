import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Interfaces.MyList;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

import java.time.LocalDate;

public class SistemaTweetsImp implements SistemaTweets{
    MyList<User> usuarios = CSV.userLinkedList;
    MyLinkedListImp<Tweet>tweets = CSV.tweetLinkedList;
    MyLinkedListImp<HashTag> hashtags = CSV.hashTagLinkedList;
    MyLinkedListImp<String> pilotos = CSV.driversLinkedList;

    @Override
    public MyQueue<String> pilotosMasMencionadosMes(LocalDate mes) {
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
