package com.example.firebase;

public class LeaderboardsUser {
    protected String s;// שם משתמש

    public LeaderboardsUser(String s)
    {
        this.s = s;
    }
    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }
    @Override
    public String toString() {
        return "LeaderboardsUser{" +
                "s='" + s + '\'' +
                '}';
    }
}
