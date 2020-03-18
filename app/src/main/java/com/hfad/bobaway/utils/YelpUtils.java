package com.hfad.bobaway.utils;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.hfad.bobaway.data.BobaWayRepo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class YelpUtils {
    private final static String YELP_BASE_URL = "https://api.yelp.com/v3/businesses/search";
    private final static String YELP_QUERY_PARAM = "Authorization";
    private final static String YELP_KEY = "Bearer RX0RyDW9JYbHkUrMKp3REkF51-YsQbZbSagBgXZ4HgpZn2WMPBwXat-LzkxiRHZkCEKbue5Yd2qarbhpxm_Ib3DOpF9dIIaLwc5-I2YQs8V4de5ATm8YJbJaQwtsXnYx";
    private final static String YELP_LOCATION = "location";

    @NotNull
    public static String buildOpenWeatherURL(String query) {
        return Uri.parse(YELP_BASE_URL).buildUpon()
                .appendQueryParameter(YELP_QUERY_PARAM, YELP_KEY)
                .appendQueryParameter(YELP_LOCATION, "Corvallis,OR,USA")
                .build()
                .toString();
    }

    public static class YelpResults {
        ArrayList<BobaWayRepo> businesses;
    }

    @Nullable
    public static ArrayList<BobaWayRepo> parseYelpResults(String json) {
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
