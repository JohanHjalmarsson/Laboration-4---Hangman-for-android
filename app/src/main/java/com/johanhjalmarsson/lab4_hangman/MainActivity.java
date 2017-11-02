package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Class for main options of the application; Play Hangman, Play Multiplayer and About
 * @author uthor Johan Hjalmarsson
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        setTitle("Hangman");

        return super.onCreateOptionsMenu(menu);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.playButton):
                Intent intent1 = new Intent(this, ChooseCategory.class);
                startActivity(intent1);
                return true;
            case (R.id.infoButton):
                Intent intent2 = new Intent(this, About.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Starts the ChooseCategory category
     * @param view
     */
    public void runChooseCategory(View view) {
        Intent intent = new Intent(this, ChooseCategory.class);

        startActivity(intent);
    }

    /**
     * Starts the MultiPlayerInit activity
     * @param v
     */
    public void runMultiplayer(View v) {
        Intent intent = new Intent(this, MultiPlayerInit.class);
        startActivity(intent);
    }

    /**
     * Starts the About activity
     * @param view
     */
    public void runAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

}
