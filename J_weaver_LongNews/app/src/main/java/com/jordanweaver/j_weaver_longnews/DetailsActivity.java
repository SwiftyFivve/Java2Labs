package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/24/15.
 */
public class DetailsActivity extends Activity {

    public static String Text_Extra_Source = "com.fullsail.android.Text_Extra_Source";
    public static String Text_Extra_Title = "com.fullsail.android.Text_Extra_Title";
    public static String Text_Extra_Summary = "com.fullsail.android.Text_Extra_Summary";
    public static String Text_Extra_SourceUrl = "com.fullsail.android.Text_Extra_SourceUrl";
    public static String Object_Extra_NewsObject = "com.fullsail.android.Object_Extra_NewsObject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.detailsContainer,
                    DetailsFragment.newInstance(), DetailsFragment.TAG).commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent startingIntent = getIntent();
        if(id == R.id.action_share){
            Intent share = new Intent(Intent.ACTION_SEND);
            share.putExtra(Intent.EXTRA_TEXT, "Title: " +
                    startingIntent.getStringExtra(Text_Extra_Title) + " URL: " +
                    startingIntent.getStringExtra(Text_Extra_SourceUrl));
            share.setType("text/plain");
            startActivity(share);

        }

        if(id == R.id.action_web){
            Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(startingIntent.getStringExtra(Text_Extra_SourceUrl)));
            startActivity(browser);

        }

        if(id == R.id.action_save){
            DataBaseHelper helper = new DataBaseHelper();

            ArrayList<NewsObject> favData = helper.loadArray("Favorites.txt", this);
            Serializable selectedObject = startingIntent.getSerializableExtra(Object_Extra_NewsObject);

            NewsObject saveObject = (NewsObject) selectedObject;

            Log.e("SAVED THIS TITLE", saveObject.title);

            if(favData.size() == 0){
                favData.add(0, saveObject);
            } else {
                favData.add(favData.size(), saveObject);
            }

            helper.saveArray(favData, "Favorites.txt", this);

        }



        return true;
    }
}
