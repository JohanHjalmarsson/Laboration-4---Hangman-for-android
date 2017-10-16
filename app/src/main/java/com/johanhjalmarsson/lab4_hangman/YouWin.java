package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class YouWin extends AppCompatActivity {
    public final static String theWord = "0";
    public final static String theInt = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_win);

        Intent intentX = getIntent();

        TextView secretWordView = (TextView) findViewById(R.id.secretWord);
        TextView triesLeftView = (TextView) findViewById(R.id.triesLeft);

        String ett = "The secret word was: "+intentX.getStringExtra(theWord);
        String tva = "You had "+intentX.getStringExtra(theInt);

        secretWordView.setText(ett);
        triesLeftView.setText(tva);
    }
    public void playAgain(View v) {
        Intent intent = new Intent(this, ChooseCategory.class);
        startActivity(intent);
    }
    public void dontPlayAgain(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
