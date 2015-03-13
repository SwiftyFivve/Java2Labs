package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/12/15.
 */
public class AddForm extends Fragment implements DataBaseHelper.DisplayData{

    public static final String TAG = "AddForm.TAG";
    private static String ARG_PAGE = "AddForm.ARG_PAGE";
    private static String ARG_POSITION = "AddForm.ARG_POSITION";

    @Override
    public void updateData(ArrayList<CustomObject> _usersArray) {

    }

    public interface FormPageSelector {
        public void ChangeFrag(String _text, int mPosition);
    }

    private FormPageSelector mUpdater;

    public interface usingPosition {
        public void getPosition(int _position);
    }

    private usingPosition mPosition;

    public static AddForm newInstance(String pageFrom, int _position){
        AddForm addForm = new AddForm();
        Bundle args = new Bundle();
        args.putString(ARG_PAGE, pageFrom);
        args.putInt(ARG_POSITION, _position);
        addForm.setArguments(args);
        return addForm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_form, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof FormPageSelector){
            mUpdater = (FormPageSelector)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the FormPageSelector");
        }

        if(activity instanceof usingPosition){
            mPosition = (usingPosition)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the usingPosition interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Bundle args = getArguments();

        //Populate form here
        if(args.getInt(ARG_POSITION)>-1){
            DataBaseHelper helper = new DataBaseHelper(getActivity());
            ArrayList<CustomObject> personsArray = helper.checkArray();

            CustomObject person = personsArray.get(args.getInt(ARG_POSITION));

            populateForm(person.fName, person.lName, person.mPhone);

        }



        Button backButton =(Button)getActivity().findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Argument Change", args.getString(ARG_PAGE));

                if(args.getString(ARG_PAGE).equals("FromListFrag")) {
                    if(mUpdater!=null) {
                        mUpdater.ChangeFrag("FormBackButton", -1);
                     }
                } else if (args.getString(ARG_PAGE).equals("FromDetailsFrag")){
                    if(mPosition!=null) {
                        mPosition.getPosition(args.getInt(ARG_POSITION));
                    }
                }

            }
        });

        Button saveButton = (Button)getActivity().findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    EditText first = (EditText)getActivity().findViewById(R.id.firstInput);
                    EditText last = (EditText)getActivity().findViewById(R.id.lastInput);
                    EditText phone = (EditText)getActivity().findViewById(R.id.phoneInput);

                    String firstName = first.getText().toString();
                    String lastName = last.getText().toString();
                    String phoneModel = phone.getText().toString();

                    if(!firstName.trim().equals("") && !lastName.trim().equals("") &&
                            !phoneModel.trim().equals("")) {


                        DataBaseHelper helper = new DataBaseHelper(getActivity());
                        ArrayList<CustomObject> passArray = helper.checkArray();

                        if(args.getString(ARG_PAGE).equals("FromListFrag")) {

                            if (passArray != null) {

                                helper.saveProfile(passArray, firstName, lastName, phoneModel);
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                            }

                            if (mUpdater != null) {
                                mUpdater.ChangeFrag("savedNewProfile", -1);
                            }




                        } else if (args.getString(ARG_PAGE).equals("FromDetailsFrag")) {

                            CustomObject person = new CustomObject(firstName, lastName, phoneModel);

                            for (int i = 0; i < passArray.size(); i++) {
                                if (i == args.getInt(ARG_POSITION)) {
                                    passArray.remove(i);
                                    passArray.add(i, person);
                                }

                            }

                            helper.onSave(passArray);

                            if(mPosition!=null) {
                                mPosition.getPosition(args.getInt(ARG_POSITION));
                            }

                        }

                    }

            }
        });
    }

    public void populateForm(String fName, String lName, String mPhone){
        EditText firstField = (EditText)getActivity().findViewById(R.id.firstInput);
        EditText lastField = (EditText)getActivity().findViewById(R.id.lastInput);
        EditText phoneField = (EditText)getActivity().findViewById(R.id.phoneInput);

        firstField.setText(fName);
        lastField.setText(lName);
        phoneField.setText(mPhone);

    }
}
