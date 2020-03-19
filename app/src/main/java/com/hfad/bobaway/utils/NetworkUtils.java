package com.hfad.bobaway.utils;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {
    private static final OkHttpClient mHTTPClient = new OkHttpClient();
    private final static String HTTP_TOKEN = "Authorization";
    private final static String HTTP_AUTHORIZATION_KEY = "Bearer RX0RyDW9JYbHkUrMKp3REkF51-YsQbZbSagBgXZ4HgpZn2WMPBwXat-LzkxiRHZkCEKbue5Yd2qarbhpxm_Ib3DOpF9dIIaLwc5-I2YQs8V4de5ATm8YJbJaQwtsXnYx";

    public static String doHTTPGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).addHeader(HTTP_TOKEN,HTTP_AUTHORIZATION_KEY).build();
        Log.d("NetworkUtils request: ", request.header(HTTP_TOKEN));
        Response response = mHTTPClient.newCall(request).execute();
//        Log.d("NetworkUtils response: " , response.body().string());
        try {
            return response.body().string();
        } finally {
            response.close();
        }
    }
}
