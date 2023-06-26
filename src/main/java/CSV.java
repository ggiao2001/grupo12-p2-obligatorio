import static java.lang.Boolean.parseBoolean;
import static java.lang.Long.parseLong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.Interfaces.MyBinarySearchTree;
import uy.edu.um.prog2.adt.TADs.MyBinarySearchTreeImp;
import uy.edu.um.prog2.adt.TADs.MyHashTableImp;
import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;
import uy.edu.um.prog2.adt.TADs.hash.HashTable;
import uy.edu.um.prog2.adt.TADs.hash.table.MyHashTable;

public class CSV {

    private static final String driversFile = "src/main/resources/drivers.txt";
    public static final MyLinkedListImp<String> driversLinkedList = new MyLinkedListImp<>();
    public static final HashTable<String, User> userHashTable = new MyHashTable<>();
    public static final MyLinkedListImp<User> userLinkedList = new MyLinkedListImp<>();
    private static final String csvRaw = "src/main/resources/f1_dataset_test.csv";
    public static final MyLinkedListImp<Tweet> tweetLinkedList = new MyLinkedListImp<>();
    public static final MyBinarySearchTreeImp<LocalDateTimeWrapper,Tweet> tweetBST = new MyBinarySearchTreeImp<LocalDateTimeWrapper, Tweet>();
    //public static final MyLinkedListImp<HashTag> hashTagLinkedList = new MyLinkedListImp<>();
    private static final DateTimeFormatter FORMATTER_1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter FORMATTER_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");

    public static void getDrivers() {
        try (BufferedReader br = new BufferedReader(new FileReader(driversFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                driversLinkedList.add(line.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getCsvInfo() {
        String[] HEADERS = {"id", "user_name", "user_location", "user_description", "user_created", "user_followers", "user_friends", "user_favourites", "user_verified", "date", "text", "hashtags", "source", "is_retweet"};
        try (Reader in = new FileReader(csvRaw)) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);
            long userId = 1L;
            long hashTagId = 1L;

            for (CSVRecord record : records) {
                try {
                    // obtengo el nombre del user
                    String userName = record.get("user_name");

                    var userMatch = userHashTable.get(userName);

                    Tweet tweet = new Tweet();
                    // hago un array de los hashtags del tweet
                    String hashTags = record.get("hashtags");
                    String[] hashTagSplited = hashTags.replace("[", "").replace("]", "").replaceAll("\\s", "").split(",");
                    for (int i = 0; i < hashTagSplited.length; i++) {
                        HashTag hashTag = new HashTag(hashTagId, hashTagSplited[i]);
                        // si no existia lo agrego a la linkedList de hashTags

                        hashTagId++;

                        // lo agrego a la linkedList de hashtags del tweet
                        tweet.getHashTags().add(hashTag);
                    }

                    // creo el objeto de clase tweet
                    tweet.setId(parseLong(record.get("id")));
                    LocalDateTime date = parseDateTime(record.get("date"));
                    tweet.setDate(date);
                    LocalDateTimeWrapper dateWrapper = new LocalDateTimeWrapper(date);
                    tweet.setContent(record.get("text"));
                    tweet.setSource(record.get("source"));
                    tweet.setRetweet(parseBoolean(record.get("is_retweet")));

                    if (userMatch != null) {
                        userMatch.getTweets().add(tweet);
                        //userMatch.incrementTweetCount(); opcional si no tenes un size en tu lista, creo que no tenes
                        tweetLinkedList.add(tweet);
                        tweetBST.insert(dateWrapper, tweet);
                        if (date.isAfter(userMatch.getLastTweet())) {
                            userMatch.setLastTweet(date);
                            userMatch.setVerified(Boolean.parseBoolean(record.get("user_verified"))); // Actualizar el estado verificado si se encuentra un tweet más reciente
                        }

                    } else {
                        User user = new User();
                        user.setId(userId);
                        user.setName(userName);
                        user.setVerified(Boolean.parseBoolean(record.get("user_verified")));
                        user.setLastTweet(date);
                        user.getTweets().add(tweet);
                        user.setLocation(record.get("user_location"));
                        user.setDescription(record.get("user_description"));
                        user.setCreated(parseDateTime(record.get("user_created")));
                        user.setFollowers(Double.parseDouble(record.get("user_followers")));
                        user.setFriends(Double.parseDouble(record.get("user_friends")));
                        user.setFavourites(Double.parseDouble(record.get("user_favourites")));
                        userHashTable.put(userName, user);
                        userLinkedList.add(user);
                        tweetLinkedList.add(tweet);
                        tweetBST.insert(dateWrapper, tweet);
                        userId++;
                    }
                } catch (Exception ignored) {

                }
            }
        } catch (IOException e) {
            throw new RuntimeException("ERROR AL LEER EL CSV"); //Create una excepcion y ponele un mensaje
        }
    }

    public static LocalDateTime parseDateTime(String dateString) {
        try {
            return LocalDateTime.parse(dateString, FORMATTER_1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDateTime.parse(dateString, FORMATTER_2);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Invalid date format: " + dateString);
            }
        }
    }

    public static class LocalDateTimeWrapper implements Comparable<LocalDateTimeWrapper> {
        private LocalDateTime dateTime;

        public LocalDateTimeWrapper(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        @Override
        public int compareTo(LocalDateTimeWrapper other) {
            if (dateTime.isAfter(other.getDateTime())){
                return 1;
            }else if (dateTime.isBefore(other.getDateTime())){
                return -1;
            }else{
                return 0;
            }

        }
    }


}
