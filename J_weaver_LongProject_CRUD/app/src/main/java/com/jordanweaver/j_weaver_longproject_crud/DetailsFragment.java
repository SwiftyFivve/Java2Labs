package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/12/15.
 */
public class DetailsFragment extends Fragment {

    public static final String TAG = "DetailsFragment.TAG";

    private static final String ARG_NAME = "DetailsFragment.ARG_NAME";
    private static final String ARG_LAST = "DetailsFragment.ARG_LAST";
    private static final String ARG_PHONE = "DetailsFragment.ARG_PHONE";
    public static final String ARG_POSITION = "DetailsFragment.ARG_POSITION";

    public interface DetailsPageSelector {
        public void ChangeFrag(String _text, int mPosition);
    }

    private DetailsPageSelector mUpdater;



    public static DetailsFragment newInstance(String fName, String lName, String mPhone, int mPosition){
        DetailsFragment detailsFrag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, fName);
        args.putString(ARG_LAST, lName);
        args.putString(ARG_PHONE, mPhone);
        args.putInt(ARG_POSITION, mPosition);
        detailsFrag.setArguments(args);
        return detailsFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof DetailsPageSelector){
            mUpdater = (DetailsPageSelector)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the PageSelector interface");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button backButton = (Button)getActivity().findViewById(R.id.detailsBackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpdater != null){
                    mUpdater.ChangeFrag("DetailsBackButton", -1);
                }
            }
        });

        final Bundle args = getArguments();
        setOnText(args.getString(ARG_NAME), args.getString(ARG_LAST), args.getString(ARG_PHONE));


        Button deleteButton = (Button)getActivity().findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = new DataBaseHelper(getActivity());
                ArrayList<CustomObject> userArray = helper.checkArray();

                userArray.remove(args.getInt(ARG_POSITION));

                helper.onSave(userArray);

                if(mUpdater != null){
                    mUpdater.ChangeFrag("DetailsBackButton", -1);
                }


            }
        });

        Button editButton = (Button)getActivity().findViewById(R.id.editButton);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mUpdater != null){
                    mUpdater.ChangeFrag("EditButtonClicked", args.getInt(ARG_POSITION));
                }
            }
        });


    }

    public void setOnText(String fName, String lName, String mPhone){

        TextView firstText = (TextView)getActivity().findViewById(R.id.firstText);
        TextView lastText = (TextView)getActivity().findViewById(R.id.lastText);
        TextView phoneText = (TextView)getActivity().findViewById(R.id.phoneText);

        firstText.setText(fName);
        lastText.setText(lName);
        phoneText.setText(mPhone);

    }


}
