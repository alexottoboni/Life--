package com.lifemm.game;

public class Score {
    private String name;
    private int scoreValue;

    public Score(String name, int score) {
        this.name = name;
        this.scoreValue = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return scoreValue;
    }

    @Override
    public String toString() {
        return name + ": " + scoreValue;
    }
}