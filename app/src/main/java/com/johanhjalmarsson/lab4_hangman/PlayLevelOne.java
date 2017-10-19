package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class PlayLevelOne extends AppCompatActivity {
    Game game;
    WordLists wordLists = new WordLists();
    public static final String winOrLoose = "3";

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.secondary_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.infoButton):
                Intent intent2 = new Intent(this, About.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void initGame() {
        Intent intent = getIntent();
        String categoryChoice = intent.getStringExtra(ChooseCategory.choiceCategory);
        String levelChoice = intent.getStringExtra(ChooseCategory.choiceLevel);

        TextView levelView = (TextView) findViewById(R.id.levelView);
        levelView.setText(levelChoice);

        game = new Game(wordLists.getWordList(categoryChoice, levelChoice));
        game.initCharList();
    }
    public void sendString(View view) {
        EditText guessBox = (EditText) findViewById(R.id.guessBox);
        String guess = guessBox.getText().toString();
        if (guess.isEmpty() || guess == null) {
            return;
        }
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
        String youLoose = "You have lost the game!";
        String youWin = "You have won the game!";
        if (game.youLose()) {
            Intent intentLoose = new Intent(this, YouWin.class);
            intentLoose.putExtra(winOrLoose, youLoose );
            intentLoose.putExtra(WinnerOrLooser.theWord, secretWord);
            intentLoose.putExtra(WinnerOrLooser.theInt, triesLeft);

            startActivity(intentLoose);
            finish();

        }
        else if(game.youWin()) {
            Intent intentWin = new Intent(this, YouWin.class);
            intentWin.putExtra(winOrLoose, youWin);
            intentWin.putExtra(YouWin.theWord, secretWord);
            intentWin.putExtra(YouWin.theInt, triesLeft);

            startActivity(intentWin);
            finish();
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
