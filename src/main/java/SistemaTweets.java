import uy.edu.um.prog2.adt.Interfaces.MyList;
import uy.edu.um.prog2.adt.Entities.*;
import uy.edu.um.prog2.adt.Interfaces.MyQueue;

import java.time.LocalDate;

public interface SistemaTweets {

    /**
     * @Return: Queue de los 10 Pilotos ACTIVOS más mencionados para un mes
     * @param mes que se quiere buscar
     */
    MyQueue<String> pilotosMasMencionadosMes(LocalDate mes);

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
