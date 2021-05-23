package com.example.firebase;

public class Vipuser extends Users{
    private Boolean access;
    public Vipuser(String mail, String password,int coins, int points,String username,Boolean access)
    {
        super(mail,password,coins,points,username);
        this.access = access;
    }
    public Boolean getAccess() {
        return access;
    }
    public void setAccess(Boolean access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Vipuser{" +
                "access=" + access +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", coins=" + coins +
                ", points=" + points +
                ", access=" + access +
                '}';
    }
}
