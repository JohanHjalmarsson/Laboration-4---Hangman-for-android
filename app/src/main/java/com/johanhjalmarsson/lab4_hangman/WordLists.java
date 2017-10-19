package com.johanhjalmarsson.lab4_hangman;

import java.util.ArrayList;

/**
 * Created by Johan on 2017-10-16.
 */

public class WordLists {
    static ArrayList<String[]> wordList = new ArrayList<>();

    private void initList(String level) {
        switch (level) {
            case "Level 1":
                wordList.add(0, new String[] {"Animals", "cat", "dog", "bird", "pig", "cow"});
                wordList.add(1, new String[] {"Foods", "apple", "pear", "kiwi", "melon", "orange"});
                wordList.add(2, new String[] {"Professions", "chef", "driver", "nanny", "nurse", "chef"});
                wordList.add(3, new String[] {"Names", "john", "eve", "pam", "adam", "rudy"});
                wordList.add(4, new String[] {"Countries", "sweden", "denmark", "germany", "finland", "norway"});
                wordList.add(5, new String[] {"Drinks", "milk", "juice", "beer", "wine", "water"});
                break;
            case "Level 2":
                wordList.add(0, new String[] {"Animals", "horse", "rabbit", "delphine", "camel", "dingo"});
                wordList.add(1, new String[] {"Foods", "hamburger", "cereal", "orange", "potato", "tomato"});
                wordList.add(2, new String[] {"Professions", "doctor", "musician", "lawyer", "drummer", "programmer"});
                wordList.add(3, new String[] {"Names", "felix", "maria", "johan", "cedric", "harry"});
                wordList.add(4, new String[] {"Countries", "belgium", "holland", "greece", "somalia", "estonia"});
                wordList.add(5, new String[] {"Drinks", "whiskey", "cider", "vodka", "bourbon", "soda"});
                break;
            case "Level 3":
                wordList.add(0, new String[] {"Animals", "crocodile", "albatross", "chimpanzee", "centipede", "hippopotamus"});
                wordList.add(1, new String[] {"Foods", "entrecote", "hamburger", "carbonara", "pineapple", "sandwich"});
                wordList.add(2, new String[] {"Professions", "accountant", "director", "dishwasher", "woodworker", "chauffeur"});
                wordList.add(3, new String[] {"Names", "christian", "pauline", "lisabeth", "abraham", "georges"});
                wordList.add(4, new String[] {"Countries", "singapore", "macedonia", "kazakhstan", "switzerland", "guatemala"});
                wordList.add(5, new String[] {"Drinks", "manhattan", "champagne", "curacao", "cosmopolitan", "brewery"});
                break;
            default:
                wordList.add(0, new String[] {"Animals", "cat", "dog", "bird", "pig", "cow"});
                wordList.add(1, new String[] {"Foods", "apple", "pear", "kiwi", "melon", "orange"});
                wordList.add(2, new String[] {"Professions", "chef", "driver", "nanny", "nurse", "chef"});
                wordList.add(3, new String[] {"Names", "john", "eve", "pam", "adam", "rudy"});
                wordList.add(4, new String[] {"Countries", "sweden", "denmark", "germany", "finland", "norway"});
                wordList.add(5, new String[] {"Drinks", "milk", "juice", "beer", "wine", "water"});
                break;
        }
    }

    public String[] getWordList(String category, String level) {
        initList(level);

        switch (category) {
            case "Animals":
                return wordList.get(0);
            case "Foods":
                return wordList.get(1);
            case "Professions":
                return wordList.get(2);
            case "Names":
                return wordList.get(3);
            case "Countries":
                return wordList.get(4);
            case "Drinks":
                return wordList.get(5);
        }
        return wordList.get(0);
    }
}
