package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class MultiPlayerInit extends AppCompatActivity {

    private String choice;
    public static final String theChoice = "m0";
    private EditText editText;
    private EditText playerOne = (EditText) findViewById(R.id.playerOneName);
    private EditText playerTwo = (EditText) findViewById(R.id.playerTwoName);
    private EditText playerThree = (EditText) findViewById(R.id.playerThreeName);
    private EditText playerFour = (EditText) findViewById(R.id.playerFourName);
    public static final String myPreferencesString = "tjo!";
    public static final String playerOneName = "Player Name";
    public static final String playerTwoName = "Player Name";
    public static final String playerThreeName = "Player Name";
    public static final String playerFourName = "Player Name";
    public static final String amountOfPlayers = "Amount of players";
    private int amountOfPlayersInt = 2;
    private SharedPreferences myPreferences = getSharedPreferences(myPreferencesString, 0);
    private boolean radio2Checked = false;
    private boolean radio3Checked = false;
    private boolean radio4Checked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_init);

        playerFour = (EditText) findViewById(R.id.playerFourName);
        playerThree = (EditText) findViewById(R.id.playerThreeName);

        playerThree.setVisibility(View.INVISIBLE);
        playerFour.setVisibility(View.INVISIBLE);







    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()){
            case (R.id.threePlayers):
                if (checked) {
                    radio2Checked = true;
                    amountOfPlayersInt = 3;
                    threePlayers();
                }
                break;
            case (R.id.fourPlayers):
                if (checked) {
                    radio3Checked = true;
                    amountOfPlayersInt = 4;
                    fourPlayers();
                }
                break;
            case (R.id.twoPlayers):
                if (checked) {
                    radio4Checked = true;
                    amountOfPlayersInt = 2;
                    twoPlayers();
                }
                break;
        }
    }

    public void twoPlayers() {
        playerThree.setVisibility(View.INVISIBLE);
        playerFour.setVisibility(View.INVISIBLE);
    }

    public void threePlayers() {
        playerThree = (EditText) findViewById(R.id.playerThreeName);
        playerThree.setVisibility(View.VISIBLE);
    }
    public void fourPlayers() {
        playerThree.setVisibility(View.VISIBLE);
        playerFour.setVisibility(View.VISIBLE);
    }

    public void playMultiPlayer(View v) {
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString(playerOneName, playerOne.getText().toString());
        myEditor.putString(playerTwoName, playerTwo.getText().toString());
        myEditor.putString(playerThreeName, playerThree.getText().toString());
        myEditor.putString(playerFourName, playerFour.getText().toString());
        myEditor.putInt(amountOfPlayers, amountOfPlayersInt);
        myEditor.apply();

        Intent intent = new Intent(this, PlayMultiPlayer.class);

        startActivity(intent);
    }



}
