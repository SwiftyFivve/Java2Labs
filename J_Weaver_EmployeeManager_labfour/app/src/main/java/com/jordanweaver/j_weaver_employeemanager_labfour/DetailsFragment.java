package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by jordanweaver on 3/9/15.
 */
public class DetailsFragment extends Fragment{

    public static final String TAG = "DetailsFragment.TAG";

    public interface DetailsPageSelector {
        public void ChangeFrag(String _text);
    }

    private DetailsPageSelector mUpdater;
    private static final String ARG_NAME = "BottomFragment.ARG_NAME";
    private static final String ARG_LAST = "BottomFragment.ARG_LAST";
    private static final String ARG_NUM = "BottomFragment.ARG_NUM";
    private static final String ARG_HIRE = "BottomFragment.ARG_HIRE";
    private static final String ARG_STATUS = "BottomFragment.ARG_STATUS";

    public static DetailsFragment newInstance(String _name, String _last, String _employeeNum, String _hire, String _status){
        DetailsFragment detailsFrag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, _name);
        args.putString(ARG_LAST, _last);
        args.putString(ARG_NUM, _employeeNum);
        args.putString(ARG_HIRE, _hire);
        args.putString(ARG_STATUS, _status);
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

        Button backButton = (Button) getActivity().findViewById(R.id.detailsBackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpdater != null) {
                    mUpdater.ChangeFrag("DetailsFrag");
                }
            }
        });

        Bundle args = getArguments();
        setText(args.getString(ARG_NAME), args.getString(ARG_LAST), args.getString(ARG_NUM),
                args.getString(ARG_HIRE), args.getString(ARG_STATUS));

        Button deleteButton = (Button)getActivity().findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper helper = DataBaseHelper.getInstance(getActivity());

                TextView deleteName = (TextView)getActivity().findViewById(R.id.firstText);
                String delete = deleteName.getText().toString();

                helper.deleteEmployee(delete);

                if(mUpdater != null) {
                    mUpdater.ChangeFrag("DetailsFrag");
                }

            }
        });

    }

    public void setText(String name, String last, String employeeNum, String hire, String status){
        TextView nameView = (TextView)getActivity().findViewById(R.id.firstText);
        TextView lastView = (TextView)getActivity().findViewById(R.id.lastText);
        TextView employNum = (TextView)getActivity().findViewById(R.id.employeeNumText);
        TextView hireText = (TextView)getActivity().findViewById(R.id.hireDateText);
        TextView statusText = (TextView)getActivity().findViewById(R.id.statusText);

        nameView.setText(name);
        lastView.setText(last);
        employNum.setText("Employee Number: #"+employeeNum);
        hireText.setText("Date Hired: "+hire);
        statusText.setText("Employees Status: "+status);

    }


}
