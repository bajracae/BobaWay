package com.hfad.bobaway.data;

import android.os.AsyncTask;

import com.hfad.bobaway.utils.NetworkUtils;
import com.hfad.bobaway.utils.YelpUtils;

import java.io.IOException;
import java.util.List;

public class BobaWayAsyncTask extends AsyncTask<Void, Void, String> {
    private Callback mCallback;
    private String mUrl;

    public interface Callback {
        void onSearchFinished(List<BobaWayItem> searchResults);
    }

    public BobaWayAsyncTask(Callback callback, String url){
        mCallback = callback;
        mUrl = url;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String searchResults = null;
        try {
            searchResults = NetworkUtils.doHTTPGet(mUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    @Override
    protected void onPostExecute(String s) {
        List<BobaWayItem> searchResults = null;
        if (s != null) {
            searchResults = YelpUtils.parseYelpResults(s);
        }
        mCallback.onSearchFinished(searchResults);
    }
}
