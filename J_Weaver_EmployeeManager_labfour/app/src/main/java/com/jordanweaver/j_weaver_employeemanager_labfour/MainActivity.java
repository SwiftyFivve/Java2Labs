package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity implements MainButtonClass.ListPageSelector,
        FormFragment.FormPageSelector, MainEmployeeList.UpdateDetails,
        DetailsFragment.DetailsPageSelector {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.mainContainer,
                    MainButtonClass.newInstance(), MainButtonClass.TAG).commit();

            getFragmentManager().beginTransaction().replace(R.id.listFragContainer,
                    MainEmployeeList.newInstance(), MainEmployeeList.TAG).commit();
        }

    }

    @Override
    public void ChangeFrag(String _text) {
        Log.e("Instance", _text+"");
        switch (_text){
            case "addFrag":
                getFragmentManager().beginTransaction().replace(R.id.listFragContainer,
                        FormFragment.newInstance(), FormFragment.TAG).commit();
                findViewById(R.id.mainContainer).setVisibility(View.GONE);
                break;

            case "FormFrag":
                getFragmentManager().beginTransaction().replace(R.id.listFragContainer,
                        MainEmployeeList.newInstance(), MainEmployeeList.TAG).commit();
                findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
                break;
            case "DetailsFrag":
                getFragmentManager().beginTransaction().replace(R.id.listFragContainer,
                        MainEmployeeList.newInstance(), MainEmployeeList.TAG).commit();
                findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
                break;
            case "settingsFrag":
                getFragmentManager().beginTransaction().replace(R.id.listFragContainer,
                        SettingsFragment.newInstance(), SettingsFragment.TAG).commit();
                findViewById(R.id.mainContainer).setVisibility(View.GONE);
        }
    }

    @Override
    public void getPosition(int _position) {

        DetailsFragment detailsFrag = (DetailsFragment)getFragmentManager()
                .findFragmentByTag(DetailsFragment.TAG);

        DataBaseHelper helper = DataBaseHelper.getInstance(this);

        Cursor cursor = helper.getEmployees();

        cursor.moveToPosition(_position);


        Log.e("The position test", cursor.getString(1)+"");

        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String employeeNum = cursor.getString(3);
        String hireText = cursor.getString(4);
        String statusText = cursor.getString(5);

        if (detailsFrag == null) {

            getFragmentManager().beginTransaction().replace(R.id.listFragContainer,
                    DetailsFragment.newInstance(firstName, lastName, employeeNum, hireText,
                            statusText), DetailsFragment.TAG).commit();
            findViewById(R.id.mainContainer).setVisibility(View.GONE);
        } else {
            detailsFrag.setText(firstName, lastName, employeeNum, hireText, statusText);
        }
    }

}
