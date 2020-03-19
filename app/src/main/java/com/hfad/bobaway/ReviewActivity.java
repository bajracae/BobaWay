package com.hfad.bobaway;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;

    private static final String DB_URL = "jdbc:mysql://192.168.56.1/bobaway";
    private static final String USER = "cs340_perezjoe";
    private static final String PASS = "8666";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

//        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.et_write_review);
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
                else{
                    String query = "INSERT INTO bobaway" + text + ")";
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
