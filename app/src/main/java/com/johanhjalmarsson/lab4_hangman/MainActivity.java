package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Drawable d = getDrawable(R.drawable.actionwallpaper);
        getActionBar().setBackgroundDrawable(d);*/

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.playButton):
                Intent intent1 = new Intent(this, PlayLevelOne.class);
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

    public void runPlayLevelOne(View view) {
        Intent intent = new Intent(this, PlayLevelOne.class);
        startActivity(intent);
    }
    public void runPlayLevelTwo(View view) {
        Intent intent = new Intent(this, PlayLevelTwo.class);
        startActivity(intent);
    }
    public void runPlayLevelThree(View view) {
        Intent intent = new Intent(this, PlayLevelThree.class);
        startActivity(intent);
    }
    public void runAbout(View view) {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

}
