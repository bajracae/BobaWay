package com.hfad.bobaway.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BobaWayRepository implements BobaWayAsyncTask.Callback {
    private static final String TAG = BobaWayRepository.class.getSimpleName();
    private MutableLiveData<List<BobaWayRepo>> mSearchResults;
    private MutableLiveData<Status> mLoadingStatus;

    public BobaWayRepository() {
        mSearchResults = new MutableLiveData<>();
        mSearchResults.setValue(null);

        mLoadingStatus = new MutableLiveData<>();
        mLoadingStatus.setValue(Status.SUCCESS);
    }

    public LiveData<List<BobaWayRepo>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }


    @Override
    public void onSearchFinished(List<BobaWayRepo> searchResults) {
        mSearchResults.setValue(searchResults);
        if (searchResults != null) {
            mLoadingStatus.setValue(Status.SUCCESS);
        } else {
            mLoadingStatus.setValue(Status.ERROR);
        }
    }
}
