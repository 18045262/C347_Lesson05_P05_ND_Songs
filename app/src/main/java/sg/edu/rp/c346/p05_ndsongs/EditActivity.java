package sg.edu.rp.c346.p05_ndsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etID, etTitle, etSinger, etYear;
    RadioGroup rgStars;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        rgStars = findViewById(R.id.rgStars);
        etID = findViewById(R.id.etID);
        etTitle =  findViewById(R.id.etEditTitle);
        etSinger = findViewById(R.id.etEditSinger);
        etYear = findViewById(R.id.etEditYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(data.get_id() + "");
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear() + "");
        if (data.getStars() == 1) {
            rb1.setChecked(true);
        } else if (data.getStars() == 2) {
            rb2.setChecked(true);
        } else if (data.getStars() == 3) {
            rb3.setChecked(true);
        } else if (data.getStars() == 4) {
            rb4.setChecked(true);
        } else if (data.getStars() == 5) {
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditActivity.this);

                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setStars(Integer.parseInt(((RadioButton) rgStars.getChildAt(rgStars.indexOfChild(rgStars.findViewById(rgStars.getCheckedRadioButtonId())))).getText().toString()));
                dbh.updateNote(data);
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.get_id());
                dbh.close();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
