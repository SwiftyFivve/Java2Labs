package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by jordanweaver on 3/11/15.
 */
public class SchoolAddForm extends Fragment {

    public static final String TAG = "SchoolAddForm.TAG";

    public interface SchoolSelector {
        public void ChangeFrag(String _text);
    }

    private SchoolSelector mUpdater;

    public static SchoolAddForm newInstance(){
        SchoolAddForm schoolAddForm = new SchoolAddForm();
        return schoolAddForm;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.school_add_form, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof SchoolSelector){
            mUpdater = (SchoolSelector)activity;
        } else {
            throw new IllegalArgumentException("Main Activity needs to implement ListPageSelector");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button backButton = (Button)getActivity().findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpdater!=null) {
                    mUpdater.ChangeFrag("SchoolBackButton");
                }
            }
        });

        Button saveButton = (Button)getActivity().findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver resolver = getActivity().getContentResolver();

                ContentValues values = new ContentValues();
                EditText firstName = (EditText)getActivity().findViewById(R.id.firstInput);
                EditText lastName = (EditText)getActivity().findViewById(R.id.lastInput);
                EditText school = (EditText)getActivity().findViewById(R.id.schoolInput);

                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String mSchool = school.getText().toString();

                if(!fName.trim().equals("") && !lName.trim().equals("") && !mSchool.trim().equals("")) {


                    values.put(DeVaunteDataContract.FIRST_NAME, fName);
                    values.put(DeVaunteDataContract.LAST_NAME, lName);
                    values.put(DeVaunteDataContract.NAMEOFSCHOOL, mSchool);


                    resolver.insert(DeVaunteDataContract.DeVaunte_CONTENT_URI, values);

                    if (mUpdater != null) {
                        mUpdater.ChangeFrag("SchoolBackButton");
                    }
                }

            }
        });
    }
}
