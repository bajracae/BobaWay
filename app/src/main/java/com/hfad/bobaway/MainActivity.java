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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_nav_menu);

        searchBarET = (EditText) findViewById(R.id.et_bobashop_entry_box);

//        searchResultsRV = findViewById(R.id.rv_search_results);
//
//        searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
//        searchResultsRV.setHasFixedSize(true);

//        bobaWayAdapter = new BobaWayAdapter(this);
//        searchResultsRV.setAdapter(bobaWayAdapter);

        loadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        errorMessageTV = findViewById(R.id.tv_error_message);

        mDrawerLayout = findViewById(R.id.drawer_layout);

//        viewModel = new ViewModelProvider(this).get(BobaWayViewModel.class);

//        viewModel.getSearchResults().observe(this, new Observer<List<BobaWayRepo>>() {
//            @Override
//            public void onChanged(List<BobaWayRepo> gitHubRepos) {
//                bobaWayAdapter.updateSearchResults(gitHubRepos);
//            }
//        });


//        viewModel.getSearchResults().observe(this, new Observer<List<GitHubRepo>>() {
//            @Override
//            public void onChanged(List<BobaWayRepo> gitHubRepos) {
//                bobaWayAdapter.updateSearchResults(gitHubRepos);
//            }
//        });

//        viewModel.getLoadingStatus().observe(this, new Observer<Status>() {
//            @Override
//            public void onChanged(Status status) {
//                if (status == Status.LOADING) {
//                    loadingIndicatorPB.setVisibility(View.VISIBLE);
//                } else if (status == Status.SUCCESS) {
//                    loadingIndicatorPB.setVisibility(View.INVISIBLE);
//                    searchResultsRV.setVisibility(View.VISIBLE);
//                    errorMessageTV.setVisibility(View.INVISIBLE);
//                } else {
//                    loadingIndicatorPB.setVisibility(View.INVISIBLE);
//                    searchResultsRV.setVisibility(View.INVISIBLE);
//                    errorMessageTV.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        //clicked button, sends string of location and goes to next page (Act 2) layout
        searchBarET = findViewById(R.id.et_bobashop_entry_box);
        viewModel = new ViewModelProvider(this).get(BobaWayViewModel.class);

        Button addLocationButton = (Button) findViewById(R.id.btn_main_search);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = searchBarET.getText().toString();
                if (!TextUtils.isEmpty(locationText)) {
                    Intent strLoc = new Intent(MainActivity.this, ListShopsActivity.class);
                    strLoc.putExtra("location", locationText);
                    startActivity(new Intent(MainActivity.this, ListShopsActivity.class));
                    searchBarET.setText("");
                    try {
                        doYelpSearch("Corvallis");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent bobaListIntent =new Intent(v.getContext(), ListShopsActivity.class);
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
//    private void doYelpSearch(String location) throws IOException {
//        viewModel.loadSearchResults(location);
//    }
}
