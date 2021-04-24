package com.example.firebase;

import java.util.Stack;

public class LeaderboardsClass {
    private Stack<String> s;
    private Stack<String> svip;
    public LeaderboardsClass(Stack<String> s, Stack<String> svip)
    {
        this.s = s;
        this.svip = svip;
    }
    public Stack<String> getS() {
        return s;
    }
    public void setS(Stack<String> s) {
        this.s = s;
    }
    public Stack<String> getSvip() {
        return svip;
    }
    public void setSvip(Stack<String> svip) {
        this.svip = svip;
    }
}
