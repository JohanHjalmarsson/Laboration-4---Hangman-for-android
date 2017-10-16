package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class PlayLevelOne extends AppCompatActivity {
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level_one);

        initGame();



        TextView categoryView = (TextView) findViewById(R.id.categoryTextView);
        categoryView.setText("Category: "+game.getCategory());

        TextView textView = (TextView) findViewById(R.id.displayWord);
        textView.setText(game.printCharList());

    }

    public void initGame() {
        Intent intent = getIntent();
        String categoryChoice = intent.getStringExtra(ChooseCategory.choiceCategory);
        String levelChoice = intent.getStringExtra(ChooseCategory.choiceLevel);

        TextView levelView = (TextView) findViewById(R.id.levelView);
        levelView.setText(levelChoice);

        switch (levelChoice) {
            case "Level 1":
                initLevelOne(categoryChoice);
                break;
            case "Level 2":
                initLevelTwo(categoryChoice);
                break;
            case "Level 3":
                initLevelThree(categoryChoice);
        }

    }

    public void initLevelThree(String categoryChoice) {
        switch (categoryChoice) {
            case "Animals":
                game = new Game("Animals", "crocodile", "albatross", "chimpanzee", "centipede", "hippopotamus");
                break;
            case "Foods":
                game = new Game("Foods", "entrecote", "hamburger", "carbonara", "pineapple", "sandwich");
                break;
            case "Professions":
                game = new Game("Professions", "chef", "driver", "nanny", "nurse", "chef");
                break;
            case "Names":
                game = new Game("Names", "john", "eve", "pam", "adam", "rudy");
                break;
            case "Countries":
                game = new Game("Countries", "sweden", "denmark", "germany", "finland", "norway");
                break;
            case "Drinks":
                game = new Game("Drinks", "milk", "juice", "beer", "wine", "water");
                break;
            case "Category":
                game = new Game("Foods", "apple", "pear", "kiwi", "melon", "orange");
                break;
        }
        game.initCharList();
    }

    public void initLevelTwo(String categoryChoice) {
        switch (categoryChoice) {
            case "Animals":
                game = new Game("Animals", "cat", "dog", "bird", "pig", "cow");
                break;
            case "Foods":
                game = new Game("Foods", "apple", "pear", "kiwi", "melon", "orange");
                break;
            case "Professions":
                game = new Game("Professions", "chef", "driver", "nanny", "nurse", "chef");
                break;
            case "Names":
                game = new Game("Names", "john", "eve", "pam", "adam", "rudy");
                break;
            case "Countries":
                game = new Game("Countries", "sweden", "denmark", "germany", "finland", "norway");
                break;
            case "Drinks":
                game = new Game("Drinks", "milk", "juice", "beer", "wine", "water");
                break;
            case "Category":
                game = new Game("Foods", "apple", "pear", "kiwi", "melon", "orange");
                break;
        }
        game.initCharList();
    }

    public void initLevelOne(String categoryChoice) {
        switch (categoryChoice) {
            case "Animals":
                game = new Game("Animals", "cat", "dog", "bird", "pig", "cow");
                break;
            case "Foods":
                game = new Game("Foods", "apple", "pear", "kiwi", "melon", "orange");
                break;
            case "Professions":
                game = new Game("Professions", "chef", "driver", "nanny", "nurse", "chef");
                break;
            case "Names":
                game = new Game("Names", "john", "eve", "pam", "adam", "rudy");
                break;
            case "Countries":
                game = new Game("Countries", "sweden", "denmark", "germany", "finland", "norway");
                break;
            case "Drinks":
                game = new Game("Drinks", "milk", "juice", "beer", "wine", "water");
                break;
            case "Category":
                game = new Game("Foods", "apple", "pear", "kiwi", "melon", "orange");
                break;
        }
        game.initCharList();
    }

    public void sendString(View view) {
        EditText guessBox = (EditText) findViewById(R.id.guessBox);
        String guess = guessBox.getText().toString();

        takeTurn(guess);
        guessBox.setText(null);

    }
    public void takeTurn(String s) {
        TextView errorBox = (TextView) findViewById(R.id.errorBox);
        errorBox.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.displayWord);
        TextView usedLetters = (TextView) findViewById(R.id.usedLetterBox);
        TextView triesLeft = (TextView) findViewById(R.id.triesLeftBox);
        RelativeLayout hangTheMan = (RelativeLayout) findViewById(R.id.hangManBox);


        if (!usedLetter(s) && !toManyLetters(s)) {
            boolean correct = game.compareWords(s);
            if (correct) {
                textView.setText(game.printCharList());
            }
            else if (!correct) {
                usedLetters.setText(game.getUsedLetters());
                triesLeft.setText(game.getTriesLeftString());
                switch (game.getTriesLeft()) {
                    case 0:
                        hangTheMan.setBackgroundResource(R.drawable.hang0);
                        break;
                    case 1:
                        hangTheMan.setBackgroundResource(R.drawable.hang1);
                        break;
                    case 2:
                        hangTheMan.setBackgroundResource(R.drawable.hang2);
                        break;
                    case 3:
                        hangTheMan.setBackgroundResource(R.drawable.hang3);
                        break;
                    case 4:
                        hangTheMan.setBackgroundResource(R.drawable.hang4);
                        break;
                    case 5:
                        hangTheMan.setBackgroundResource(R.drawable.hang5);
                        break;
                    case 6:
                        hangTheMan.setBackgroundResource(R.drawable.hang6);
                        break;
                    case 7:
                        hangTheMan.setBackgroundResource(R.drawable.hang7);
                        break;
                    case 8:
                        hangTheMan.setBackgroundResource(R.drawable.hang8);
                        break;
                    case 9:
                        hangTheMan.setBackgroundResource(R.drawable.hang9);
                        break;
                }

            }
            winOrLoose();

        }

    }

    public void winOrLoose() {
        String secretWord = game.getSecretWord();
        String triesLeft = game.getTriesLeftString();
        if (game.youLose()) {
            Intent intentLoose = new Intent(this, YouLoose.class);
            intentLoose.putExtra(YouLoose.theWord, secretWord);
            intentLoose.putExtra(YouLoose.theInt, triesLeft);
            startActivity(intentLoose);
        }
        else if(game.youWin()) {
            Intent intentWin = new Intent(this, YouWin.class);
            intentWin.putExtra(YouWin.theWord, secretWord);
            intentWin.putExtra(YouWin.theInt, triesLeft);
            startActivity(intentWin);
        }
    }
    public boolean usedLetter(String s) {
        if (game.alreadyUsedLetter(s)) {
            TextView errorBox = (TextView) findViewById(R.id.errorBox);
            errorBox.setText("Letter already used!");
            errorBox.setVisibility(View.VISIBLE);
        }

        return game.alreadyUsedLetter(s);
    }

    public boolean toManyLetters(String s) {
        if (game.tooManyLetters(s)) {
            TextView errorBox = (TextView) findViewById(R.id.errorBox);
            errorBox.setText("To many letters!");
            errorBox.setVisibility(View.VISIBLE);
        }
        return game.tooManyLetters(s);
    }

}
