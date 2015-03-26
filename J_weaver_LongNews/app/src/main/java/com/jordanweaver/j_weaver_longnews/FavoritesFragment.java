package com.jordanweaver.j_weaver_longnews;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class FavoritesFragment extends ListFragment {

    public static final String TAG = "FavoritesFragment.TAG";

    public static FavoritesFragment newInstance(){
        FavoritesFragment favoritesFragment = new FavoritesFragment();
        return favoritesFragment;
    }




}
