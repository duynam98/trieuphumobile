package com.duynam.ailatrieuphu.model;

public class User {

    public String email;
    public String username;
    public String photo;
    public long score;
    public String level;
    public String timestamp;

    public User() {
    }

    public User(String email, String username, String photo, long score, String level, String timestamp) {
        this.email = email;
        this.username = username;
        this.photo = photo;
        this.score = score;
        this.level = level;
        this.timestamp = timestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
