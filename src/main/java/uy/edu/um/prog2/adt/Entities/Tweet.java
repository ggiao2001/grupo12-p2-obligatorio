package uy.edu.um.prog2.adt.Entities;

public class Tweet<T> {
    private long id;
    private String content;
    private String source;
    private boolean isRetweet;

    public Tweet(long id, String content, String source, boolean isRetweet) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
    }

    public long getId() {
        return id;
    }

//    public void setId(long id) {
//        this.id = id;
//    }

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
}
