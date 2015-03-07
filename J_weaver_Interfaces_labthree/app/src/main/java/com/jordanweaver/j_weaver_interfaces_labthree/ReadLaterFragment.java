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
public class ReadLaterFragment extends Fragment{

    public static final String TAG = "ReadLaterFragment.TAG";

    public static ReadLaterFragment newInstance(){
        ReadLaterFragment readLaterFrag = new ReadLaterFragment();
        return readLaterFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.read_later_layout, container, false);

        return view;
    }


    public void showData(String weekDay, String highLow, String condition){
        TextView day = (TextView)getView().findViewById(R.id.weekdayText);
        TextView hLText = (TextView)getView().findViewById(R.id.highLowView);
        TextView conditionText = (TextView)getView().findViewById(R.id.conditionsText);

        day.setText(weekDay);
        hLText.setText(highLow);
        conditionText.setText(condition);

    }

}
