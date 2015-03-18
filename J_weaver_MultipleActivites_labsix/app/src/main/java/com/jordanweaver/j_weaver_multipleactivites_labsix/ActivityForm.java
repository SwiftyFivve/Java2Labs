package com.jordanweaver.j_weaver_multipleactivites_labsix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jordanweaver on 3/16/15.
 */
public class ActivityForm extends Activity {

    public static String Text_Extra_1 = "com.fullsail.android.EXTRA_TEXT_1";
    public static String Text_Extra_2 = "com.fullsail.android.EXTRA_TEXT_2";
    public static String Integer_Extra = "com.fullsail.android.EXTRA_INT_VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Intent startingIntent = getIntent();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        findViewById(R.id.add_action);

        if (id == R.id.saveButton) {
            EditText fName = (EditText) findViewById(R.id.firstInput);
            EditText lName = (EditText) findViewById(R.id.lastInput);
            EditText age = (EditText) findViewById(R.id.ageInput);

            String firstName = fName.getText().toString();
            String lastName = lName.getText().toString();
            String mAge = age.getText().toString();



            if(!mAge.trim().equals("")) {
                int finalAge = Integer.parseInt(mAge);
                if (!firstName.trim().equals("") && !lastName.trim().equals("") && finalAge>0) {
                    Intent intent = new Intent();
                    DataHelperClass helperClass = new DataHelperClass(this);

                    JSONObject newProfile = new JSONObject();

                    Profile profileHelper = new Profile();

                    JSONArray saveArray = null;
                    try {

                        newProfile = profileHelper.convertToJson(firstName, lastName, finalAge);

                        saveArray = helperClass.onLoad();

                        if (saveArray!=null){
                            saveArray.put(saveArray.length(), newProfile);
                            helperClass.onSave(saveArray);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            saveArray = new JSONArray();
                            saveArray.put(0, newProfile);
                            helperClass.onSave(saveArray);
                            setResult(RESULT_OK, intent);
                            finish();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return true;
                } else {
                    Toast.makeText(this, "Please make sure to complete form correctly.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Please enter an age above 0", Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }


}
