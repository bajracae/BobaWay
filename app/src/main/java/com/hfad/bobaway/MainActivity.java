package com.hfad.bobaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;


import com.google.android.material.navigation.NavigationView;
import com.hfad.bobaway.data.BobaWayRepo;
import com.hfad.bobaway.data.Status;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText searchBarET;
    private BobaWayAdapter bobaWayAdapter;

    private BobaWayViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBarET = findViewById(R.id.et_bobashop_entry_box);
        viewModel = new ViewModelProvider(this).get(BobaWayViewModel.class);
        Button addLocationButton = (Button) findViewById(R.id.btn_main_search);

        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = searchBarET.getText().toString();
                if (!TextUtils.isEmpty(locationText)) {
                    searchBarET.setText("");
                    Intent bobaListIntent =new Intent(v.getContext(), ListShopsActivity.class);
                    bobaListIntent.putExtra("location", searchBarET.getText().toString());
                    startActivity(bobaListIntent);
                }
            }
        });
    }
    // This function hides the soft keyboard when click outside of edit text
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}
