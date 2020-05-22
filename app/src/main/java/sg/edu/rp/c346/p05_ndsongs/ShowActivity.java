package sg.edu.rp.c346.p05_ndsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lvShow;
    Button btnShow5Stars;

    ArrayAdapter<Song> aaSong;
    ArrayList<Song> alSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lvShow = findViewById(R.id.lvShow);
        btnShow5Stars = findViewById(R.id.btnShow5Stars);

        DBHelper db = new DBHelper(ShowActivity.this);
        final ArrayList<Song> data = db.getAllNotes();
        db.close();

        alSong = new ArrayList<Song>();

        for (int i = 0; i < data.size(); i++){
            alSong.add(data.get(i));
        }

        aaSong = new AdapterShowSong(ShowActivity.this,R.layout.row,alSong);
        lvShow.setAdapter(aaSong);

        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alSong.clear();
                for (int i = 0; i < data.size(); i++){
                    if (data.get(i).getStars() == 5){
                        alSong.add(data.get(i));
                        ArrayAdapter aa = new AdapterShowSong(ShowActivity.this,R.layout.row,alSong);
                        lvShow.setAdapter(aa);
                    }
                }
            }
        });

        lvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long identity) {
                Song data = alSong.get(position);
                Intent i = new Intent(ShowActivity.this, EditActivity.class);
                i.putExtra("data",data);
                startActivityForResult(i,9);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 9) {
                alSong.clear();
                DBHelper dbh = new DBHelper(ShowActivity.this);
                alSong.addAll(dbh.getAllNotes());
                dbh.close();
                ArrayAdapter aa = new AdapterShowSong(ShowActivity.this,R.layout.row,alSong);
                lvShow.setAdapter(aa);
            }
        }
    }
}
