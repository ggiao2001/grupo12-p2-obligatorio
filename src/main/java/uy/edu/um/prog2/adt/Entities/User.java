package uy.edu.um.prog2.adt.Entities;

import uy.edu.um.prog2.adt.TADs.MyLinkedListImp;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String name;
    private String location;
    private String description;
    private LocalDateTime created;
    private Double followers;
    private Double friends;
    private Double favourites;
    private Boolean verified;
    private LocalDateTime lastTweet;
    private MyLinkedListImp<Tweet> tweets;


    public User(long id, String name) {
        this.id = id;
        this.name = name;
        this.tweets = new MyLinkedListImp<>();
    }

    public User() {
        this.tweets = new MyLinkedListImp<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Double getFollowers() {
        return followers;
    }

    public void setFollowers(Double followers) {
        this.followers = followers;
    }

    public Double getFriends() {
        return friends;
    }

    public void setFriends(Double friends) {
        this.friends = friends;
    }

    public Double getFavourites() {
        return favourites;
    }

    public void setFavourites(Double favourites) {
        this.favourites = favourites;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getLastTweet() {
        return lastTweet;
    }

    public void setLastTweet(LocalDateTime lastTweet) {
        this.lastTweet = lastTweet;
    }

    public MyLinkedListImp<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(MyLinkedListImp<Tweet> tweets) {
        this.tweets = tweets;
    }

    public int tweetCount() {
        return tweets.size();
    }
}
