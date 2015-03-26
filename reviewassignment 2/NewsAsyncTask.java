package java2.devaunteledee.com.reviewassignment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by devaunteledee on 3/25/15.
 */
public class NewsAsyncTask extends AsyncTask<String,Void,String> {
    private Context mContext;
    String jsonString;
    ArrayList<NYTimes> newsArray;
    String newsTitle;
    String date;
    String line;
    public NewsAsyncTask(Context context,Adapterhelp _Adapterhelp) {
        mContext = context;
        mUpdater = _Adapterhelp;
    }
    @Override
    protected void onPreExecute() {
        ProgressDialog progressDialog;
//
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setProgressStyle((ProgressDialog.STYLE_HORIZONTAL));
//       FeedFragment feedFragment =  FeedFragment.newInstance();
//        progressDialog.setMessage("Loading " +feedFragment.feedSpinner.getSelectedItem().toString() + "...");
//        progressDialog.setIndeterminate(true);
//        progressDialog.setProgressNumberFormat("Copyright (c) 2014 The New York Times Company. All Rights Reserved.");
//        progressDialog.setProgressPercentFormat(null);
//        progressDialog.setCancelable(false);
//        progressDialog.show();

    }

    @Override
    protected String doInBackground(String... params) {

        try {
             URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            jsonString = IOUtils.toString(inputStream);
            inputStream.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public interface Adapterhelp{
        public void updateAdapter(ArrayList _ARRAYLIST);
    }
    public Adapterhelp mUpdater;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try{
            newsArray = new ArrayList<NYTimes>();
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray informationOfJson = jsonObject.getJSONArray("results");

            for ( int i = 0; i < informationOfJson.length(); i++){
                JSONObject newsArticles = informationOfJson.getJSONObject(i);
                if (newsArticles.has("title")){
                    newsTitle = newsArticles.getString("title");
                }
                if (newsArticles.has("published_date")){
                    date = newsArticles.getString("published_date");
                }
                if (newsArticles.has("byline")){
                    line = newsArticles.getString("byline");
                }
                newsArray = new ArrayList<>();
                newsArray.add(new NYTimes(newsTitle,date,line));
                mUpdater.updateAdapter(newsArray);
                Log.i("mUpdater", "mUpdater" + mUpdater);

//                Log.i("NEWS ARRAY","NEWSARRAY" + newsArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
