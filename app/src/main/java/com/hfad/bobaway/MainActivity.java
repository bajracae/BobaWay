//package com.hfad.bobaway;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
////
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//    private TextView mBobashopListTextView;
//    private EditText mBobashopEntryEditText;
//
//    private ArrayList<String> mBobashopList;
//
//    private RecyclerView mBobawayListRecyclerView;
//    private BobawayAdapter mBobawayAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        TextView bobashopListTextView = (TextView) findViewById(R.id.tv_bobashop_list);
//
//        mBobashopListTextView =
//                (TextView)findViewById(R.id.tv_bobashop_list);
//        mBobashopEntryEditText =
//                (EditText)findViewById(R.id.et_bobashop_entry_box);
//
//        Button searchBobashopButton =
//                (Button)findViewById(R.id.btn_bobashop_search);
//
//        mBobawayListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mBobawayListRecyclerView.setHasFixedSize(true);
//
//        mBobawayAdapter = new BobawayAdapter();
//        mBobawayListRecyclerView.setAdapter(mBobawayAdapter);
//
//        mBobashopList = new ArrayList<String>();
//
//        searchBobashopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String bobashopText =
//                        mBobashopEntryEditText.getText().toString();
//                if (!TextUtils.isEmpty(bobashopText)) {
//                    mBobashopListTextView.setText(bobashopText);
//                    mBobashopEntryEditText.setText("");
//                }
//            }
//        });
//    }
//
//
//}
//

package com.hfad.bobaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hfad.bobaway.data.BobaWayRepo;
import com.hfad.bobaway.data.Status;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView searchResultsRV;
    private EditText searchBarET;
    private ProgressBar loadingIndicatorPB;
    private TextView errorMessageTV;

    private BobaWayAdapter bobaWayAdapter;

    private BobaWayAdapter viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBarET = (EditText)findViewById(R.id.et_bobashop_entry_box);

        searchResultsRV = findViewById(R.id.rv_search_results);

        searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRV.setHasFixedSize(true);

        viewModel = new ViewModelProvider(this).get(BobawayViewHolder.class);

        viewModel.getSearchResults().observe(this, new Observer<List<BobaWayRepo>>(){
            @Override
            public void onChanged(List<BobaWayRepo> gitHubRepos) {
                BobaWayAdapter.updateSearchResults(gitHubRepos);
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

        Button addLocationButton = (Button)findViewById(R.id.btn_bobashop_search);
        addLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locationText = searchBarET.getText().toString();
                if (!TextUtils.isEmpty(locationText)) {
                    searchBarET.setText("");
                }
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_search:
                return true;
            case R.id.nav_saved_repos:
                Intent savedReposIntent = new Intent(this, SavedReposActivity.class);
                startActivity(savedReposIntent);
                return true;
            case R.id.nav_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onSearchResultClicked(BobaWayRepo repo) {
        Intent intent = new Intent(this, RepoDetailActivity.class);
        intent.putExtra(RepoDetailActivity.EXTRA_GITHUB_REPO, repo);
        startActivity(intent);
    }

    private void doGitHubSearch(String searchQuery) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String sort = preferences.getString(
                getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_default)
        );
        String language = preferences.getString(
                getString(R.string.pref_language_key),
                getString(R.string.pref_language_default)
        );
        String user = preferences.getString(
                getString(R.string.pref_user_key), ""
        );
        boolean searchInName = preferences.getBoolean(
                getString(R.string.pref_in_name_key), true
        );
        boolean searchInDescription = preferences.getBoolean(
                getString(R.string.pref_in_description_key), true
        );
        boolean searchInReadme = preferences.getBoolean(
                getString(R.string.pref_in_readme_key), true
        );
        viewModel.loadSearchResults(searchQuery, sort, language, user, searchInName,
                searchInDescription, searchInReadme);
    }
}
