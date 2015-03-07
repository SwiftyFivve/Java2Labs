package com.jordanweaver.j_weaver_interfaces_labthree;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.PublicKey;


public class MainActivity extends Activity implements WeatherFragment.ContainerUpdate{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner userSpinner = (Spinner) findViewById(R.id.displaySpinner);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_stuff,
                android.R.layout.simple_dropdown_item_1line
        );

        userSpinner.setAdapter(spinnerAdapter);

        if (savedInstanceState == null) {
            WeatherFragment listFrag = WeatherFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.listContainer, listFrag, WeatherFragment.TAG).commit();
        }

        userSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        FavoritesFragment favoritesFrag = FavoritesFragment.newInstance();
                        getFragmentManager().beginTransaction().replace(R.id.detailsContainer, favoritesFrag, FavoritesFragment.TAG).commit();
                        break;
                    case 1:
                        ReadLaterFragment readLaterFrag = ReadLaterFragment.newInstance();
                        getFragmentManager().beginTransaction().replace(R.id.detailsContainer, readLaterFrag, ReadLaterFragment.TAG).commit();
                        break;
                    case 2:
                        SaveFragment saveFrag = SaveFragment.newInstance();
                        getFragmentManager().beginTransaction().replace(R.id.detailsContainer, saveFrag, SaveFragment.TAG).commit();
                        break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void getPosition(int _position){

    }


    @Override
    public void updateContainer(DailyWeather _weather) {
        FavoritesFragment favoritesFrag = (FavoritesFragment)getFragmentManager()
                .findFragmentByTag(FavoritesFragment.TAG);

        ReadLaterFragment readLaterFrag = (ReadLaterFragment)getFragmentManager()
                .findFragmentByTag(ReadLaterFragment.TAG);

        SaveFragment saveFrag = (SaveFragment)getFragmentManager()
                .findFragmentByTag(SaveFragment.TAG);

        if(favoritesFrag != null){
            favoritesFrag.showData(_weather.fullDate, _weather.icon, _weather.getHigh()+"/"+_weather.getLow());
        }

        if(readLaterFrag !=null){
            readLaterFrag.showData(_weather.weekday, _weather.high+"/"+_weather.low, _weather.conditions);
        }

        if(saveFrag != null){
            saveFrag.showData(_weather.icon, _weather.conditions, _weather.high+"/"+_weather.low);
        }




    }
}
