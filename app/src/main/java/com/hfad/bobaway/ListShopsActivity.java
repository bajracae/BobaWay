package com.hfad.bobaway;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hfad.bobaway.data.BobaWayRepo;
import com.hfad.bobaway.data.Status;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListShopsActivity extends AppCompatActivity implements BobaWayAdapter.OnSearchResultClickListener{
    private RecyclerView searchResultsRV;
    private EditText searchBarET;
    private ProgressBar loadingIndicatorPB;
    private TextView errorMessageTV;
    private DrawerLayout mDrawerLayout;
    private BobaWayAdapter bobaWayAdapter;
    private BobaWayViewModel viewModel;
    private String location = getIntent().getExtras().getString("location"); //searched location from MainActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shops);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchBarET = (EditText)findViewById(R.id.et_bobashop_entry_box);

        searchResultsRV = findViewById(R.id.rv_search_results);
        searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRV.setHasFixedSize(true);

        bobaWayAdapter = new BobaWayAdapter(this);
        searchResultsRV.setAdapter(bobaWayAdapter);

        loadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        errorMessageTV = findViewById(R.id.tv_error_message);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        viewModel = new ViewModelProvider(this).get(BobaWayViewModel.class);

        viewModel.getSearchResults().observe(this, new Observer<List<BobaWayRepo>>() {
            @Override
            public void onChanged(List<BobaWayRepo> gitHubRepos) {
                bobaWayAdapter.updateSearchResults(gitHubRepos);
            }
        });

        viewModel.getSearchResults().observe(this, new Observer<List<BobaWayRepo>>() {
            @Override
            public void onChanged(List<BobaWayRepo> gitHubRepos) {
                bobaWayAdapter.updateSearchResults(gitHubRepos);
            }
        });

        viewModel.getLoadingStatus().observe(this, new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status == Status.LOADING) {
                    loadingIndicatorPB.setVisibility(View.VISIBLE);
                } else if (status == Status.SUCCESS) {
                    loadingIndicatorPB.setVisibility(View.INVISIBLE);
                    searchResultsRV.setVisibility(View.VISIBLE);
                    errorMessageTV.setVisibility(View.INVISIBLE);
                } else {
                    loadingIndicatorPB.setVisibility(View.INVISIBLE);
                    searchResultsRV.setVisibility(View.INVISIBLE);
                    errorMessageTV.setVisibility(View.VISIBLE);
                }
            }
        });
        //error, needs to populate search bar
//        Intent intent = getIntent();
//        searchBarET.setText(intent.getExtras().getString("location"));
        Button addLocationButton = (Button) findViewById(R.id.btn_bobashop_search);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Log.d("Location: ", );
                if (!TextUtils.isEmpty(searchBarET.getText())) {
                    searchBarET.setText("");
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
