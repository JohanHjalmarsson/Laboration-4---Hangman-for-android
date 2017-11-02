package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Class for displaying if player managed to solve the word or not.
 * Displays button for next player to enter word.
 * In the future, this class should be modified to have a score board, amount
 * of turns and eventually a end of the game with appropriate user options included.
 * @author Johan Hjalmarsson
 */
public class DisplayMultiPlayerWinner extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_multi_player_winner);
        setTitle("Multiplayer");

        Intent intentX = getIntent();

        TextView secretWordView = (TextView) findViewById(R.id.secretWord);
        TextView triesLeftView = (TextView) findViewById(R.id.triesLeft);
        TextView winOrLooseTv = (TextView) findViewById(R.id.winOrLooseTV);

        String ett = "The secret word was: "+intentX.getStringExtra(YouWin.theWord);
        String tva = "You had "+intentX.getStringExtra(YouWin.theInt);
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
     * Starts the PlayMultiPlayer acticity
     * @param view
     */
    public void onClickNextPlayer(View view) {
        Intent intent = new Intent(this, PlayMultiPlayer.class);
        startActivity(intent);
    }

    /**
     * Starts the ChooseCategory activity, then finishes this activity
     * @param v
     */
    public void playAgain(View v) {
        Intent intent = new Intent(this, ChooseCategory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    /**
     * Returns to (starts) MainActivity activity, then finishes this activity
     * @param v
     */
    public void dontPlayAgain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

}

