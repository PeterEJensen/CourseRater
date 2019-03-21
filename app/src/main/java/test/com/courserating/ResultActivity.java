package test.com.courserating;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private static String TAG = "ResultActivity";

    private TextView t1, t2, t3, t4, t5, tvScore;
    private RatingBar ratingBar;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        final TeacherRating stud = getIntent().getParcelableExtra(MainActivity.TEACHER);
        Log.i(TAG, "Received" + stud);

        init();

        setStudDataInView(stud);

        int x = Integer.parseInt(stud.getSubjectRelevans());
        int y = Integer.parseInt(stud.getPerformance());
        int z = Integer.parseInt(stud.getPreparation());

        final int total = (x + y + z) / 3;

        tvScore.setText(Integer.toString(total));

        if (total >= 90) {
            tvScore.setText("A");
            ratingBar.setRating(5);

        } else if (total >= 80 && total < 90) {
            tvScore.setText("B");
            ratingBar.setRating(4);

        } else if (total >= 70 && total < 80) {
            tvScore.setText("C");
            ratingBar.setRating(3);

        } else if (total >= 60 && total < 70) {
            tvScore.setText("D");
            ratingBar.setRating(2);
        } else if (total >= 50 && total < 60) {
            tvScore.setText("E");
            ratingBar.setRating(1);

        } else if (total < 50) {
            tvScore.setText(R.string.rekt);
            ratingBar.setRating(0);
        }

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.i(TAG, "Send mail");
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("text/plain");

                email.putExtra(Intent.EXTRA_EMAIL, new String[]{stud.getTeacherName() + "@kea.dk"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Rating for " + stud.getSubject() + " course");

                String htmlContent = "<h2>Hi " + stud.getTeacherName() + "</h2>" +
                        "<p>Your rating for the subject: </p>" + stud.getSubject() +
                        "<p>Your total average score was: </p>" + total +

                        "<br>" +
                        "<h3>/KEA Administration</h3>";

                email.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(htmlContent, 1));
                startActivity(Intent.createChooser(email, "Choose an email client from..."));
            }
        });

    }

    private void setStudDataInView(TeacherRating stud) {
        Log.i(TAG, "setStudDataInView called");
        t1.setText(stud.getTeacherName());
        t2.setText(stud.getSubject());
        t3.setText(stud.getSubjectRelevans());
        t4.setText(stud.getPerformance());
        t5.setText(stud.getPreparation());
    }

    private void init() {
        Log.i(TAG, "Init called");
        t1 = findViewById(R.id.tv1);
        t2 = findViewById(R.id.tv2);
        t3 = findViewById(R.id.tv3);
        t4 = findViewById(R.id.tv4);
        t5 = findViewById(R.id.tv5);
        tvScore = findViewById(R.id.tvScore);
        ratingBar = findViewById(R.id.ratingBar);
        submit = findViewById(R.id.buttonSubmit);


    }

}
