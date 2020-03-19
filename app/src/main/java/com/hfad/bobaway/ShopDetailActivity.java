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

import com.hfad.bobaway.data.BobaWayItem;
import com.hfad.bobaway.utils.YelpUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOBAWAY_REPO = "YelpRepo";

    //    private BobaWayRepo repo;
    private BobaWayItem repo;
    private Button mLeaveReview;
    private TextView mRestHours;
    private TextView mRestReviews;
    private TextView mRestTitle;
    private TextView mRestAddr;
    private RatingBar mRestRB;

    private BobaWayViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_restaurant);

        mLeaveReview = findViewById(R.id.b_leave_a_review);
        mRestAddr = findViewById(R.id.tv_restaurant_address);
        mRestHours = findViewById(R.id.tv_restaurant_hours);
        mRestReviews = findViewById(R.id.tv_restaurant_review);
        mRestTitle = findViewById(R.id.tv_restaurant_name);
        mRestRB = findViewById(R.id.rb_restaurant_rating);
        viewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(BobaWayViewModel.class);

        Intent intent = getIntent();
        repo = (BobaWayItem)intent.getSerializableExtra(EXTRA_BOBAWAY_REPO);
        doYelpSearch(repo.id);
        Log.d("detail","repo: " + repo.location.address1);
        Log.d("detail","repo: " + repo.id);
        Log.d("detail","repo: " + repo.name);
        mRestTitle.setText(repo.name);
        mRestAddr.setText(addressFromRepo(repo.location));
        if(repo.hours != null){
            mRestHours.setText(hoursFromRepo(repo.hours));
        }
        else mRestHours.setText("Check website for hours");

        mLeaveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RepoDetailActivity","Leave a review!");
                Intent intent = new Intent(view.getContext(), ReviewActivity.class);
                intent.putExtra(YelpUtils.EXTRA_ITEM, repo);
                startActivity(intent);
            }
        });
    }

    private String hoursFromRepo(BobaWayItem.BobaWayItem_OpenHours hours){

        ArrayList<String> hoursArrayList = new ArrayList<>();
        String finalDate = "";
        Log.d("detail","hours: " + hours);

        for(int i = 0; i < hours.open.length; i ++){
            String start = convertToTime(hours.open[0].start);
            String end = convertToTime(hours.open[0].end);
            String date = convertToDay(hours.open[0].day);
            String dateFormat = date + ": " + start + " - " + end;
            hoursArrayList.add(dateFormat);
        }

        for(int i = 0; i < hoursArrayList.size(); i ++){
            finalDate = finalDate + hoursArrayList.get(i) + "\n";
        }
        return finalDate;
    }

    private String convertToTime(String time){
        String dateString = "N/A";
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
            Date date = sdf.parse(time);
            dateString = sdf.format(date);

        } catch (final ParseException e) {
            e.printStackTrace();
        }

        return dateString;
    }

    private String convertToDay(int day){
        switch (day){
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            case 5:
                return "Saturday";
            case 6:
                return "Sunday";
            default:
                return "N/A";
        }
    }

    private String addressFromRepo(BobaWayItem.BobaWayItem_Location location){
        return location.address1 + ", " + location.city + ", " + location.state + ", "
                + location.country + ". " + location.zip_code;
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

    private void doYelpSearch(String id){
        viewModel.loadDetailResults(id);
    }

}
