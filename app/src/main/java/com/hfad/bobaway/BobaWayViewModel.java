package com.hfad.bobaway;

import com.hfad.bobaway.data.BobaWayItem;
import com.hfad.bobaway.data.BobaWayRepo;
import com.hfad.bobaway.data.BobaWayRepository;
import com.hfad.bobaway.data.Status;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class BobaWayViewModel extends ViewModel {
    private BobaWayRepository mRepository;
    private LiveData<List<BobaWayItem>> mSearchResults;
    private LiveData<Status> mLoadingStatus;

    public BobaWayViewModel() {
        mRepository = new BobaWayRepository();
        mSearchResults = mRepository.getSearchResults();
        mLoadingStatus = mRepository.getLoadingStatus();
    }

    public void loadSearchResults(String location) {
        mRepository.loadSearchResults(location);
    }

    public void loadDetailResults(String id){
        mRepository.loadDetailResults(id);
    }

    public LiveData<List<BobaWayItem>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }
}
