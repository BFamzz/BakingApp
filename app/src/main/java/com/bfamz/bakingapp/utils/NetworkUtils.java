package com.bfamz.bakingapp.utils;

import android.net.Uri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkUtils {

    public static final String RECIPE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public NetworkUtils() {

    }

    public static String httpRequest(URL apiUrl) {

        OkHttpClient newClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try {
            Response response = newClient.newCall(request).execute();
            return response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static URL recipeUrl() {
        URL url = null;

        Uri builtUri = Uri.parse(RECIPE_URL)
                .buildUpon()
                .build();

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}