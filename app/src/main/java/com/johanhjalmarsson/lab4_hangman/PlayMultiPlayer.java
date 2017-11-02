package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Class for keeping track of turns (multiplayer) and collecting secret words from the players
 * @Author: Johan Hjalmarsson
 */
public class PlayMultiPlayer extends AppCompatActivity {
    private SharedPreferences myPreferences;
    private String[] playerList;
    private String secretWord;
    private int amountOfPlayers;
    private TextView playerGiveWordView;
    private TextView playerGuessWordView;
    private TextView whenReady;
    private TextView questionMark;
    private ProgressBar progress;
    private Button playButton;
    private Button okButton;
    /**
     * static final String key for secret word given by player
     */
    public static final String putSecretWord = "MultiPlayer Secret Word";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_multi_player);
        setTitle("Multiplayer");

        myPreferences = getSharedPreferences(MultiPlayerInit.myPreferencesString, 0);
        amountOfPlayers = myPreferences.getInt(MultiPlayerInit.amountOfPlayers, 2);
        playerList = new String[] {
                myPreferences.getString(MultiPlayerInit.playerOneName, "PlayerName"),
                myPreferences.getString(MultiPlayerInit.playerTwoName, "PlayerName"),
                myPreferences.getString(MultiPlayerInit.playerThreeName, "PlayerName"),
                myPreferences.getString(MultiPlayerInit.playerFourName, "PlayerName")};

        playerGiveWordView = (TextView) findViewById(R.id.playerGiveWord);
        playerGuessWordView = (TextView) findViewById(R.id.playerGuessWord);
        whenReady = (TextView) findViewById(R.id.whenReady);
        playButton = (Button) findViewById(R.id.playButton);
        okButton = (Button) findViewById(R.id.okButton);
        questionMark = (TextView) findViewById(R.id.questionMark);
        progress = (ProgressBar) findViewById(R.id.progressBar);


        playerGuessWordView.setVisibility(View.INVISIBLE);
        whenReady.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.INVISIBLE);
        questionMark.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.INVISIBLE);

        playerTurn();
    }

    /**
     * When OK button is clicked, collects players given secret word for the next player.
     * Change the activitys layout for the next player to start the game
     * @param view
     */
    public void onClickOkButton(View view) {
        EditText secretWordText = (EditText) findViewById(R.id.editTextSecretWord);
        TextView secretInfo = (TextView) findViewById(R.id.secretInfo);
        secretWord = secretWordText.getText().toString();

        playerGiveWordView.setVisibility(View.INVISIBLE);
        secretWordText.setVisibility(View.INVISIBLE);
        secretInfo.setVisibility(View.INVISIBLE);
        okButton.setVisibility(View.INVISIBLE);
        questionMark.setVisibility(View.VISIBLE);

        playerGuessWordView.setVisibility(View.VISIBLE);
        whenReady.setVisibility(View.VISIBLE);
        playButton.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);




    }

    /**
     * When Play button is clicked, start the PlayLevelOne activity
     * @param view
     */
    public void onClickPlayButton(View view) {
        Intent intent = new Intent(this, PlayLevelOne.class);
        intent.putExtra(putSecretWord, secretWord);
        intent.putExtra(ChooseCategory.choiceCategory, "MultiPlayer");

        startActivity(intent);
        finish();
    }

    /**
     * Keeps count on amount of turns played and which player is next
     */
    public void playerTurn() {
        int turn = myPreferences.getInt(MultiPlayerInit.turns, 0);
        int turnZero = 0;
        amountOfPlayers = myPreferences.getInt(MultiPlayerInit.amountOfPlayers, 0);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        playerGiveWordView.setText(playerList[turn]);
        if (turn == amountOfPlayers-1) {
            playerGuessWordView.setText(playerList[0]);
            myEditor.putInt(MultiPlayerInit.turns, turnZero);
            myEditor.apply();
        }else {
            playerGuessWordView.setText(playerList[turn+1]);
            myEditor.putInt(MultiPlayerInit.turns, turn+1);
            myEditor.apply();
        }
    }
}
