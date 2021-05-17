package com.myapplicationdev.android.p04_revisionnotes;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyActivity extends AppCompatActivity {

    TextView tvSongId;
    Song song;
    EditText etSongTitle, etSingers, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup radioGroupStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);


        tvSongId = findViewById(R.id.tvSongId);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        radioGroupStars = findViewById(R.id.radioGroupStars);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");

        tvSongId.setText(song.getId() + "");
        etYear.setText(song.getYear() + "");
        etSingers.setText(song.getSingers());
        etSongTitle.setText(song.getTitle());


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checking = radioGroupStars.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(checking);
                int stars = Integer.parseInt(rbSelected.getText().toString());
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                song.setYear(Integer.parseInt(etYear.getText().toString()));
                song.setSingers(etSingers.getText().toString());
                song.setTitle(etSongTitle.getText().toString());
                song.setStars(stars);

                dbh.updateSong(song);
                dbh.close();
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }
}
