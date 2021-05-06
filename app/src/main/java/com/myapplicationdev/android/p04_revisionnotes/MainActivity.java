package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// Todo: Done by Myron
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

        btnInsert.setOnClickListener(new View.OnClickListener() {
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
                Log.i("Myron", rg.getCheckedRadioButtonId() + "");


                // Todo: Prevent the user from submitting empty note in the editText.
                // Todo: Done by Zuhaili
                String textnode = "";
                textnode += etNote.getText().toString();

                if(etNote.getText().toString().trim().length() != 0){
                    db.insertNote(textnode, stars);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please do insert note next time!", Toast.LENGTH_SHORT).show();
                }
                Boolean canInsert = true;

                // Todo: Done by Shufang
                // Todo: Prevent the user from entering an empty value in EditText.
                //counting of editTextNote
//                int editTextNoteCount = etNote.getText().toString().trim().length();
//
//
//                if (editTextNoteCount == 0) {
//                    canInsert = false;
//                    Toast.makeText(MainActivity.this, "The note has not been inserted; the field is empty.", Toast.LENGTH_SHORT).show();
//                }

                // Todo: Check if content is a duplicate | By Myron
                String content = etNote.getText().toString();
                ArrayList<Note> contents = db.getAllNotes();


                for (int i = 0; i < contents.size(); i++) {
                    Note currentNote = contents.get(i);
                    if (currentNote.getNoteContent().equalsIgnoreCase(content)) {
                        Toast.makeText(MainActivity.this, "Note not inserted; already exists", Toast.LENGTH_SHORT).show();
                        canInsert = false;
                        break;
                    }
                }

                if (canInsert) {
                    db.insertNote(content, stars);
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                }

                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(i);
            }
        });

    }
}
