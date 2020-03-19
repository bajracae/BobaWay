package com.hfad.bobaway;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;

    private static final String DB_URL = "mysql:host=classmysql.engr.oregonstate.edu;dname=cs340_perezjoe";
    private static final String USER = "cs340_perezjoe";
    private static final String PASS = "8666";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

//        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.et_write_review);
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
