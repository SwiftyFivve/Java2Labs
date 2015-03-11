package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PublicKey;

/**
 * Created by jordanweaver on 3/9/15.
 */
public class FormFragment extends Fragment {

    public static final String TAG = "FormFragment.TAG";

    public interface FormPageSelector {
        public void ChangeFrag(String _text);
    }

    private FormPageSelector mUpdater;

    public static FormFragment newInstance(){
        FormFragment formFrag = new FormFragment();
        return formFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_fragment, container, false);

        return view;
    }

    @Override
     public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof FormPageSelector){
            mUpdater = (FormPageSelector)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the PageSelector interface");
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button backButton = (Button) getActivity().findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpdater != null) {
                    mUpdater.ChangeFrag("FormFrag");
                }
            }
        });

        Button saveButton = (Button) getActivity().findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText first = (EditText) getActivity().findViewById(R.id.firstInput);
                EditText last = (EditText) getActivity().findViewById(R.id.lastInput);
                EditText num = (EditText) getActivity().findViewById(R.id.employeeNumInput);
                EditText date = (EditText) getActivity().findViewById(R.id.dateInput);
                EditText status =(EditText) getActivity().findViewById(R.id.statusInput);


                String firstN = first.getText().toString();
                String lastN = last.getText().toString();
                String employeeNum = num.getText().toString();
                String hireDate = date.getText().toString();
                String employStatus = status.getText().toString();

                if(!firstN.trim().equals("") && !lastN.trim().equals("") && !employeeNum.trim().equals("") &&
                        !hireDate.trim().equals("") && !employStatus.trim().equals("")){
                    //if(Integer.parseInt(employeeNum) == true) {

                        int employeeNumber = Integer.parseInt(employeeNum);

                        DataBaseHelper helper = DataBaseHelper.getInstance(getActivity());

                        helper.addEmployee(firstN, lastN, employeeNumber, hireDate, employStatus);

                    if(mUpdater!=null){
                        mUpdater.ChangeFrag("FormFrag");
                    }

                    //}
                } else {
                    Toast.makeText(getActivity(), "Please complete all required areas", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
