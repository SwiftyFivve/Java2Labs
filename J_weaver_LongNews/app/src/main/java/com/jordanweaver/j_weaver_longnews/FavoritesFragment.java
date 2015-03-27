package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class FavoritesFragment extends ListFragment {

    public static final String TAG = "FavoritesFragment.TAG";

    public static FavoritesFragment newInstance(){
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DataBaseHelper helper = new DataBaseHelper();
        ArrayList<String> titleNames = new ArrayList<>();

        ArrayList<NewsObject> loadedArray = helper.loadArray("Favorites.txt", getActivity());

        for(int i=0; i<loadedArray.size(); i++){

            if(titleNames.size() == 0){
                titleNames.add(0, loadedArray.get(i).title);
            } else {
                titleNames.add(titleNames.size(), loadedArray.get(i).title);
            }
        }

        ArrayAdapter<String> favoritesAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, titleNames);

        setListAdapter(favoritesAdapter);

        setEmptyText("No Favorites");



    }
}
