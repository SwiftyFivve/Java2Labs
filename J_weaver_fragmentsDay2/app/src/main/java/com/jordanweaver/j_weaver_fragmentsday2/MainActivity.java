package com.jordanweaver.j_weaver_fragmentsday2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainActivity extends Activity {


    boolean mDualPane;
    boolean mOrientation;

    public static final String ARG_NAME = "ARG_NAME";
    private static final String ARG_TEAM = "ARG_TEAM";
    private static final String ARG_DIVISION = "ARG_DIVISION";
    private static final String ARG_LAST = "ARG_LAST";



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner staffSpinner = (Spinner) findViewById(R.id.formSpinner);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button refreshButton = (Button) findViewById(R.id.refreshDetails);


        //mOrientation = false;

        if(savedInstanceState != null){
            mOrientation = true;
            //String nameText = savedInstanceState.getString(ARG_NAME);
            //String teamText = savedInstanceState.getString(ARG_TEAM);
           // mRun = true;

            //name = nameText;

//            EditText nameInput = (EditText)findViewById(R.id.staffNameInput);
//            nameInput.setText(nameText);

        }
        else {
            mOrientation = false;
        }

        FragmentManager mgr = getFragmentManager();

        if(mgr.findFragmentByTag(OwnerFormFragment.TAG) != null){

        }


        if(findViewById(R.id.detailsContainer) != null){
            mDualPane = true;

            DetailsFragment detsFrag = DetailsFragment.detailsInstance(false);
            getFragmentManager().beginTransaction().replace(R.id.detailsContainer,detsFrag,
                    DetailsFragment.TAG).commit();

        } else {
            mDualPane = false;

        }


        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_stuff,
                android.R.layout.simple_dropdown_item_1line
        );

        staffSpinner.setAdapter(spinnerAdapter);

        staffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                    switch (position) {
                        case 0:
                            if(savedInstanceState == null) {
                                OwnerFormFragment ownerFrag = OwnerFormFragment.formInstance
                                        ("Transaction Complete");
                                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                                        ownerFrag, OwnerFormFragment.TAG).commit();
                            }
                            break;

                        case 1:
                            if(savedInstanceState == null) {
                                CoachFormFragment coachFrag = CoachFormFragment.formInstance
                                        ("Transaction Complete");
                                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                                        coachFrag, CoachFormFragment.TAG).commit();
                            }
                            break;
                        case 2:
                            if(savedInstanceState == null) {
                                PlayerFormFragment playerFrag = PlayerFormFragment.formInstance
                                        ("Transaction Complete");
                                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                                        playerFrag, PlayerFormFragment.TAG).commit();
                            }
                            break;
                    }

                mOrientation = false;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final Intent intent = new Intent(this, DetailsActivity.class);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mDualPane){
                    FragmentManager mgr = getFragmentManager();

                    DetailsFragment detailsFragment = (DetailsFragment) mgr.findFragmentByTag
                            (DetailsFragment.TAG);


                    //Add boolean saying this is coming from a reload, thing will reload the data
                    detailsFragment.loadData();


                } else {

                    findViewById(R.id.detailsContainer).setVisibility(View.VISIBLE);

                    startActivity(intent);

                }

            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int caseCheck = staffSpinner.getSelectedItemPosition();
                FragmentManager mgr = getFragmentManager();



                    switch (caseCheck) {
                        case 0:
                            OwnerFormFragment ownerFrag = (OwnerFormFragment)
                                    mgr.findFragmentByTag(OwnerFormFragment.TAG);
                            ownerFrag.checkArray();
                            break;

                        case 1:
                            CoachFormFragment coachFrag = (CoachFormFragment)
                                    mgr.findFragmentByTag(CoachFormFragment.TAG);
                            coachFrag.checkArray();
                            break;

                        case 2:
                            PlayerFormFragment playerFrag = (PlayerFormFragment)
                                    mgr.findFragmentByTag(PlayerFormFragment.TAG);
                            playerFrag.checkArray();
                            break;
                    }


            }
        });


        OrientationEventListener checkOrientation = new OrientationEventListener(this,
                SensorManager.SENSOR_DELAY_UI) {
            @Override
            public void onOrientationChanged(int orientation) {

                if(getResources().getConfiguration().orientation ==
                        Configuration.ORIENTATION_PORTRAIT){

                    mDualPane = false;


                    findViewById(R.id.detailsContainer).setVisibility(View.GONE);


                } else {
                    findViewById(R.id.detailsContainer).setVisibility(View.VISIBLE);

                }

            }
        };

        checkOrientation.enable();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText nameText = (EditText) findViewById(R.id.staffNameInput);
        EditText teamText = (EditText) findViewById(R.id.teamNameInput);
        EditText divisionText = (EditText) findViewById(R.id.divisionInput);

        String name = nameText.getText().toString();

//        switch (findViewById(R.id.formSpinner).)
//        EditText lastText = (EditText) findViewById(R.id.inp)

        outState.putString(ARG_NAME, name);

    }
}
