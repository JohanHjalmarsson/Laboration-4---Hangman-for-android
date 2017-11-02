package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Class for playing the game. Takes the players choice of level and category (single player) and initialize the Game Class
 * or takes the players secret word (multiplayer) and carrys on the multiplayer.
 * @author Johan Hjalmarsson
 */
public class PlayLevelOne extends AppCompatActivity {
    Game game;
    WordLists wordLists = new WordLists();
    /**
     * static final String key for adding win or loose message to intent
     */
    public static final String winOrLoose = "3";
    private String levelChoice;
    private String categoryChoice;
    private Intent intent;
    private String[] playerList;
    private SharedPreferences myPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_level_one);

        intent = getIntent();
        categoryChoice = intent.getStringExtra(ChooseCategory.choiceCategory);
        myPreferences = getSharedPreferences(MultiPlayerInit.myPreferencesString, 0);
        int turn = myPreferences.getInt(MultiPlayerInit.turns, 0);

        if (categoryChoice.equals("MultiPlayer")) {
            playerList = new String[] {
                    myPreferences.getString(MultiPlayerInit.playerOneName, "PlayerName"),
                    myPreferences.getString(MultiPlayerInit.playerTwoName, "PlayerName"),
                    myPreferences.getString(MultiPlayerInit.playerThreeName, "PlayerName"),
                    myPreferences.getString(MultiPlayerInit.playerFourName, "PlayerName")};
            setTitle(playerList[turn]);
            SharedPreferences.Editor myEditor = myPreferences.edit();

            initMultiPlayer();


        }else {
            setTitle("Hangman");
            initGame();

        }


        TextView categoryView = (TextView) findViewById(R.id.categoryTextView);
        categoryView.setText("Category: "+game.getCategory());

        TextView textView = (TextView) findViewById(R.id.displayWord);
        textView.setText(game.printCharList());
    }

    /**
     * Initialize the single player game. Declares game by the users choice of category and level
     */
    public void initGame() {

        levelChoice = intent.getStringExtra(ChooseCategory.choiceLevel);

        TextView levelView = (TextView) findViewById(R.id.levelView);
        levelView.setText(levelChoice);

        game = new Game(wordLists.getWordList(categoryChoice, levelChoice));
        game.initCharList();
    }

    /**
     * Initialize the multiplayer game. Declares game with user secret word
     */
    public void initMultiPlayer() {
        game = new Game(intent.getStringExtra(PlayMultiPlayer.putSecretWord));
        game.initCharList();
    }

    /**
     * Collects the players input from guessBox, checks that its not empty, then uses the method takeTurn to continue the game
     * @param view
     */
    public void sendString(View view) {
        EditText guessBox = (EditText) findViewById(R.id.guessBox);
        String guess = guessBox.getText().toString();
        if (guess.isEmpty() || guess == null) {
            return;
        }
        takeTurn(guess);
        guessBox.setText(null);

    }

    /**
     * Takes the players guess and uses methods to validate and compare it to secret word. Controls hangman game images accordingly to players success
     * @param guess
     */
    public void takeTurn(String guess) {
        TextView errorBox = (TextView) findViewById(R.id.errorBox);
        errorBox.setVisibility(View.INVISIBLE);
        TextView textView = (TextView) findViewById(R.id.displayWord);
        TextView usedLetters = (TextView) findViewById(R.id.usedLetterBox);
        TextView triesLeft = (TextView) findViewById(R.id.triesLeftBox);
        RelativeLayout hangTheMan = (RelativeLayout) findViewById(R.id.hangManBox);

        if (!usedLetter(guess) && !toManyLetters(guess)) {
            boolean correct = game.compareWords(guess);
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

    /**
     * Checks if game is single or multi player. Checks if player has won or lost the game and then start the YouWin activity, adding String youLoose or youWin accordingly
     * to game outcome
     */
    public void winOrLoose() {
        String secretWord = game.getSecretWord();
        String triesLeft = game.getTriesLeftString();
        String youLoose = "You have lost the game!";
        String youWin = "You have won the game!";


        if (!categoryChoice.equals("MultiPlayer")){
            Intent intentWinOrLoose = new Intent(this, YouWin.class);
            intentWinOrLoose.putExtra(YouWin.theWord, secretWord);
            intentWinOrLoose.putExtra(YouWin.theInt, triesLeft);
            if (game.youLose()) {

                intentWinOrLoose.putExtra(winOrLoose, youLoose );
                startActivity(intentWinOrLoose);
                finish();

            }
            else if(game.youWin()) {
                intentWinOrLoose.putExtra(winOrLoose, youWin);
                startActivity(intentWinOrLoose);
                finish();
            }
        }
        else if(categoryChoice.equals("MultiPlayer")) {
            Intent intentMultiPlayer = new Intent(this, DisplayMultiPlayerWinner.class);
            intentMultiPlayer.putExtra(YouWin.theWord, secretWord);
            intentMultiPlayer.putExtra(YouWin.theInt, triesLeft);
            if (game.youLose()) {

                intentMultiPlayer.putExtra(winOrLoose, "Wrong!");
                startActivity(intentMultiPlayer);
                finish();

            }
            else if(game.youWin()) {
                intentMultiPlayer.putExtra(winOrLoose, "Congrats! You solved the word!");
                startActivity(intentMultiPlayer);
                finish();
            }
        }


    }

    /**
     * Display error in errorBox if player inputs a already used letter
     * @param s
     * @return
     */
    public boolean usedLetter(String s) {
        if (game.alreadyUsedLetter(s)) {
            TextView errorBox = (TextView) findViewById(R.id.errorBox);
            errorBox.setText("Letter already used!");
            errorBox.setVisibility(View.VISIBLE);
        }
        return game.alreadyUsedLetter(s);
    }

    /**
     * Display error in errorBox if player inputs to many letters
     * @param s
     * @return
     */
    public boolean toManyLetters(String s) {
        if (game.tooManyLetters(s)) {
            TextView errorBox = (TextView) findViewById(R.id.errorBox);
            errorBox.setText("To many letters!");
            errorBox.setVisibility(View.VISIBLE);
        }
        return game.tooManyLetters(s);
    }

}
