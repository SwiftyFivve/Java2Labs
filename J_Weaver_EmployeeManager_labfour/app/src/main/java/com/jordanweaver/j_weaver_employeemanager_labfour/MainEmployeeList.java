package com.jordanweaver.j_weaver_employeemanager_labfour;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by jordanweaver on 3/9/15.
 */
public class MainEmployeeList extends ListFragment {

    public static final String TAG = "MainEmployeeList.TAG";

    public interface UpdateDetails {
        public void getPosition(int _position);
    }

    private UpdateDetails mPosition;

    public static MainEmployeeList newInstance() {
        MainEmployeeList mainEmployeeFrag = new MainEmployeeList();
        return mainEmployeeFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof UpdateDetails){
            mPosition = (UpdateDetails)activity;
            Log.e("onAttach", "Instance working");
        } else {
            throw new IllegalArgumentException("Activity must implement UpdateDetails interface");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DataBaseHelper helper = DataBaseHelper.getInstance(getActivity());
        Cursor cursor = helper.getEmployees();
        setEmptyText("No Data");

        setListAdapter(new EmployeeDatabaseAdapter(getActivity(), cursor));
    }

    @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if(mPosition != null){
            mPosition.getPosition(position);
        }

    }


    private class EmployeeDatabaseAdapter extends ResourceCursorAdapter {


        public EmployeeDatabaseAdapter(Context context, Cursor c) {
            super(context, android.R.layout.simple_expandable_list_item_1, c, 0);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            String first = cursor.getString(1);
            String last = cursor.getString(2);
            int employeeNum = cursor.getInt(3);

            ((TextView)view).setText(first + " " + last + " -#"+employeeNum);
        }
    }


}
