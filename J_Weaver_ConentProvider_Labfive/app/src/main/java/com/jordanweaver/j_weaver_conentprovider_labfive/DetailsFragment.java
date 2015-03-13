package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jordanweaver on 3/13/15.
 */
public class DetailsFragment extends Fragment {

    public static final String TAG = "DetailsFragment.TAG";

    private static final String ARG_NAME = "DetailsFragment.ARG_NAME";
    private static final String ARG_LAST = "DetailsFragment.ARG_LAST";
    private static final String ARG_SCHOOL = "DetailsFragment.ARG_SCHOOL";
    private static final String ARG_ID = "DetailsFragment.ARG_ID";

    public interface DetailsPageSelector {
        public void ChangeFrag(String _text);
    }

    private DetailsPageSelector mUpdater;

    public static DetailsFragment newInstance(String fName, String lName, String mSchool, int _id){
        DetailsFragment detailsFrag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, fName);
        args.putString(ARG_LAST, lName);
        args.putString(ARG_SCHOOL, mSchool);
        args.putInt(ARG_ID, _id);
        detailsFrag.setArguments(args);
        return detailsFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_layout, container, false);
        return view;
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button backButton = (Button)getActivity().findViewById(R.id.detailsBackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mUpdater != null){
                    mUpdater.ChangeFrag("DetailsBackButton");
                }

            }
        });


        final Bundle args = getArguments();
        setOnText(args.getString(ARG_NAME), args.getString(ARG_LAST), args.getString(ARG_SCHOOL));



        Button deleteButton = (Button)getActivity().findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getActivity().getContentResolver();

                //Uri accessUri = Uri.parse(DeVaunteDataContract.DeVaunte_URI_STRING);

                TextView firstName = (TextView)getActivity().findViewById(R.id.firstText);
                String fName = firstName.getText().toString();

                String whereClause = DeVaunteDataContract.ID+"=?";
                String[] whereArgs = new String[]{""+args.getInt(ARG_ID)+""};

                resolver.delete(DeVaunteDataContract.DeVaunte_CONTENT_URI, whereClause, whereArgs);

                if(mUpdater!=null){
                    mUpdater.ChangeFrag("DetailsBackButton");
                }


            }
        });

    }

    public void setOnText(String fName, String lName, String mSchool){

        TextView firstText = (TextView)getActivity().findViewById(R.id.firstText);
        TextView lastText = (TextView)getActivity().findViewById(R.id.lastText);
        TextView schoolText = (TextView)getActivity().findViewById(R.id.schoolText);

        firstText.setText(fName);
        lastText.setText(lName);
        schoolText.setText(mSchool);

    }
}
