package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

import java.time.LocalDateTime;

public class Tweet<T> {
    private long id;
    private LocalDateTime date;
    private String content;
    private String source;
    private boolean isRetweet;
    private MyLinkedListImp<HashTag> hashTags;

    public Tweet(long id, LocalDateTime date, String content, String source, boolean isRetweet) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.hashTags = new MyLinkedListImp<>();
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

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

//    public void setSource(String source) {
//        this.source = source;
//    }

    public boolean isRetweet() {
        return isRetweet;
    }

//    public void setRetweet(boolean retweet) {
//        isRetweet = retweet;
//    }

    public MyLinkedListImp<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(MyLinkedListImp<HashTag> hashTags) {
        this.hashTags = hashTags;
    }
}
