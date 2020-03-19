package com.hfad.bobaway.utils;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.hfad.bobaway.data.BobaWayItem;
import com.hfad.bobaway.data.BobaWayRepo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class YelpUtils {
    public final static String EXTRA_ITEM = "ExtraItem";

    private final static String YELP_BASE_URL = "https://api.yelp.com/v3/businesses/search";
    private final static String YELP_SINGLE_BUSINESS_URL = "https://api.yelp.com/v3/businesses";
    private final static String YELP_LOCATION = "location";
    private final static String YELP_ID = "id";
    private final static String YELP_ALIAS = "categories";
    private final static String YELP_ALIAS_DEFAULT = "bubbletea, Bubble Tea";



    public static String buildOpenYelpURL(String location) {
        Log.d("YelpUtils","location: " + location);
        return Uri.parse(YELP_BASE_URL).buildUpon()
                .appendQueryParameter(YELP_LOCATION, location)
                .appendQueryParameter(YELP_ALIAS, YELP_ALIAS_DEFAULT)
                .build()
                .toString();
    }

    public static String buildYelpDetailURL(String id) {

        return Uri.parse(YELP_SINGLE_BUSINESS_URL).buildUpon()
                .appendQueryParameter(YELP_ID,id)
                .build()
                .toString();
    }

    public static class YelpResults {
        ArrayList<BobaWayItem> businesses;
    }

    @Nullable
    public static ArrayList<BobaWayItem> parseYelpResults(String json) {
        Log.d("Returned JSON: ", json);
        Gson gson = new Gson();
        YelpResults results = gson.fromJson(json, YelpResults.class);
        if (results != null && results.businesses != null) {
            Log.d(TAG, "results not null and results.list not null");
            return results.businesses;
        } else {
            return null;
        }
    }


}
