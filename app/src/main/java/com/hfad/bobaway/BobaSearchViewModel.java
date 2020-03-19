package com.hfad.bobaway;

import android.util.Log;

import com.hfad.bobaway.utils.NetworkUtils;
import com.hfad.bobaway.utils.YelpUtils;

import java.io.IOException;

import androidx.lifecycle.ViewModel;

public class BobaSearchViewModel extends ViewModel{
    public void loadSearchResults(String location) throws IOException {
        Log.d("main class called", NetworkUtils.doHTTPGet(YelpUtils.buildOpenYelpURL(location)));

    }
}
