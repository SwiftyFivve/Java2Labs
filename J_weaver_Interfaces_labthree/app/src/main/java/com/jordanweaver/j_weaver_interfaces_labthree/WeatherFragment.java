package com.jordanweaver.j_weaver_interfaces_labthree;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/6/15.
 */

public class WeatherFragment extends android.app.ListFragment implements NetworkUtils.DisplayData{



    public static final String TAG = "ListFragment.TAG";

    public static WeatherFragment newInstance(){
        WeatherFragment listFrag = new WeatherFragment();
        return listFrag;
    }

    public interface ContainerUpdate{
        public void updateContainer(DailyWeather _weather);
    }

    private ContainerUpdate mContainUpdate;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ContainerUpdate){
            mContainUpdate = (ContainerUpdate)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the TextUpdater Interface");
        }
    }

    @Override
    public void updateArray(ArrayList<DailyWeather> _weather) {

        ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getString(_weather));

        setListAdapter(newAdapter);


        if(_weather != null) {
            try {
                FileOutputStream fos = getActivity().openFileOutput("array.txt", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(_weather);
                oos.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.e("The Array", _weather+"");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         NetworkUtils connectNetwork = new NetworkUtils(getActivity(), this);
         connectNetwork.execute("http://api.wunderground.com/api/ec864c4dc772cc90/forecast10day/q/FL/Orlando.json");

    }


    public ArrayList<String> getString(ArrayList<DailyWeather> _weather){
        ArrayList<String> finalString = new ArrayList<>();



            for (int i = 0; i < _weather.size(); i++) {
                finalString.add(_weather.get(i).fullDate);
            }

        return finalString;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        ArrayList<DailyWeather> _allWeather = null;

        try {
            FileInputStream fis = getActivity().openFileInput("array.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            _allWeather = (ArrayList<DailyWeather>)ois.readObject();

            if(mContainUpdate != null){
                mContainUpdate.updateContainer(_allWeather.get(position));
            }

            Log.e("Opened File", _allWeather.get(2).conditions+"");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }




}
