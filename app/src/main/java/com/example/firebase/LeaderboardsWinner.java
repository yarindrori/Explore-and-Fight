package com.example.firebase;

import java.util.Stack;

public class LeaderboardsWinner extends LeaderboardsClass  {
    private Boolean winner;
    public LeaderboardsWinner(Stack<String> s, Stack<String> svip, Boolean winner) {
        super(s, svip);
        this.winner = winner;
    }
    public Boolean getWinner() {
        return winner;
    }
    public void setWinner(Boolean winner) {
        this.winner = winner;
    }
    public Boolean IsWinner(String name) // פעולה שמקבלת שם ובודקת אם הוא מנצח (מקום 1) -
    {
        if (name.equals(super.getS().peek()))
        {
           winner = true; // אם הוא מקום 1 אז הוא winner
        }
        return winner; // אם כן מחזירה true ואם לא אז false
    }
}
