package com.hfad.bobaway.data;

import android.os.AsyncTask;

import com.hfad.bobaway.utils.NetworkUtils;
import com.hfad.bobaway.utils.YelpUtils;

import java.io.IOException;
import java.util.List;

public class BobaWayAsyncTask extends AsyncTask<String, Void, String> {
    private Callback mCallback;

    public interface Callback {
        void onSearchFinished(List<BobaWayRepo> searchResults);
    }

    public BobaWayAsyncTask(Callback callback){
        mCallback = callback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String searchResults = null;
        try {
            searchResults = NetworkUtils.doHttpGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    @Override
    protected void onPostExecute(String s) {
        List<BobaWayRepo> searchResults = null;
        if (s != null) {
            searchResults = YelpUtils.parseYelpResults(s);
        }
        mCallback.onSearchFinished(searchResults);
    }
}
