import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSV {

    private static final String driversFile = "src/main/resources/drivers.txt";
    private static final MyLinkedListImp<String> driversLinkedList = new MyLinkedListImp<>();
    private static final String csvRaw = "src/main/resources/f1_dataset.csv";
    private static final MyLinkedListImp<User> userLinkedList = new MyLinkedListImp<>();
    private static final MyLinkedListImp<Tweet> tweetLinkedList = new MyLinkedListImp<>();
    private static final MyLinkedListImp<HashTag> hashTagLinkedList = new MyLinkedListImp<>();

    public static void getDrivers() {
        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedList.add(line.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCsvInfo() {

    }
}
