package com.example.firebase;


public class LeaderboardsUser {
    protected String s;
    protected Integer s2;
    public LeaderboardsUser(String s, Integer s2)
    {
        this.s = s;
        this.s2 = s2;
    }
    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }
    public Integer getS2() {
        return s2;
    }
    public void setS2(Integer s2) {
        this.s2 = s2;
    }
    @Override
    public String toString() {
        return "LeaderboardsUser{" +
                "s='" + s + '\'' +
                ", s2=" + s2 +
                '}';
    }
}
