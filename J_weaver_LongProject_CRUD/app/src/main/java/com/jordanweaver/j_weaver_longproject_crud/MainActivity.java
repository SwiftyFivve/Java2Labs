package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/12/15.
 */

public class MainActivity extends Activity implements MainListFrag.UpdateDetails,
        MainButtons.ListPageSelector, AddForm.FormPageSelector, DetailsFragment.DetailsPageSelector,
        AddForm.usingPosition {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.buttonContainer,
                    MainButtons.newInstance(), MainButtons.TAG).commit();

            getFragmentManager().beginTransaction().replace(R.id.listContainer,
                    MainListFrag.newInstance(), MainListFrag.TAG).commit();
        }
    }

    @Override
    public void ChangeFrag(String _text, int mPosition) {
        switch (_text){
            case "addButtonClicked":
                findViewById(R.id.buttonContainer).setVisibility(View.GONE);
                findViewById(R.id.listContainer).setVisibility(View.GONE);
                findViewById(R.id.formContainer).setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                        AddForm.newInstance("FromListFrag", -1), AddForm.TAG).commit();
                break;
            case "FormBackButton":
                findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.listContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.formContainer).setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.buttonContainer,
                        MainButtons.newInstance(), MainButtons.TAG).commit();

                getFragmentManager().beginTransaction().replace(R.id.listContainer,
                        MainListFrag.newInstance(), MainListFrag.TAG).commit();
                break;
            case "savedNewProfile":
                findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.listContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.formContainer).setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.buttonContainer,
                        MainButtons.newInstance(), MainButtons.TAG).commit();

                getFragmentManager().beginTransaction().replace(R.id.listContainer,
                        MainListFrag.newInstance(), MainListFrag.TAG).commit();
                break;
            case "DetailsBackButton":
                findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.listContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.formContainer).setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                        MainButtons.newInstance(), MainButtons.TAG).commit();
                getFragmentManager().beginTransaction().replace(R.id.buttonContainer,
                        MainButtons.newInstance(), MainButtons.TAG).commit();

                getFragmentManager().beginTransaction().replace(R.id.listContainer,
                        MainListFrag.newInstance(), MainListFrag.TAG).commit();
                break;
            case "EditButtonClicked":
                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                        AddForm.newInstance("FromDetailsFrag", mPosition), AddForm.TAG).commit();
                break;
            case "EditCancelBackToDetails":
//                EditText fName = (EditText) findViewById(R.id.firstText);
//                EditText lName = (EditText) findViewById(R.id.lastText);
//                EditText mPhone = (EditText) findViewById(R.id.phoneText);
//                String firstName = fName.getText().toString();
//                String lastName = lName.getText().toString();
//                String _phone = mPhone.getText().toString();

                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                        DetailsFragment.newInstance("STILL", "TESTING", "DATA", 0),
                        DetailsFragment.TAG).commit();

                break;
        }
    }

    @Override
    public void getPosition(int _position) {
        DetailsFragment detailsFragment = (DetailsFragment) getFragmentManager().
                findFragmentByTag(DetailsFragment.TAG);


        Log.e("Click Position", _position+"");

        DataBaseHelper helper = new DataBaseHelper(this);
        ArrayList<CustomObject> usersArray = helper.checkArray();
        CustomObject getPerson = usersArray.get(_position);


        if (detailsFragment == null) {

            findViewById(R.id.buttonContainer).setVisibility(View.GONE);
            findViewById(R.id.listContainer).setVisibility(View.GONE);
            findViewById(R.id.formContainer).setVisibility(View.VISIBLE);
            getFragmentManager().beginTransaction().replace(R.id.formContainer,
                    DetailsFragment.newInstance(getPerson.fName, getPerson.lName, getPerson.mPhone, _position),
                    DetailsFragment.TAG).commit();

        } else {
            detailsFragment.setOnText(getPerson.fName, getPerson.lName, getPerson.mPhone);
        }


    }

}
