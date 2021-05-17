package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// todo: Done by Zizou
public class ShowActivity extends AppCompatActivity {
    ArrayList<Song> al;
    ArrayAdapter aa;
    ListView lv;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnShow = findViewById(R.id.btnShow);

        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        DBHelper db = new DBHelper(ShowActivity.this);
        al = db.getAllNotes();
        aa = new SongAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener((parent, view, position, identity) -> {
            Song song = al.get(position);
            Intent i = new Intent(ShowActivity.this,
                    ModifyActivity.class);
            i.putExtra("song", song);
            startActivityForResult(i, 9);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            DBHelper db = new DBHelper(ShowActivity.this);
            al = new ArrayList<>();
            al = db.getAllNotes();
            aa = new SongAdapter(this, R.layout.row, al);
            lv.setAdapter(aa);
        }
    }
}
