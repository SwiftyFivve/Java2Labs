package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by jordanweaver on 3/9/15.
 */
public class MainButtonClass extends Fragment {

    public static final String TAG = "MainButtonClass.TAG";

    public interface ListPageSelector {
        public void ChangeFrag(String _text);
    }

    private ListPageSelector mUpdater;

    public static MainButtonClass newInstance(){
        MainButtonClass mainButtonFrag = new MainButtonClass();
        return mainButtonFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_list_fragment, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ListPageSelector){
            mUpdater = (ListPageSelector)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement PageSelector interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button addButton = (Button) getActivity().findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mUpdater != null) {
                    mUpdater.ChangeFrag("addFrag");
                }

            }
        });

        Button settingsButton = (Button)getActivity().findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mUpdater != null){
                    mUpdater.ChangeFrag("settingsFrag");
                }

            }
        });

    }
}
