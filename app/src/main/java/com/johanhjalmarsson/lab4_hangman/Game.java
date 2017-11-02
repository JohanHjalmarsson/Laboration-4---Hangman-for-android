package com.johanhjalmarsson.lab4_hangman;

import java.util.ArrayList;
import java.util.Random;

/**
 * Game class that contains variables and method to play the game.
 * @author Johan Hjalmarsson
 */
public class Game {

    private String[] theWords;
    private Random r = new Random();
    private String secretWord;
    private char[] charList;
    private int usedLettersIndex = 0;
    private char[] usedLetters = new char[29];
    private String category;
    private String level;

    private int triesLeft = 10;


    /**
     * Constructor for (singel player) Game
     * @param list
     */
    public Game(String[] list) {
        this.category = list[0];
        //this.level = level;
        theWords = new String[] {list[1], list[2], list[3], list[4], list[5]};
        secretWord  = theWords[r.nextInt(theWords.length)];
        charList  = new char[secretWord.length()];
    }

    /**
     * Constructor for (multiplayer) Game
     * @param playerWord
     */
    public Game (String playerWord) {
        this.category = "Multiplayer";
        secretWord = playerWord;
        charList = new char[secretWord.length()];
    }

    /**
     * Takes the lenght of the secretWord and adds "*" to each slot
     */
    public void initCharList() {

        for (int i = 0; i < charList.length; i++) {
            charList[i] = '*';
        }

    }

    /**
     * Takes the users guess and compare its to all the letters in secretWord.
     * If correct replaces "*" in charList to letter, else adds letter to used letters and subtracts 1 from triesLeft
     * @param guess
     * @return true if guess == any letter in secretWord, else false
     */
    public boolean compareWords(String guess) {
        char c = guess.charAt(0);
        boolean correct = false;
        for (int i = 0; i<secretWord.length();i++) {

            if (c == secretWord.charAt(i)) {
                charList[i] = secretWord.charAt(i);
                correct = true;
            }

        }
        if (!correct) {
            triesLeft--;
            addToUsedLetters(c);
            return false;

        }
        return true;
    }

    /**
     * Adds used letters to usedLetters (char[])
     * @param c
     */
    public void addToUsedLetters(char c) {
        usedLetters[usedLettersIndex] = c;
        usedLettersIndex++;
        usedLetters[usedLettersIndex] = ',';
        usedLettersIndex++;
    }

    /**
     *
     * Returns a String of the charList
     * @return
     */
    public String printCharList() {
        String string = String.valueOf(charList);
        return string;
    }

    /**
     * returns true if triesLeft == 0
     * @return
     */
    public boolean youLose() {

        return triesLeft == 0;
    }

    /**
     * Checks if there is any "*" left in charList. If not; return true, else false
     * @return
     */
    public boolean youWin() {
        boolean b = true;
        for (int i = 0; i<charList.length;i++) {
            if (charList[i] == '*') {
                b = false;
            }

        }
        return b;
    }

    /**
     * Returns a String of usedLetters
     * @return
     */
    public String getUsedLetters() {
        String string = String.valueOf(usedLetters);
        return string;
    }

    /**
     * returns a String of triesLeft
     * @return
     */
    public String getTriesLeftString() {
        String tries = Integer.toString(triesLeft);
        return tries + " attempts left";
    }

    /**
     * checks if users has inputet more than one letter. If yes; return true, else false
     * @param s
     * @return
     */
    public boolean tooManyLetters(String s) {
        if (s.length() > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if users input letter is already used. If yes; return true, else false.
     * @param s
     * @return
     */
    public boolean alreadyUsedLetter(String s) {
        for (int i=0;i<usedLetters.length;i++) {
            if (s.charAt(0) == usedLetters[i]) {
                return true;
            }
        }
        for (int i=0;i<charList.length;i++) {
            if (s.charAt(0) == charList[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if users guess is empty, if yes; return true, else false
     * @param s
     * @return
     */
    public boolean noInput(String s) {
        if(s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Getter for triesLeft
     * @return triesLeft
     */
    public int getTriesLeft() {
        return triesLeft;
    }

    /**
     * Getter for secretWord
     * @return secretWord
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Getter for category
     * @return category
     */
    public String getCategory() {
        return category;
    }

}
