package com.jordanweaver.j_weaver_interfaces_labthree;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

/**
 * Created by jordanweaver on 3/6/15.
 */
public class FavoritesFragment extends Fragment {

    public static final String TAG = "FavoritesFragment.TAG";


    public static FavoritesFragment newInstance(){
        FavoritesFragment favoritesFrag = new FavoritesFragment();
        return favoritesFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_layout, container, false);

        return view;
    }

    public void showData(String month, String icon, String temps){
        TextView months = (TextView)getView().findViewById(R.id.monthText);
        SmartImageView icons = (SmartImageView)getView().findViewById(R.id.my_image);
        TextView tempsText = (TextView)getView().findViewById(R.id.highLowText);

        months.setText(month);
        icons.setImageUrl(icon);
        tempsText.setText(temps);

    }

}
