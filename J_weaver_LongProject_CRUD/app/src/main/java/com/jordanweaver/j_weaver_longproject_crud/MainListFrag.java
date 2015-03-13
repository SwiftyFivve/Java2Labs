package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordanweaver on 3/12/15.
 */
public class MainListFrag extends ListFragment {

    public static final String TAG = "MainListFrag.TAG";

    public interface UpdateDetails{
        public void getPosition(int _position);
    }

    private UpdateDetails mPosition;

    public static MainListFrag newInstance(){
        MainListFrag mainListFrag = new MainListFrag();
        return mainListFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof UpdateDetails){
            mPosition = (UpdateDetails)activity;
            onLoad();
        } else {
            throw new IllegalArgumentException("Activity must implement UpdateDetails interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        String[] testAdapter = new String[]{};
//        ArrayAdapter<String> profileAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testAdapter);
//
//        setEmptyText("No Data");
//
//        setListAdapter(profileAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(mPosition != null){
            mPosition.getPosition(position);
        }
    }

    public void onLoad (){


            DataBaseHelper helper = new DataBaseHelper(getActivity());
            ArrayList<CustomObject> personsArray = helper.checkArray();

            ArrayList<String> arrayName = new ArrayList<>();
            for (int i = 0; i < personsArray.size(); i++) {
                arrayName.add(personsArray.get(i).fName);
            }

            ArrayAdapter<String> profileAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, arrayName);

            setListAdapter(profileAdapter);

        }
}
