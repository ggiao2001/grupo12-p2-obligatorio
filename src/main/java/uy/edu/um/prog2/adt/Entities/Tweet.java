package uy.edu.um.prog2.adt.Entities;

import java.time.LocalDateTime;
import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

public class Tweet {
    private long id;
    private LocalDateTime date;
    private String content;
    private String source;
    private boolean isRetweet;
    private MyLinkedListImp<HashTag> hashTags;

    public Tweet(long id, LocalDateTime date, String content, String source, boolean isRetweet, MyLinkedListImp hashtags) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.hashTags = new MyLinkedListImp<>();
    }

    public Tweet() {
        this.hashTags = new MyLinkedListImp<>();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public MyLinkedListImp<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(MyLinkedListImp<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
