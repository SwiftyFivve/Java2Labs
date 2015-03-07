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
public class SaveFragment extends Fragment {

    public static final String TAG = "SaveFragment.TAG";

    public static SaveFragment newInstance(){
        SaveFragment saveFrag = new SaveFragment();

        return saveFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.save_layout, container, false);

        return view;
    }

    public void showData(String icon, String highLow, String condition){
        SmartImageView icons = (SmartImageView)getView().findViewById(R.id.weatherIcon);
        TextView conditions = (TextView)getView().findViewById(R.id.saveConditionText);
        TextView highsnlows = (TextView)getView().findViewById(R.id.highLowSave);

        icons.setImageUrl(icon);
        conditions.setText(condition);
        highsnlows.setText(highLow);

    }
}
