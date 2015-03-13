package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.app.Activity;
import android.app.ListFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by jordanweaver on 3/11/15.
 */
public class ContentListFragment extends ListFragment {
    
    public static final String TAG = "ContentListFragment.TAG";

    public interface UpdateDetails {
        public void getPosition(int _position);
    }

    private UpdateDetails mPosition;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ContentResolver resolver = getActivity().getContentResolver();

        String[] columns = new String[]{
                DeVaunteDataContract.ID,
                DeVaunteDataContract.FIRST_NAME,
                DeVaunteDataContract.LAST_NAME,
                DeVaunteDataContract.NAMEOFSCHOOL
        };

        Cursor nameCursor = resolver.query(
                DeVaunteDataContract.DeVaunte_CONTENT_URI, columns, null, null, null
        );

        Log.e("check",DeVaunteDataContract.DeVaunte_CONTENT_URI+"");

        setListAdapter(new ContentAdapter(getActivity(), nameCursor));
    }

    public static ContentListFragment newInstance(){
        ContentListFragment contentListFrag = new ContentListFragment();
        return contentListFrag;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof UpdateDetails){
            mPosition = (UpdateDetails)activity;
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        Log.e("Position", position+"");

        if(mPosition != null){
            mPosition.getPosition(position);
        }

    }


    private class ContentAdapter extends ResourceCursorAdapter{

        public ContentAdapter(Context context, Cursor c) {
            super(context, android.R.layout.simple_list_item_1, c, 0);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            String first = cursor.getString(1);
            String last = cursor.getString(2);

            ((TextView)view).setText(first + " " + last);
        }
    }
}
