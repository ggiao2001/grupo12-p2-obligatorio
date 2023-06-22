import java.time.LocalDate;
import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;

public interface SistemaTweets {

    /**
     * @param month que se quiere buscar, year que se quiere buscar
     * @Return: Queue de los 10 Pilotos ACTIVOS más mencionados para un mes
     */
    MyQueue<String> pilotosMasMencionadosMes(int month, int year) throws FullHeapException, EmptyTreeException;

    /**
     * @Return: Queue de 15 Usuarios con más tweets con:
    **/
    MyQueue<User> usuariosMasTweets();

    /**
     * @Return: Integer con la cantidad de Hashtags para un dia
     * @Parameter dia que se quiere buscar
     **/
    int cantidadHashtags(LocalDate dia);

    /**
     * @Return: Hashtag mas usado en un dia
     * @Parameter dia que se quiere buscar
     * SIN CONTAR #F1
     **/
    HashTag hashtagMasUsado(LocalDate dia);


    /**
     * @Return: Queue de 7 Usuarios con más favoritos con:
     **/
    MyQueue<User> usuariosMasFavoritos();


    /**
     * @Return: Cantidad de Veces que fue mencionada una frase o palabra en tweets
     * @Parameter frase utilizada
     **/
    int cantidadTweetsPalabra(String frase);




}
