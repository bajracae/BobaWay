package com.hfad.bobaway.data;

import android.text.TextUtils;
import android.util.Log;

import com.hfad.bobaway.utils.YelpUtils;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BobaWayRepository implements BobaWayAsyncTask.Callback {
    private static final String TAG = BobaWayRepository.class.getSimpleName();
    private MutableLiveData<List<BobaWayItem>> mSearchResults;
    private MutableLiveData<Status> mLoadingStatus;
    private String mCurrentLocation;

    public BobaWayRepository() {
        mSearchResults = new MutableLiveData<>();
        mSearchResults.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);
    }

    public LiveData<List<BobaWayItem>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }


    @Override
    public void onSearchFinished(List<BobaWayItem> searchResults) {
        mSearchResults.setValue(searchResults);
        if (searchResults != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }

    private boolean shouldExecuteSearch(String location) {
        return !TextUtils.equals(location, mCurrentLocation);
    }

    public void loadSearchResults(String location) {

        if (shouldExecuteSearch(location)) {
            String url = YelpUtils.buildOpenYelpURL(location);
            Log.d("location here -> ", location);
            mSearchResults.setValue(null);
            Log.d(TAG, "executing search with url: " + url);
            mLoadingStatus.setValue(Status.LOADING);
            new BobaWayAsyncTask(this, url).execute();
        } else {
            Log.d(TAG, "using cached search results");
        }
    }

    public void loadDetailResults(String id){
        String url = YelpUtils.buildYelpDetailURL(id);
        Log.d(TAG, "executing search with url: " + url);
        mLoadingStatus.setValue(Status.LOADING);
        new BobaWayAsyncTask(this,url).execute();
    }
}
