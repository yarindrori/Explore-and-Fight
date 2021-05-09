package com.example.firebase;

public class Users {
    protected String mail, password, username;
    protected int coins ,points;
    Users(){}

    public Users(String mail, String password, int coins, int points, String username) {
        this.mail = mail;
        this.password = password;
        this.coins = coins;
        this.points = points;
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getCoins() {
        return coins;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Users{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", coins=" + coins +
                ", points=" + points +
                '}';
    }
}
