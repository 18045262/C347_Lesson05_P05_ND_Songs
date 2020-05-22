package sg.edu.rp.c346.p05_ndsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText etTitle, etSingers, etYear;
    RadioGroup rgStar;
    RadioButton rbSelected;
    TextView tv;

    String title, singer;
    int year, star;

    ArrayList<Song> alSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStars);
        tv = findViewById(R.id.tv);





        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = etTitle.getText().toString();
                singer = etSingers.getText().toString();
                year = Integer.parseInt(etYear.getText().toString());
                star = rgStar.getCheckedRadioButtonId();
                rbSelected = findViewById(star);
                int selectedStar = Integer.parseInt(rbSelected.getText().toString());

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(title,singer,year,selectedStar);
                dbh.close();
                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ShowActivity.class);
                startActivity(i);
            }
        });

    }
}
