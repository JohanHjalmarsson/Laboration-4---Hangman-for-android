package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Displays if the player has solved the word or not and offers options to play again or return to main activity.
 * @author Johan Hjalmarsson
 */
public class YouWin extends AppCompatActivity {
    /**
     * static final String key to collect the secret word used in the game
     */
    public final static String theWord = "0";
    /**
     * static final String key to collect the amount of tries left
     */
    public final static String theInt = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_win);

        Intent intentX = getIntent();

        TextView secretWordView = (TextView) findViewById(R.id.secretWord);
        TextView triesLeftView = (TextView) findViewById(R.id.triesLeft);
        TextView winOrLooseTv = (TextView) findViewById(R.id.winOrLooseTV);

        String ett = "The secret word was: "+intentX.getStringExtra(theWord);
        String tva = "You had "+intentX.getStringExtra(theInt);
        String winOrLoose = intentX.getStringExtra(PlayLevelOne.winOrLoose);

        secretWordView.setText(ett);
        triesLeftView.setText(tva);
        winOrLooseTv.setText(winOrLoose);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.secondary_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Select options item in menu and start activity or method for that option
     * @param item
     * @return
     */
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

    /**
     * Starts the ChooseCategory activity
     * @param v
     */
    public void playAgain(View v) {
        Intent intent = new Intent(this, ChooseCategory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    /**
     * Starts the MainActivity activity
     * @param v
     */
    public void dontPlayAgain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

}
