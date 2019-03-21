package test.com.courserating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TEACHER = "stud";
    private static String TAG = "MainActivity";

    private Button b1, b2;
    private EditText et3, et4, et5, et6;
    private Spinner teacher, subject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_main);

        init();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.teachers));
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.subjects));

        //set the spinners adapter to the ones just created
        teacher.setAdapter(adapter);
        subject.setAdapter(adapter2);
        Log.d(TAG, "spinner adapters init succesfully");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                Log.d(TAG, "B1: onClick: called");
                TeacherRating teacher = createTeacherFromView();
                Log.i(TAG, "Send: " + teacher);

                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra(TEACHER, teacher);
                startActivity(intent);

                break;
            case R.id.b2:
                Log.d(TAG, "B2: onClick: called");
                clearEditTexts();
                break;

        }
    }

    private TeacherRating createTeacherFromView() {
        TeacherRating stud = new TeacherRating();
        stud.setInitials(teacher.getSelectedItem().toString());
        stud.setSubject(subject.getSelectedItem().toString());
        stud.setRelevans(et3.getText().toString());
        stud.setPerformance(et4.getText().toString());
        stud.setPreparation(et5.getText().toString());

        return stud;
    }


    private void init() {
        Log.d(TAG, "init called");

        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);


        teacher = findViewById(R.id.spinnerTeacher);
        subject = findViewById(R.id.spinnerSubject);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        et6 = findViewById(R.id.et6);


        et3.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});
        et4.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});
        et5.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});
        et6.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});

    }

    private void clearEditTexts() {
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
    }

}
