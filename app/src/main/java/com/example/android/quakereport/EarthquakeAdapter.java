package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import org.w3c.dom.Text;
import android.support.v4.content.ContextCompat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shubham on 10-02-2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    List<Earthquake> earthquakes;
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes){
        super(context,0,earthquakes);
        this.earthquakes=earthquakes;
    }
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private String[] getLocationArray(String place){
        String[] split=new String[2];
        if(place.contains("of")){
            split=place.split("(?<=of)");
        }
        else{
            split[0]="Near the";
            split[1]=place;
        }
       return split;
    }
    private int getMagnitudeColor(Double mag){
        int magColor;
        switch((int)Math.floor(mag)){
            case 0:
            case 1:
                magColor=R.color.magnitude1;
                break;
            case 2:
                magColor=R.color.magnitude2;
                break;
            case 3:
                magColor=R.color.magnitude3;
                break;
            case 4:
                magColor=R.color.magnitude4;
                break;
            case 5:
                magColor=R.color.magnitude5;
                break;
            case 6:
                magColor=R.color.magnitude6;
                break;
            case 7:
                magColor=R.color.magnitude7;
                break;
            case 8:
                magColor=R.color.magnitude8;
                break;
            case 9:
                magColor=R.color.magnitude9;
                break;
            default:
                magColor=R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(),magColor);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView=convertView;
        if(listItemView==null){
            listItemView=LayoutInflater.from(getContext()).inflate(R.layout.layout_row,parent,false);
        }
        Earthquake current=earthquakes.get(position);
        TextView magnitude_view=(TextView)listItemView.findViewById(R.id.textView_mag);
        GradientDrawable magnitudeCircle=(GradientDrawable) magnitude_view.getBackground();
        int magnitudeColor=getMagnitudeColor(current.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        Double magnitude=current.getMagnitude();
        DecimalFormat formatter=new DecimalFormat("0.0");
        magnitude_view.setText(formatter.format(magnitude));
        TextView place_view=(TextView)listItemView.findViewById(R.id.textView_place);
        TextView place_offset_view=(TextView)listItemView.findViewById(R.id.textView_place_offset);
        String[] place=getLocationArray(current.getPlace());
        place_offset_view.setText(place[0]);
        place_view.setText(place[1]);
        TextView date_view=(TextView)listItemView.findViewById(R.id.textView_date);
        Date dateObject=new Date(current.getTimeInMilliSeconds());
        String formatted_date=formatDate(dateObject);
        date_view.setText(formatted_date);
        TextView time_view=(TextView)listItemView.findViewById(R.id.textView_time);
        String formatted_time=formatTime(dateObject);
        time_view.setText(formatted_time);
        return listItemView;
    }
}
