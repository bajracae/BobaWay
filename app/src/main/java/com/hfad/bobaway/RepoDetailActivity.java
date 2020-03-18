package com.hfad.bobaway;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hfad.bobaway.data.BobaWayRepo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RepoDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BOBAWAY_REPO = "YelpRepo";

    private BobaWayRepo repo;
//    private boolean isSaved = false;

//    private SavedReposViewModel savedReposViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

//        mViewModel = new ViewModelProvider(
//                this,
//                new ViewModelProvider.AndroidViewModelFactory(getApplication())
//        ).get(SavedReposViewModel.class);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(EXTRA_BOBAWAY_REPO)) {
            repo = (BobaWayRepo)intent.getSerializableExtra(EXTRA_BOBAWAY_REPO);

            TextView repoNameTV = findViewById(R.id.tv_repo_name);
            repoNameTV.setText(repo.businesses[0].name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_view_repo:
//                viewRepoOnWeb();
//                return true;
            case R.id.action_share:
                shareRepo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void shareRepo() {
        if (repo != null) {
            String shareText = getString(R.string.share_repo_text);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            shareIntent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(shareIntent, null);
            startActivity(chooserIntent);
        }
    }
}
