package com.johanhjalmarsson.lab4_hangman;

/**
 * Created by Johan on 2017-10-16.
 */

public class Player {
    private String Name;
    private int Score;
    private int amountOfGames;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getAmountOfGames() {
        return amountOfGames;
    }

    public void setAmountOfGames(int amountOfGames) {
        this.amountOfGames = amountOfGames;
    }
}
