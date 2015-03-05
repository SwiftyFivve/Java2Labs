package com.jordanweaver.j_weaver_fragmentsday2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner staffSpinner = (Spinner) findViewById(R.id.formSpinner);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button refreshButton = (Button) findViewById(R.id.refreshDetails);



        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_stuff,
                android.R.layout.simple_dropdown_item_1line
        );

        staffSpinner.setAdapter(spinnerAdapter);

        staffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        OwnerFormFragment ownerFrag = OwnerFormFragment.formInstance("Transaction Complete");
                        getFragmentManager().beginTransaction().replace(R.id.formContainer,ownerFrag, OwnerFormFragment.TAG).commit();
                        break;
                    case 1:
                        CoachFormFragment coachFrag = CoachFormFragment.formInstance("Transaction Complete");
                        getFragmentManager().beginTransaction().replace(R.id.formContainer,coachFrag, CoachFormFragment.TAG).commit();
                        break;
                    case 2:
                        PlayerFormFragment playerFrag = PlayerFormFragment.formInstance("Transaction Complete");
                        getFragmentManager().beginTransaction().replace(R.id.formContainer,playerFrag, PlayerFormFragment.TAG).commit();
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (savedInstanceState == null){
            DetailsFragment detsFrag = DetailsFragment.detailsInstance();
            getFragmentManager().beginTransaction().replace(R.id.detailsContainer,detsFrag, DetailsFragment.TAG).commit();
        }


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager mgr = getFragmentManager();

                DetailsFragment detailsFragment = (DetailsFragment)mgr.findFragmentByTag(DetailsFragment.TAG);

                detailsFragment.loadData();

            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int caseCheck = staffSpinner.getSelectedItemPosition();
                FragmentManager mgr = getFragmentManager();


                switch (caseCheck){
                    case 0:
                        OwnerFormFragment ownerFrag = (OwnerFormFragment)mgr.findFragmentByTag(OwnerFormFragment.TAG);
                        ownerFrag.checkArray();
                        break;

                    case 1:
                        CoachFormFragment coachFrag = (CoachFormFragment)mgr.findFragmentByTag(CoachFormFragment.TAG);
                        coachFrag.checkArray();
                        break;

                    case 2:
                        PlayerFormFragment playerFrag = (PlayerFormFragment)mgr.findFragmentByTag(PlayerFormFragment.TAG);
                        playerFrag.checkArray();
                        break;
                }
            }
        });



    }
}
