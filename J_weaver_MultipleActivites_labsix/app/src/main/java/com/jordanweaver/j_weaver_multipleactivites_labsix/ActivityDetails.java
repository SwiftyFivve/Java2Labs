package com.jordanweaver.j_weaver_multipleactivites_labsix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jordanweaver on 3/17/15.
 */
public class ActivityDetails extends Activity {


    public static final String Text_Extra_1 = "com.fullsail.android.EXTRA_TEXT_1";
    public static final String Text_Extra_2 = "com.fullsail.android.EXTRA_TEXT_2";
    public static final String Integer_Extra = "com.fullsail.android.EXTRA_INT_VALUE";
    public static final String Position_Integer = "com.fullsail.android.Position_Integer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent startingIntent = getIntent();

        TextView firstText = (TextView) findViewById(R.id.firstText);
        TextView lastText = (TextView) findViewById(R.id.lastText);
        TextView ageText = (TextView) findViewById(R.id.ageText);

        if(startingIntent.hasExtra(Text_Extra_1) && startingIntent.hasExtra(Text_Extra_2) &&
                startingIntent.hasExtra(Integer_Extra)){

            firstText.setText(startingIntent.getStringExtra(Text_Extra_1));
            lastText.setText(startingIntent.getStringExtra(Text_Extra_2));
            int age = startingIntent.getIntExtra(Integer_Extra, -1);
            ageText.setText(age+"");

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
        if (id == R.id.deleteButton) {

            DataHelperClass helperClass = new DataHelperClass(this);
            JSONArray allProfiles = helperClass.onLoad();
            int position = startingIntent.getIntExtra(Position_Integer, -1);

            if(position != -1) {

                allProfiles.remove(position);
                helperClass.onSave(allProfiles);
                finish();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
