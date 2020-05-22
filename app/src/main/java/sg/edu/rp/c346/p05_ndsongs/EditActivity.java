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
    RadioButton r1, r2, r3, r4, r5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        rgStars = (RadioGroup) findViewById(R.id.rgStars);
        etID = (EditText) findViewById(R.id.etID);
        etTitle = (EditText) findViewById(R.id.etEditTitle);
        etSinger = (EditText) findViewById(R.id.etEditSinger);
        etYear = (EditText) findViewById(R.id.etEditYear);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        r1 = (RadioButton) findViewById(R.id.rb1);
        r2 = (RadioButton) findViewById(R.id.rb2);
        r3 = (RadioButton) findViewById(R.id.rb3);
        r4 = (RadioButton) findViewById(R.id.rb4);
        r5 = (RadioButton) findViewById(R.id.rb5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(data.get_id() + "");
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear() + "");
        if (data.getStars() == 1) {
            r1.setChecked(true);
        } else if (data.getStars() == 2) {
            r2.setChecked(true);
        } else if (data.getStars() == 3) {
            r3.setChecked(true);
        } else if (data.getStars() == 4) {
            r4.setChecked(true);
        } else if (data.getStars() == 5) {
            r5.setChecked(true);
        } else {
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
