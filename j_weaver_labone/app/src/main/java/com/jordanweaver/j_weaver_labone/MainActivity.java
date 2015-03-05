package com.jordanweaver.j_weaver_labone;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.io.StreamCorruptedException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    Context mContext;
    ListView listView;
    ArrayList<NewsClass> newsArrays;
    String selectedStation="";
    File newsFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        newsFile = new File ("newsFile");
        newsFile.mkdir();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        listView = (ListView) findViewById(R.id.listview);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                mContext,
                R.array.spinner_stuff,
                android.R.layout.simple_dropdown_item_1line
        );

        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                selectedStation = "";

                newsArrays = new ArrayList<>();
                ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

                switch (position){
                    case 0:
                        selectedStation = "cnn";
                        Log.e("String selected", selectedStation+"");
                        break;
                    case 1:
                        selectedStation = "foxnews";
                        Log.e("String selected", selectedStation+"");
                        break;
                    case 2:
                        selectedStation = "espn";
                        Log.e("String selected", selectedStation+"");
                        break;
                    case 3:
                        selectedStation = "dailyshow";
                        Log.e("String selected", selectedStation+"");
                        break;
                    case 4:
                        selectedStation = "cspan";
                        Log.e("String selected", selectedStation+"");
                        break;
                    case 5:
                        selectedStation = "bbc";
                        Log.e("String selected", selectedStation+"");
                        break;
                }

                if (manager !=null){
                    NetworkInfo info = manager.getActiveNetworkInfo();
                    //Log.e("Inside Network", info+"");
                    ArrayAdapter<NewsClass> arrayAdapter = new ArrayAdapter<>(
                            mContext,
                            android.R.layout.simple_list_item_1,
                            newsArrays
                    );
                    if (info == null){
                        newsArrays = new ArrayList<NewsClass>();
                        listView.setAdapter(arrayAdapter);

                        //Do something with no network. Run method to load saved data.
                        File externRoot = Environment.getExternalStorageDirectory();
                        //Log.e("Worked!", externRoot +"");
                        File saveFolder = new File(externRoot, selectedStation+".bin");


                        NewsClass obj = null;

                        Object myObj = null;


                        try {
                            FileInputStream fis = new FileInputStream(saveFolder);
                            Log.e("The fis", fis+"");
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            Log.e("The ois", ois+"");

                           myObj = (Object)ois.readObject();
                            obj = (NewsClass)myObj;

                            newsArrays.add(obj);


                            Log.e("The obj", newsArrays+"");
                            Log.e("The obj", obj.toString()+"");

//                            for (int i = 0; i<obj.title.length(); i++){
//                                Log.e("The Length", obj.title.length()+"");
//                                Log.e("SOMETHING WORKED!!", obj.title.toString() +"");
//                            }


                            listView.setAdapter(arrayAdapter);


                            ois.close();


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (StreamCorruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                        Log.e("No Connection", "DO SOMETHING HERE IDIOT!");
                    } else{
                        if (info.isConnected()){
                            //Do api connection here.
                            MyTask myTask = new MyTask();
                            myTask.execute("http://www.reddit.com/r/" +selectedStation+"/hot.json");
                            Log.e("Connected", selectedStation+"");
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    public class MyTask extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(mContext);
            dialog.setTitle("Loading Data");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Please Wait");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String results = "";

            try {
                URL url = new URL(params[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream is = connection.getInputStream();
                results = IOUtils.toString(is);

            } catch (MalformedURLException e) {
                e.printStackTrace();
                results = "Error";
            } catch (IOException e) {
                e.printStackTrace();
                results = "Error";
            }

            return results;


        }



        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s.equals("Error")) {
                Toast.makeText(MainActivity.this, "Something Else went wrong.", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject mainObject = new JSONObject(s);

                    JSONArray childrenArray = mainObject.getJSONObject("data").getJSONArray("children");
                    String title;

                    for (int i = 0; i < childrenArray.length(); i++) {
                        JSONObject childObject = childrenArray.getJSONObject(i).getJSONObject("data");
                        if (childObject.has("title")) {
                            title = childObject.getString("title");
                            //Log.e("Grabbing data", title+"");
                        } else {
                            title = "N/A";
                        }
                        newsArrays.add(new NewsClass(title));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //Save everything to local storage here


            File externRoot = Environment.getExternalStorageDirectory();
            File saveFolder = new File(externRoot, selectedStation+".bin");
            //File saveFolder = new File(externRoot, "text.txt");
            try {

                FileOutputStream fos = new FileOutputStream(saveFolder);
                ObjectOutputStream finalSaveOutPut = new ObjectOutputStream(fos);
                //fos.write("some text".getBytes());

                for (int i = 0; i < newsArrays.size(); i++) {
                    //Log.i("Title: ", newsArrays.get(i).title+"......");
                    finalSaveOutPut.write(newsArrays.get(i).title.getBytes());
                    Log.e("Success!!!", "You Win");
                }
                fos.close();

                }catch(FileNotFoundException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            try {
                FileInputStream fis = new FileInputStream(saveFolder);
                String temp = IOUtils.toString(fis);
                Log.i("Input Stream", "..."+temp);
                fis.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            ArrayAdapter<NewsClass> arrayAdapter = new ArrayAdapter<>(
                    mContext,
                    android.R.layout.simple_list_item_1,
                    newsArrays
            );
            listView.setAdapter(arrayAdapter);
            dialog.cancel();
        }
    }



}
