package com.example.firebase;

import java.util.Stack;
public class LeaderboardsSearched extends LeaderboardsUser  {
    private Boolean found;
    private Stack<LeaderboardsUser> stack = new Stack<LeaderboardsUser>();
    public LeaderboardsSearched(String s, Boolean found ,Stack<LeaderboardsUser> stack ) {
        super(s);
        this.found = found;
        this.stack = stack;
    }
    public Boolean IsValid(String name) // הפעולה בודקת אם השם משתמש שהוזן תקין
    {
        Boolean valid;
        String rn = name.replaceAll(" ", "");
        if ((rn.length() > 2 && rn.length() < 9) && rn.matches(".*[a-z].*"))
        {
            valid = true;
        }
        else
        {
            valid = false;
        }
        return valid;
    }
    public Boolean IsExist(String name) //  הפעולה בודקת אם השם משתמש קיים
    {
        found = false; // נניח והוא לא נמצא
        if (IsValid(name)) // בודק אם השם תקין לפני הבדיקה
        {
            while (!stack.isEmpty())
            {
                if (stack.peek().getS().equals(name))
                {
                    found = true;
                }
                stack.pop();
            }
        }
        return found;
    }

    public Boolean getFound() {
        return found;
    }
    public void setFound(Boolean found) {
        this.found = found;
    }
    public Stack<LeaderboardsUser> getStack() {
        return stack;
    }
    public void setStack(Stack<LeaderboardsUser> stack) {
        this.stack = stack;
    }
    @Override
    public String toString() {
        return "LeaderboardsSearched{" +
                "found=" + found +
                ", stack=" + stack +
                ", s='" + s + '\'' +
                '}';
    }
}
