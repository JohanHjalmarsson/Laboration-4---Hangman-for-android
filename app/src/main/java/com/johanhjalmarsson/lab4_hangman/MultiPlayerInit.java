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
    private EditText playerOne;
    private EditText playerTwo;
    private EditText playerThree;
    private EditText playerFour;
    public static final String myPreferencesString = "tjo!";
    public static final String playerOneName = "Player One";
    public static final String playerTwoName = "Player Two";
    public static final String playerThreeName = "Player Three";
    public static final String playerFourName = "Player Four";
    public static final String amountOfPlayers = "Amount of players";
    public static final String turns = "Turns";
    private int amountOfPlayersInt = 2;
    private int turnNumber = 0;
    private SharedPreferences myPreferences;
    private boolean radio2Checked = false;
    private boolean radio3Checked = false;
    private boolean radio4Checked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_init);

        playerOne = (EditText) findViewById(R.id.playerOneName);
        playerTwo = (EditText) findViewById(R.id.playerTwoName);
        playerFour = (EditText) findViewById(R.id.playerFourName);
        playerThree = (EditText) findViewById(R.id.playerThreeName);

        playerThree.setVisibility(View.INVISIBLE);
        playerFour.setVisibility(View.INVISIBLE);

        myPreferences = getSharedPreferences(myPreferencesString, 0);







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

    public void playMultiplayer(View v) {
        SharedPreferences.Editor myEditor = myPreferences.edit();

        switch (amountOfPlayersInt) {
            case 2:
                myEditor.putString(playerOneName, playerOne.getText().toString());
                myEditor.putString(playerTwoName, playerTwo.getText().toString());
                myEditor.apply();
                break;
            case 3:
                myEditor.putString(playerOneName, playerOne.getText().toString());
                myEditor.putString(playerTwoName, playerTwo.getText().toString());
                myEditor.putString(playerThreeName, playerThree.getText().toString());
                myEditor.apply();
                break;
            case 4:
                myEditor.putString(playerOneName, playerOne.getText().toString());
                myEditor.putString(playerTwoName, playerTwo.getText().toString());
                myEditor.putString(playerThreeName, playerThree.getText().toString());
                myEditor.putString(playerFourName, playerFour.getText().toString());
                myEditor.apply();
                break;
            default:
                break;


        }
        myEditor.putInt(amountOfPlayers, amountOfPlayersInt);
        myEditor.putInt(turns, turnNumber);
        myEditor.apply();

        Intent intent = new Intent(this, PlayMultiPlayer.class);

        startActivity(intent);
    }



}
