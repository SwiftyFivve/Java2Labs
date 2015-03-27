package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/24/15.
 */
public class NetworkUtils extends AsyncTask<String, Void, ArrayList<NewsObject> > {

    Context mContext;
    DisplayData theData;

    public NetworkUtils(Context mContext, DisplayData mData) {
        this.mContext = mContext;
        this.theData = mData;
    }

    public interface DisplayData {
        public void updateArray(ArrayList<NewsObject> _news);
    }


    @Override
    protected ArrayList<NewsObject> doInBackground(String... params) {

        String results = "";

        ArrayList<NewsObject> newsArray = new ArrayList<>();

        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService
                (Context.CONNECTIVITY_SERVICE);


        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info == null) {
                Log.e("No", "NETWORK");
            } else {
                if (info.isConnected()) {
                    if (info.isConnected()) {

                        try {
                            URL url = new URL(params[0]);

                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                            InputStream is = connection.getInputStream();
                            results = IOUtils.toString(is);

                            if (results.equals("Error")) {

                            } else {

                                String _source;
                                String _title;
                                String _summary;
                                String _sourceUrl;

                                try {
                                    JSONObject mainObject = new JSONObject(results);
                                    JSONArray articles = mainObject.getJSONArray("articles");
                                    for(int i = 0; i<articles.length();i++){
                                        JSONObject loop = articles.getJSONObject(i);
                                        _source = loop.getString("source");
                                        _title = loop.getString("title");
                                        _summary = loop.getString("summary");
                                        _sourceUrl = loop.getString("url");


                                        NewsObject newsObject = new NewsObject(_source, _title, _summary, _sourceUrl);

                                        if(newsArray.size() == 0){
                                            newsArray.add(0, newsObject);
                                        } else {
                                            newsArray.add(newsArray.size(), newsObject);
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


        }

        return newsArray;
    }

    @Override
    protected void onPostExecute(ArrayList<NewsObject> newsObjects) {
        super.onPostExecute(newsObjects);

        if(newsObjects.size() != 0) {
            theData.updateArray(newsObjects);
        } else {
            Log.e("Network failed", "Please try again");


            ImageView image = new ImageView(mContext);
            image.setImageResource(R.drawable.jordanceley2);


            AlertDialog.Builder celeyAlert = new AlertDialog.Builder(mContext).setView(image);
            celeyAlert.setTitle("Dear Celey:");
            celeyAlert.setMessage("Feedzilla is slow sometimes, please retry");

            celeyAlert.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    cancel(true);
                    doInBackground("http://api.feedzilla.com/v1/categories/3/subcategories/65/articles.json");


                }
            });

            celeyAlert.show();

        }

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        cancel(true);
    }
}
