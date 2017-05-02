package com.example.ian.twixter;

/**
 * Created by ian on 4/27/17.
 */

public class Newsitem {
    private String username;
    private String feed;

    public Newsitem (String username, String feed) {
        super();
        this.username = username;
        this.feed = feed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getFeed() {
        return feed;
    }

}
