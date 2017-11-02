package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Class for choosing category and level for single player game
 * @author Johan Hjalmarsson
 */
public class ChooseCategory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Spinner spinner2;
    /**
     * static final String key for putting string to intent
     */
    public static final String choiceCategory = "0";
    /**
     * static final String key for putting string to intent
     */
    public static final String choiceLevel = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        setTitle("Choose category");

        spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner2 = (Spinner) findViewById(R.id.levelSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.level_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
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

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    }


    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Takes the users choices in category and level and starts the game (PlayLevelOne)
     * @param v
     */
    public void playActivity(View v) {
        String categoryChoice = spinner.getSelectedItem().toString();
        String levelChoice = spinner2.getSelectedItem().toString();

        Intent intent = new Intent(this, PlayLevelOne.class);

        intent.putExtra(choiceCategory, categoryChoice);
        intent.putExtra(choiceLevel, levelChoice);
        startActivity(intent);
        finish();


    }

}
