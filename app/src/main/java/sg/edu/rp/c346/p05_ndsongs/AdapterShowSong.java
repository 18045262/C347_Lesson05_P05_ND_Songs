package sg.edu.rp.c346.p05_ndsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AdapterShowSong extends ArrayAdapter<Song> {

    private Context context;
    private ArrayList<Song> songs;
    private int resource;
    private ImageView iv1, iv2, iv3, iv4, iv5;
    private TextView tvYear, tvTitle, tvSinger;

    public AdapterShowSong(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);


        iv1 = rowView.findViewById(R.id.star1);
        iv2 = rowView.findViewById(R.id.star2);
        iv3 = rowView.findViewById(R.id.star3);
        iv4 = rowView.findViewById(R.id.star4);
        iv5 = rowView.findViewById(R.id.star5);

        tvYear = rowView.findViewById(R.id.tvYear);
        tvTitle = rowView.findViewById(R.id.tvTitle);
        tvSinger = rowView.findViewById(R.id.tvSinger);

        Song song = songs.get(position);

        if(song.getStars() >= 1){
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if(song.getStars() >= 2){
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if(song.getStars() >= 3){
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if(song.getStars() >= 4){
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
        }
        if(song.getStars() == 5){
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
        }


        tvYear.setText(song.getYear() + "");
        tvTitle.setText(song.getTitle());
        tvSinger.setText(song.getSingers());

        return rowView;
    }
}
