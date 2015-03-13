package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * Created by jordanweaver on 3/12/15.
 */

public class MainButtons extends Fragment {

    public static final String TAG = "MainButtons.TAG";

    public static MainButtons newInstance(){
        MainButtons mainButtons = new MainButtons();
        return mainButtons;
    }

    public interface ListPageSelector {
        public void ChangeFrag(String _text, int mPosition);
    }

    private ListPageSelector mUpdater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_buttons, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ListPageSelector){
            mUpdater = (ListPageSelector)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement ListPageSelector interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button addButton = (Button)getActivity().findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpdater!=null){
                    mUpdater.ChangeFrag("addButtonClicked", -1);
                }
            }
        });


    }
}
