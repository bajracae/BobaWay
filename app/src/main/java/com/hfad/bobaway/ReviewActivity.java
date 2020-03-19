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

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

import com.hfad.bobaway.data.BobaWayItem;
import com.hfad.bobaway.utils.YelpUtils;

import static com.hfad.bobaway.ShopDetailActivity.EXTRA_BOBAWAY_REPO;

public class ReviewActivity extends AppCompatActivity {

    private EditText editText;
    private RatingBar ratingBar;
    private Button submitButton;
    private float rating;
    private String review;


    private static final String DB_URL = "mysql:host=classmysql.engr.oregonstate.edu;dname=cs340_perezjoe";
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

//    public void btnConn(View view) {
//        Send objectSend = new Send();
//        objectSend.execute("");
//    }

//    private class Send extends AsyncTask<String, String, String> {
//        String msg = "";
//        String text = editText.getText().toString();
//
//        @Override
//        protected void onPreExecute() {
//            textView.setText("Please Wait Inserting Data");
//        }
//
//
//    }
}
