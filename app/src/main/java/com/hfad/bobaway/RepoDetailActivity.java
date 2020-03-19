package com.hfad.bobaway;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hfad.bobaway.data.BobaWayRepo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RepoDetailActivity extends AppCompatActivity {
    public static final String EXTRA_BOBAWAY_REPO = "YelpRepo";

    private BobaWayRepo repo;
    private Button mLeaveReview;
    private TextView mRestHours;
    private TextView mRestReviews;
    private TextView mRestTitle;
    private TextView mRestAddr;
    private RatingBar mRestRB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_restaurant);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra(EXTRA_BOBAWAY_REPO)) {
            repo = (BobaWayRepo)intent.getSerializableExtra(EXTRA_BOBAWAY_REPO);

            TextView repoNameTV = findViewById(R.id.tv_restaurant_name);
            repoNameTV.setText(repo.businesses[0].name);
        }

        mLeaveReview = findViewById(R.id.b_leave_a_review);
        mRestAddr = findViewById(R.id.tv_restaurant_address);
        mRestHours = findViewById(R.id.tv_restaurant_hours);
        mRestReviews = findViewById(R.id.tv_restaurant_review);
        mRestTitle = findViewById(R.id.tv_restaurant_name);
        mRestRB = findViewById(R.id.rb_restaurant_rating);

        mLeaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RepoDetailActivity","Leave a review!");
            }
        });
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
