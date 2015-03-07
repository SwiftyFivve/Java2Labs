package com.jordanweaver.j_weaver_interfaces_labthree;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/6/15.
 */
public class NetworkUtils extends AsyncTask<String, Void, ArrayList<DailyWeather>> {

    Context mContext;
    DisplayData theData;

    public NetworkUtils(Context mContext, DisplayData mData){
        this.mContext = mContext;
        this.theData = mData;
    }

    public interface DisplayData {
        public void updateArray(ArrayList<DailyWeather> _weather);
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }



    @Override
    protected ArrayList<DailyWeather> doInBackground(String... params) {


        String results = "";

        ArrayList<DailyWeather> allWeather = new ArrayList<>();

        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);


        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null) {
                Toast.makeText(mContext, "No Network Connection", Toast.LENGTH_LONG).show();
            } else {
                Log.i("Network", "Device is connected");
                if (info.isConnected()) {

                    try {
                        URL url = new URL(params[0]);

                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        InputStream is = connection.getInputStream();
                        results = IOUtils.toString(is);


                        if (results.equals("Error")) {
                            //Toast.makeText(, "Something Else went wrong.", Toast.LENGTH_LONG).show();
                        } else {
                            String high;
                            String low;
                            String conditions;
                            String weekday;
                            String fullDate;
                            String icon;

                            try {
                                JSONObject mainObject = new JSONObject(results);
                                JSONArray childrenArray = mainObject.getJSONObject("forecast")
                                        .getJSONObject("simpleforecast").getJSONArray("forecastday");
                                //Log.e("Network Connected", childrenArray.get(0)+"");

                                for (int i = 0; i < childrenArray.length(); i++) {
                                    JSONObject date = childrenArray.getJSONObject(i).getJSONObject("date");

                                    fullDate = date.getString("pretty");
                                    weekday = date.getString("weekday");

                                    JSONObject highForcast = childrenArray.getJSONObject(i).getJSONObject("high");
                                    JSONObject lowForcast = childrenArray.getJSONObject(i).getJSONObject("low");
                                    high = highForcast.getString("fahrenheit");
                                    low = lowForcast.getString("fahrenheit");

                                    conditions = childrenArray.getJSONObject(i).getString("conditions");
                                    icon = childrenArray.getJSONObject(i).getString("icon_url");

                                    DailyWeather dailyWeather = new DailyWeather(high, low, conditions, weekday, fullDate, icon);

                                    if (allWeather.size() == 0) {

                                        allWeather.add(0, dailyWeather);
                                    } else {
                                        allWeather.add(allWeather.size(), dailyWeather);
                                    }


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        return allWeather;


    }



    @Override
    protected void onPostExecute(ArrayList<DailyWeather> s) {
        super.onPostExecute(s);
        Log.e("My Array", s+"");
        theData.updateArray(s);


            //SAVE TO FILE!!!!!!!!!


    }



}
