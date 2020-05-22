package sg.edu.rp.c346.p05_ndsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lvShow;
    Button btnShow5Stars;

    ArrayAdapter aaSong;
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



    }
}
