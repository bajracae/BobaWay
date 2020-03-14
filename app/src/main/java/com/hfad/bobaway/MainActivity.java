package com.hfad.bobaway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWriteReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_review_page);
        mWriteReview = findViewById(R.id.et_write_review);
        InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(mWriteReview.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
