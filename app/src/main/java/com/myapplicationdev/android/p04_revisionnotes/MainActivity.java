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
            String data1 = etSingers.getText().toString();
            int data2 = Integer.parseInt(etYear.getText().toString());
            int data3 = Integer.parseInt(selectedRadioButton.getText().toString());
            DBHelper dbh = new DBHelper(MainActivity.this);



            long inserted_id = dbh.insertSong(data, data1, data2, data3);
            dbh.close();

            if (inserted_id != -1) {
                Toast.makeText(MainActivity.this, "Insert successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,
                        ShowSong.class);
                startActivity(i);
            }
        });
    }

}

