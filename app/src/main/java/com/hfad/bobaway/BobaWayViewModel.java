package com.hfad.bobaway;

import com.hfad.bobaway.data.BobaWayRepo;
import com.hfad.bobaway.data.Status;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class BobaWayViewModel extends ViewModel {
    private GitHubSearchRepository mRepository;
    private LiveData<List<BobaWayRepo>> mSearchResults;
    private LiveData<Status> mLoadingStatus;

    public BobaWayViewModel() {
        mRepository = new GitHubSearchRepository();
        mSearchResults = mRepository.getSearchResults();
        mLoadingStatus = mRepository.getLoadingStatus();
    }


    public void loadSearchResults(String query, String sort, String language, String user,
                                  boolean searchInName, boolean searchInDescription,
                                  boolean searchInReadme) {
        mRepository.loadSearchResults(query, sort, language, user, searchInName,
                searchInDescription, searchInReadme);
    }

    public LiveData<List<BobaWayRepo>> getSearchResults() {
        return mSearchResults;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }
}
