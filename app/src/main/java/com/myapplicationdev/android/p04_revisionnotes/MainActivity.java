package com.myapplicationdev.android.p04_revisionnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert;
        Button btnShowList;
        final EditText etNote;
        final RadioButton rb1;
        final RadioButton rb2;
        final RadioButton rb3;
        final RadioButton rb4;
        final RadioButton rb5;

        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShowList = findViewById(R.id.buttonShowList);
        etNote = findViewById(R.id.editTextNote);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        rb4 = findViewById(R.id.radio4);
        rb5 = findViewById(R.id.radio5);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                int stars = 0;
                if (rb1.isSelected()) {
                    stars = 1;
                } else if (rb2.isSelected()) {
                    stars = 2;
                } else if (rb3.isSelected()) {
                    stars = 3;
                } else if (rb4.isSelected()) {
                    stars = 4;
                } else {
                    stars = 5;
                }

                // Insert a task
                db.insertNote(etNote.getText().toString(), stars);
                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertNote();
                db.close();
            }
        });

    }
}
