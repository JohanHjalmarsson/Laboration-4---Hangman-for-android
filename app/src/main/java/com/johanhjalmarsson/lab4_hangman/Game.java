package com.johanhjalmarsson.lab4_hangman;

import java.util.ArrayList;
import java.util.Random;

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

    public Game() {

    }

    public Game(String category, String one, String two, String three, String four, String five) {
        this.category = category;
        //this.level = level;
        theWords = new String[] {one, two, three, four, five};
        secretWord  = theWords[r.nextInt(theWords.length)];
        charList  = new char[secretWord.length()];
    }
    
    public void initCharList() {

        for (int i = 0; i < charList.length; i++) {
            charList[i] = '*';
        }

    }

    public boolean compareWords(String s) {
        char c = s.charAt(0);
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
    // fixa en stiligare lösning här för att få mellanslag och komma mellan bokstäver!
    public void addToUsedLetters(char c) {
        usedLetters[usedLettersIndex] = c;
        usedLettersIndex++;
        usedLetters[usedLettersIndex] = ',';
        usedLettersIndex++;
    }


    public String printCharList() {
        String string = String.valueOf(charList);
        return string;
    }
    public boolean youLose() {

        return triesLeft == 0;
    }
    public boolean youWin() {
        boolean b = true;
        for (int i = 0; i<charList.length;i++) {
            if (charList[i] == '*') {
                b = false;
            }

        }
        return b;
    }
    public String getUsedLetters() {
        String string = String.valueOf(usedLetters);
        return string;
    }

    public String getTriesLeftString() {
        String tries = Integer.toString(triesLeft);
        return tries + " attempts left";
    }
    public boolean tooManyLetters(String s) {
        if (s.length() > 1) {
            return true;
        } else {
            return false;
        }
    }
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

    public int getTriesLeft() {
        return triesLeft;
    }
    public String getSecretWord() {
        return secretWord;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }
}
