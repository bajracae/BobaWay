package com.hfad.bobaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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


public class MainActivity extends AppCompatActivity implements BobaWayAdapter.OnSearchResultClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView searchResultsRV;
    private EditText searchBarET;
    private ProgressBar loadingIndicatorPB;
    private TextView errorMessageTV;
    private DrawerLayout mDrawerLayout;

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
        Button addLocationButton = (Button) findViewById(R.id.btn_bobashop_search);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = searchBarET.getText().toString();
                if (!TextUtils.isEmpty(locationText)) {
//                    searchBarET.setText("");
                    Intent strLoc = new Intent(MainActivity.this, ListShopsActivity.class);
                    strLoc.putExtra("location", locationText);
                    startActivity(new Intent(MainActivity.this, ListShopsActivity.class));
//                    try {
//                        doYelpSearch("Corvallis");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSearchResultClicked(BobaWayRepo repo) {
        Intent intent = new Intent(this, RepoDetailActivity.class);
        intent.putExtra(RepoDetailActivity.EXTRA_BOBAWAY_REPO, repo);
        startActivity(intent);
    }


    private void doGitHubSearch(String searchQuery) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String sort = preferences.getString(
//                getString(R.string.pref_sort_key),
//                getString(R.string.pref_sort_default)
//        );
//        String language = preferences.getString(
//                getString(R.string.pref_language_key),
//                getString(R.string.pref_language_default)
//        );
//        String user = preferences.getString(
//                getString(R.string.pref_user_key), ""
//        );
//        boolean searchInName = preferences.getBoolean(
//                getString(R.string.pref_in_name_key), true
//        );
//        boolean searchInDescription = preferences.getBoolean(
//                getString(R.string.pref_in_description_key), true
//        );
//        boolean searchInReadme = preferences.getBoolean(
//                getString(R.string.pref_in_readme_key), true
//        );
//        viewModel.loadSearchResults(searchQuery, sort, language, user, searchInName,
//                searchInDescription, searchInReadme);
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
