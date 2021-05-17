package com.myapplicationdev.android.p04_revisionnotes;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etSongTitle, etSingers, etYear;
    RadioGroup radioGroupStars;
    Button btnInsert, btnShow;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        radioGroupStars = findViewById(R.id.radioGroupStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);

        btnInsert.setOnClickListener(v -> {

            // TODO: getting values from RadioButton
            int CheckedRadioButtonId = radioGroupStars.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(CheckedRadioButtonId);


            // obtain data from the Song Form
            String songTitle = etSongTitle.getText().toString();
            String singerName = etSingers.getText().toString();
            int releasedYear = Integer.parseInt(etYear.getText().toString());
            int starValue = Integer.parseInt(selectedRadioButton.getText().toString());
            DBHelper dbh = new DBHelper(MainActivity.this);


            long insertedId = dbh.insertSong(songTitle, singerName, releasedYear, starValue);
            dbh.close();

            if (insertedId != -1) {
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });


    }

}

