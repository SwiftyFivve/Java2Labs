package com.jordanweaver.j_weaver_multipleactivites_labsix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {

    public static final int REQUEST_ACTIVITY_FORM  = 0x0001;

    public static final String ACTION_DETAILS = "com.fullsail.android.ACTION_VIEW_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView peopleList = (ListView) findViewById(R.id.peopleList);

        final Context mContext = this;


        ArrayList<String> profileNames = new ArrayList<>();

        DataHelperClass helperClass = new DataHelperClass(this);

        JSONArray profiles = helperClass.onLoad();

        try {
            for(int i=0; i<profiles.length();i++) {
                JSONObject profile = profiles.getJSONObject(i);
                profileNames.add(profileNames.size(), profile.getString("first"));
                //Log.e("Worked", profile.getString("first"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, profileNames);

        peopleList.setAdapter(nameAdapter);


        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ACTION_DETAILS);


                DataHelperClass helperClass = new DataHelperClass(mContext);

                JSONArray profiles = helperClass.onLoad();

                JSONObject profile = null;
                try {
                    profile = profiles.getJSONObject(position);

                    intent.putExtra(ActivityDetails.Text_Extra_1, profile.getString("first"));
                    intent.putExtra(ActivityDetails.Text_Extra_2, profile.getString("last"));
                    intent.putExtra(ActivityDetails.Integer_Extra, profile.getInt("age"));
                    intent.putExtra(ActivityDetails.Position_Integer, position);

                    Intent chooser = Intent.createChooser(intent, "Select Application");

                    startActivity(chooser);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<String> profileNames = new ArrayList<>();

        DataHelperClass helperClass = new DataHelperClass(this);

        JSONArray profiles = helperClass.onLoad();

        try {
            for(int i=0; i<profiles.length();i++) {
                JSONObject profile = profiles.getJSONObject(i);
                profileNames.add(profileNames.size(), profile.getString("first"));
                //Log.e("Worked", profile.getString("first"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, profileNames);

        ListView peopleList = (ListView) findViewById(R.id.peopleList);
        peopleList.setAdapter(nameAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_action) {
            Intent intent = new Intent(this, ActivityForm.class);
            startActivityForResult(intent, REQUEST_ACTIVITY_FORM);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data !=null){

            ArrayList<String> profileNames = new ArrayList<>();

            DataHelperClass helperClass = new DataHelperClass(this);

            JSONArray profiles = helperClass.onLoad();

            try {
                for(int i=0; i<profiles.length();i++) {
                    JSONObject profile = profiles.getJSONObject(i);
                    profileNames.add(profileNames.size(), profile.getString("first"));
                    //Log.e("Worked", profile.getString("first"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, profileNames);

            ListView peopleList = (ListView) findViewById(R.id.peopleList);
            peopleList.setAdapter(nameAdapter);
        }
    }




}
