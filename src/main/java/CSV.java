import org.apache.commons.csv.*;
import uy.edu.um.prog2.adt.Entities.HashTag;
import uy.edu.um.prog2.adt.Entities.Tweet;
import uy.edu.um.prog2.adt.Entities.User;
import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class CSV {

    private static final String driversFile = "src/main/resources/drivers.txt";
    public static final MyLinkedListImp<String> driversLinkedList = new MyLinkedListImp<>();
    private static final String csvRaw = "src/main/resources/f1_dataset.csv";
    public static final MyLinkedListImp<User> userLinkedList = new MyLinkedListImp<>();
    public static final MyLinkedListImp<Tweet> tweetLinkedList = new MyLinkedListImp<>();
    public static final MyLinkedListImp<HashTag> hashTagLinkedList = new MyLinkedListImp<>();
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

    public static void getCsvInfo() throws IOException {
        String[] HEADERS = {"id","user_name", "user_location", "user_description", "user_created", "user_followers", "user_friends", "user_favourites", "user_verified", "date", "text", "hashtags", "source", "is_retweet"};
        try (Reader in = new FileReader("src/main/resources/datasetSanti.csv")) {
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(HEADERS)
                    .setSkipHeaderRecord(true)
                    .build();

            Iterable<CSVRecord> records = csvFormat.parse(in);

            // defino ids para user y hashtag que se generan automatico
            long userId = 1L;
            long hashTagId = 1L;

            for (CSVRecord record : records) {

                // creo un objeto user de clase user
                String userName = record.get("user_name");
                User user = new User(userId,userName);

                // creo el objeto de clase tweet
                Long id = parseLong(record.get("id"));
                LocalDateTime date = parseDateTime(record.get("date"));
                String text = record.get("text");
                String source = record.get("source");
                Boolean isRetweet = parseBoolean(record.get("is_retweet"));
                Tweet tweet = new Tweet(id,date,text,source,isRetweet);
                user.getTweets().add(tweet);
                tweetLinkedList.add(tweet);

                // hago un array de los hashtags del tweet
                String hashTags = record.get("hashtags");
                String[] hashTagSplited = hashTags.replace("[", "").replace("]", "").replaceAll("\\s", "").split(",");
                for (int i = 0; i < hashTagSplited.length; i++) {
                    HashTag hashTag = new HashTag(hashTagId, hashTagSplited[i]);
                    // si no existia lo agrego a la linkedList de hashTags
                    if (hashTagLinkedList.contains(hashTag) == false) {
                        hashTagLinkedList.add(hashTag);
                        hashTagId++;
                    }
                    // lo agrego a la linkedList de hashtags del tweet
                    tweet.getHashTags().add(hashTag);
                }

                if (userLinkedList.contains(user)) {
                    // si contiene al user revisar cual es el ultimo tweet y si es actualizar la info
                    if (tweet.getDate().isAfter(user.getLastTweet())) {
                        String userLocation = record.get("user_location");
                        String userDescription = record.get("user_description");
                        LocalDateTime userCreated = parseDateTime(record.get("user_created"));
                        Double userFollowers = parseDouble(record.get("user_followers"));
                        Double userFriends = parseDouble(record.get("user_friends"));
                        Double userFavourites = parseDouble(record.get("user_favourites"));
                    }
                } else {
                    userLinkedList.add(user);
                    userId++;
                }

            }
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
}
