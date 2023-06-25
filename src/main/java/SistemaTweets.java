import java.time.LocalDate;
import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Piloto;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Exceptions.EmptyTreeException;
import uy.edu.um.prog2.adt.Exceptions.FullHeapException;
import uy.edu.um.prog2.adt.Exceptions.OutOfBoundsException;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;
import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

public interface SistemaTweets {

    /**
     * @param month que se quiere buscar, year que se quiere buscar
     * @Return: Queue de los 10 Pilotos ACTIVOS más mencionados para un mes
     */
    MyQueue<Piloto> pilotosMasMencionadosMes(int month, int year) throws FullHeapException, EmptyTreeException;

    /**
     * @Return: Queue de 15 Usuarios con más tweets con:
    **/
    void usuariosMasTweets() throws OutOfBoundsException;

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
    void usuariosMasFavoritos() throws OutOfBoundsException;


    /**
     * @Return: Cantidad de Veces que fue mencionada una frase o palabra en tweets
     * @Parameter frase utilizada
     **/
    int cantidadTweetsPalabra(String frase);




}
