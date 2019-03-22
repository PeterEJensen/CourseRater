package test.com.courserating;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import guy4444.smartrate.SmartRate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TEACHER = "stud";
    private static String TAG = "MainActivity";

    private Button b1, b2;
    private EditText et3, et4, et5, et6;
    private Spinner teacher, subject;
    private FloatingActionButton fab;
    private TextView textview4;


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


        teacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!teacher.getSelectedItem().equals("Select teacher")) {
                    subject.setVisibility(View.VISIBLE);
                } else {
                    hideUI();
                    subject.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!subject.getSelectedItem().equals("Select subject")) {
                    showUI();
                } else {
                    hideUI();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1:
                Log.d(TAG, "B1: onClick: called");
                if (et3.getText().length() > 0 && et4.getText().length() > 0 && et5.getText().length() > 0) {
                    TeacherRating teacher = createTeacherFromView();
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra(TEACHER, teacher);
                    startActivity(intent);

                } else if (et3.getText().length() == 0) {
                    et3.setError("Cannot be blank");
                    et3.requestFocus();
                } else if (et4.getText().length() == 0) {
                    et4.setError("Cannot be blank");
                    et4.requestFocus();
                } else if (et5.getText().length() == 0) {
                    et5.setError("Cannot be blank");
                    et5.requestFocus();
                }
                break;

            case R.id.b2:
                Log.d(TAG, "B2: onClick: called");
                clearEditTexts();
                break;

            case R.id.fab:
                Log.d(TAG, "FAB clicked");
                SmartRate.Rate(MainActivity.this
                        , "Rate Us"
                        , "Tell others what you think about this app"
                        , "Continue"
                        , "Please take a moment and rate us on Google Play"
                        , ""
                        , "Cancel"
                        , "Thanks for the feedback"
                        , Color.parseColor("#2196F3")
                        , 4

                );
                break;

        }      //Log.i(TAG, "Send: " + teacher);
    }

    private TeacherRating createTeacherFromView() {

        TeacherRating teach = new TeacherRating();
        teach.setInitials(teacher.getSelectedItem().toString());
        teach.setSubject(subject.getSelectedItem().toString());
        teach.setRelevans(et3.getText().toString());
        teach.setPerformance(et4.getText().toString());
        teach.setPreparation(et5.getText().toString());
        return teach;


    }


    private void init() {
        Log.d(TAG, "init called");

        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);


        teacher = findViewById(R.id.spinnerTeacher);
        subject = findViewById(R.id.spinnerSubject);
        et3 = findViewById(R.id.et3);
        et4 = findViewById(R.id.et4);
        et5 = findViewById(R.id.et5);
        //  et6 = findViewById(R.id.et6);
        textview4 = findViewById(R.id.textView4);


        et3.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});
        et4.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});
        et5.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});
        //  et6.setFilters(new InputFilter[]{new MinMaxFilter("1", "100")});


    }

    private void clearEditTexts() {
        et3.setText("");
        et4.setText("");
        et5.setText("");
        teacher.setSelection(0);
        subject.setSelection(0);
        //et6.setText("");
    }

    private void hideUI() {
        et3.setVisibility(View.INVISIBLE);
        et4.setVisibility(View.INVISIBLE);
        et5.setVisibility(View.INVISIBLE);
        textview4.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.INVISIBLE);
        b2.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);
    }

    private void showUI() {
        et3.setVisibility(View.VISIBLE);
        et4.setVisibility(View.VISIBLE);
        et5.setVisibility(View.VISIBLE);
        textview4.setVisibility(View.VISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

}
