package com.hfad.bobaway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView bobashopListTextView = (TextView) findViewById(R.id.tv_bobashop_list);
        bobashopListTextView.setText("Finish my TODO app.");


    }
}

