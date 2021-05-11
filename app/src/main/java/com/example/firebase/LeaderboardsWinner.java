package com.example.firebase;

import java.util.Stack;

public class LeaderboardsWinner extends LeaderboardsUser  {
    private Boolean winner;
    public LeaderboardsWinner(String s, Integer s2, Boolean winner) {
        super(s, s2);
        this.winner = winner;
    }
    public Boolean getWinner() {
        return winner;
    }
    public void setWinner(Boolean winner) {
        this.winner = winner;
    }


    @Override
    public String toString() {
        return "LeaderboardsWinner{" +
                "winner=" + winner +
                '}';
    }
}
