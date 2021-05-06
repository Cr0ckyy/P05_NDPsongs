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

// Todo: MainActivity | by Myron
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
                int stars = (rg.getCheckedRadioButtonId()) - 2131165315;
                Boolean canInsert = true;

                // Todo: Done by Zuhaili, simplified up by Shufang
                // Todo: Prevent the user from entering an empty value in EditText.
                // counting of editTextNote
                int editTextNoteCount = etNote.getText().toString().trim().length();
                if (editTextNoteCount == 0) {
                    canInsert = false;
                    Toast.makeText(MainActivity.this, "Not inserted; field is empty.", Toast.LENGTH_SHORT).show();
                }

                // Todo: Check if content is a duplicate | By Myron
                String content = etNote.getText().toString();
                ArrayList<Note> notes = db.getAllNotes();
                for (int i = 0; i < notes.size(); i++) {
                    Note currentNote = notes.get(i);
                    String currentContent = currentNote.getNoteContent();
                    if (currentContent.equalsIgnoreCase(content)) {
                        canInsert = false;
                        Toast.makeText(MainActivity.this, "Not inserted; already exists", Toast.LENGTH_SHORT).show();
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
