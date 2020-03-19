package com.hfad.bobaway;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

import com.hfad.bobaway.data.BobaWayItem;
import com.hfad.bobaway.utils.YelpUtils;


public class ReviewActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private RatingBar ratingBar;
    private Button submitButton;
    private float rating;
    private String review;

    private static final String DB_URL = "jdbc:mysql://192.168.56.1/bobaway";
    private static final String USER = "cs340_perezjoe";
    private static final String PASS = "8666";

    private BobaWayItem restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(YelpUtils.EXTRA_ITEM)) {
            restaurant = (BobaWayItem)intent.getSerializableExtra(YelpUtils.EXTRA_ITEM);
        }

        submitButton = findViewById(R.id.b_submit_rating);
        ratingBar = findViewById(R.id.rating);
        editText = findViewById(R.id.et_write_review);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating = ratingBar.getRating();
                review = editText.getText().toString();
                Log.d("Review Activity", "submit is pressed with rating: " + rating);

            }
        });
    }

    public void btnConn(View view) {
        Send objectSend = new Send();
        objectSend.execute("");
    }

    private class Send extends AsyncTask<String, String, String> {
        String msg = "";
        String text = editText.getText().toString();

        @Override
        protected void onPreExecute() {
            textView.setText("Please Wait Inserting Data");
        }

        protected String doInBackground(String... strings) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                if (conn == null) {
                    msg = "Connection went wrong";
                }
                else{ // If conn is not empty, insert the entered value into the
                    String query = "INSERT INTO bobaway (comment) VALUES('"+ text + "')";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    msg = "Inserting Success!";
                }
                conn.close();
            } catch (Exception e) {
                msg = "Connection went wrong";
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {
            textView.setText(msg);
        }
    }
}
