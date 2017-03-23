package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by shubham on 12-02-2017.
 */

public class EarthQuakeLoader extends AsyncTaskLoader<List<Earthquake>>{
    private static final String LOG_TAG = EarthQuakeLoader.class.getName();
    private String url;
    public EarthQuakeLoader(Context context,String url){
        super(context);
        this.url=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(url==null || TextUtils.isEmpty(url)) return null;
        String jsonResponse = null;
        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG,"IO Exception occured");
        }
        List<Earthquake> earthquakes=QueryUtils.extractEarthquakes(jsonResponse);
        return earthquakes;
    }
}
