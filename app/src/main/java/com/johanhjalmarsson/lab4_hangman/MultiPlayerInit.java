package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Class for collecting amount of players and the names the player.
 * @author Johan Hjalmarsson
 */
public class MultiPlayerInit extends AppCompatActivity {

    private EditText playerOne;
    private EditText playerTwo;
    private EditText playerThree;
    private EditText playerFour;
    /**
     * key for sharedPreferences
     */
    public static final String myPreferencesString = "tjo!";
    /**
     * key for adding player name to sharedPreferences
     */
    public static final String playerOneName = "Player One";
    /**
     * key for adding player name to sharedPreferences
     */
    public static final String playerTwoName = "Player Two";
    /**
     * key for adding player name to sharedPreferences
     */
    public static final String playerThreeName = "Player Three";
    /**
     * key for adding player name to sharedPreferences
     */
    public static final String playerFourName = "Player Four";
    /**
     * key for adding amount of players to sharedPreferences
     */
    public static final String amountOfPlayers = "Amount of players";
    /**
     * key for adding turn count to sharedPreferences
     */
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
        setTitle("Multiplayer");

        playerOne = (EditText) findViewById(R.id.playerOneName);
        playerTwo = (EditText) findViewById(R.id.playerTwoName);
        playerFour = (EditText) findViewById(R.id.playerFourName);
        playerThree = (EditText) findViewById(R.id.playerThreeName);

        playerThree.setVisibility(View.INVISIBLE);
        playerFour.setVisibility(View.INVISIBLE);

        myPreferences = getSharedPreferences(myPreferencesString, 0);







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
     * Checks which radiobutton is checked and changes amount of players according to the button.
     * @param view
     */
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();


        switch (view.getId()){
            case (R.id.twoPlayers):
                if (checked) {
                    radio4Checked = true;
                    amountOfPlayersInt = 2;
                    twoPlayers();
                }
                break;
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

        }
    }

    /**
     * Controls visibility of the EditTexts according to amount of players
     */
    public void twoPlayers() {
        playerThree.setVisibility(View.INVISIBLE);
        playerFour.setVisibility(View.INVISIBLE);
    }

    /**
     * Controls visibility of the EditTexts according to amount of players
     */
    public void threePlayers() {
        playerThree = (EditText) findViewById(R.id.playerThreeName);
        playerThree.setVisibility(View.VISIBLE);
        playerFour.setVisibility(View.INVISIBLE);
    }

    /**
     * Controls visibility of the EditTexts according to amount of players
     */
    public void fourPlayers() {
        playerThree.setVisibility(View.VISIBLE);
        playerFour.setVisibility(View.VISIBLE);
    }

    /**
     * Adds the players names to SharedPreferences according to amount of players
     * Then starts the PlayMultiPlayer activity.
     * @param v
     */
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
        finish();
    }



}
