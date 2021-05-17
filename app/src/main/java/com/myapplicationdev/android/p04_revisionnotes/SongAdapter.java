package com.myapplicationdev.android.p04_revisionnotes;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



// todo: Done by Shufang
public class SongAdapter extends ArrayAdapter<com.myapplicationdev.android.p04_revisionnotes.Song> {
    Context context;
    ArrayList<com.myapplicationdev.android.p04_revisionnotes.Song> songs;
    int resource;
    ImageView iv1, iv2, iv3, iv4, iv5;
    TextView tvYear, tvTitle, tvSinger;

    public SongAdapter(Context context, int resource, ArrayList<com.myapplicationdev.android.p04_revisionnotes.Song> notes) {
        super(context, resource, notes);
        this.context = context;
        this.songs = notes;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        iv1 = rowView.findViewById(R.id.imageView1star2);
        iv2 = rowView.findViewById(R.id.imageView2star2);
        iv3 = rowView.findViewById(R.id.imageView3star2);
        iv4 = rowView.findViewById(R.id.imageView4star2);
        iv5 = rowView.findViewById(R.id.imageView5star2);

        tvYear = rowView.findViewById(R.id.tvYearSongs);
        tvTitle = rowView.findViewById(R.id.tvSongName);
        tvSinger = rowView.findViewById(R.id.tvArtist);

        com.myapplicationdev.android.p04_revisionnotes.Song song = songs.get(position);
        int stars = song.getStars();

        //Check if the property for starts >= 5, if so, "light" up the stars
        tvYear.setText(song.getYear());
        tvTitle.setText(song.getTitle());
        tvSinger.setText(song.getSingers());

        if (stars >= 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (stars == 4) {
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (stars == 3) {
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (stars == 2) {
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        return rowView;
    }


}

