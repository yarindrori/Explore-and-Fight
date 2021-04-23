package com.example.firebase;


public class Vipuser extends Users{
    private Boolean Access;
    public Vipuser(String mail, String password,int coins, int points,String username, Boolean Access)
    {
        super(mail,password,coins,points,username);
        this.Access = Access;
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }
    @Override
    public String getMail() {
        return super.getMail();
    }
    @Override
    public void setMail(String mail) {
        super.setMail(mail);
    }
    @Override
    public String getPassword() {
        return super.getPassword();
    }
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }
    @Override
    public int getCoins() {
        return super.getCoins();
    }
    @Override
    public void setCoins(int coins) {
        super.setCoins(coins);
    }
    @Override
    public int getPoints() {
        return super.getPoints();
    }
    @Override
    public void setPoints(int points) {
        super.setPoints(points);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
