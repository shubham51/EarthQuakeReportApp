package com.example.android.quakereport;

/**
 * Created by shubham on 10-02-2017.
 */

public class Earthquake {
    private Double magnitude;
    private String place;
    private long timeInMilliSeconds;
    private String url;

    public Earthquake(Double magnitude,String place,long timeInMilliSeconds,String url){
        this.magnitude=magnitude;
        this.place=place;
        this.timeInMilliSeconds=timeInMilliSeconds;
        this.url=url;
    }
    public Double getMagnitude(){
        return magnitude;
    }
    public String getPlace(){
        return place;
    }
    public long getTimeInMilliSeconds(){
        return timeInMilliSeconds;
    }
    public String getUrl(){
        return url;
    }
}
