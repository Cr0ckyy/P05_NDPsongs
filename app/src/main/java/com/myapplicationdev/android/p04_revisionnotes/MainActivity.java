package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert;
        Button btnShowList;
        final EditText etNote;
        final RadioGroup rg;

        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShowList = findViewById(R.id.buttonShowList);
        etNote = findViewById(R.id.editTextNote);
        rg = findViewById(R.id.radioGroupStars);
//        rb5 = findViewById(R.id.radio5);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                int stars = 0;
                if ((rg.getCheckedRadioButtonId()) == 2131165316) {
                    stars = 1;
                } else if ((rg.getCheckedRadioButtonId()) == 2131165317) {
                    stars = 2;
                } else if ((rg.getCheckedRadioButtonId()) == 2131165318) {
                    stars = 3;
                } else if ((rg.getCheckedRadioButtonId()) == 2131165319) {
                    stars = 4;
                } else {
                    stars = 5;
                }

                String content = etNote.getText().toString();
                ArrayList<Note> contents = db.getAllNotes();
                Boolean found = false;
                for (int i = 0; i < contents.size(); i++) {
                    Note currentNote = contents.get(i);
                    if (currentNote.getNoteContent().equalsIgnoreCase(content)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    db.insertNote(content, stars);
                } else {
                    Log.i("Myron", "Noped");
                }

                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(i);
            }
        });

    }
}
