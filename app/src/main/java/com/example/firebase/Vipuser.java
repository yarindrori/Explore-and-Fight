package com.example.firebase;


public class Vipuser extends Users{
    private Boolean Access;
    public Vipuser(String mail, String password,int coins, int points,String username, Boolean Access)
    {
        super(mail,password,coins,points,username);
        this.Access = Access;
    }
    public Boolean getAccess() {
        return Access;
    }
    public void setAccess(Boolean access) {
        Access = access;
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
