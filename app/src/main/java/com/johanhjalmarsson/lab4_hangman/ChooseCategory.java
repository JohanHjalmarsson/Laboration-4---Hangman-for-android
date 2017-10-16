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
    public static final String choiceX = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        spinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
    }


    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void nextActivity(View v) {
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

    }
    public void playOne(String choice) {
        Intent intent = new Intent(this, PlayLevelOne.class);

        intent.putExtra(choiceX, choice);
        startActivity(intent);
    }
    public void playTwo(String choice) {
        Intent intent = new Intent(this, PlayLevelTwo.class);

        intent.putExtra(choiceX, choice);
        startActivity(intent);
    }
    public void playThree(String choice) {
        Intent intent = new Intent(this, PlayLevelThree.class);

        intent.putExtra(choiceX, choice);
        startActivity(intent);
    }
}
