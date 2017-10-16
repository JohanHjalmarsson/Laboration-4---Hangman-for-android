package com.johanhjalmarsson.lab4_hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ChooseCategory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Spinner spinner2;
    public static final String choiceCategory = "0";
    public static final String choiceLevel = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner2 = (Spinner) findViewById(R.id.levelSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.level_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    }


    public void onNothingSelected(AdapterView<?> parent) {
    }

    /*public void nextActivity(View v) {
        Intent getIntent = getIntent();
        String option = getIntent.getStringExtra(MainActivity.nullString);
        String choice = spinner.getSelectedItem().toString();

        switch (option) {
            case "1":
                playOne(choice);
                break;
            case "2":
                playTwo(choice);
                break;
            case "3":
                playThree(choice);
                break;
        }

    }*/
    public void playActivity(View v) {
        String categoryChoice = spinner.getSelectedItem().toString();
        String levelChoice = spinner2.getSelectedItem().toString();

        Intent intent = new Intent(this, PlayLevelOne.class);

        intent.putExtra(choiceCategory, categoryChoice);
        intent.putExtra(choiceLevel, levelChoice);

        startActivity(intent);
    }
  /*  public void playTwo(String choice) {
        Intent intent = new Intent(this, PlayLevelTwo.class);

        intent.putExtra(choiceX, choice);
        startActivity(intent);
    }
    public void playThree(String choice) {
        Intent intent = new Intent(this, PlayLevelThree.class);

        intent.putExtra(choiceX, choice);
        startActivity(intent);
    } */
}
