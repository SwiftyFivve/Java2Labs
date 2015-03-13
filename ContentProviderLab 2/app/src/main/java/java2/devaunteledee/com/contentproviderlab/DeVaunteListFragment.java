package java2.devaunteledee.com.contentproviderlab;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;

public class DeVaunteListFragment extends ListFragment {
     public static final String TAG = "DeVaunteListFragment.TAG";
    public static  DeVaunteListFragment newInstance(){
        DeVaunteListFragment fragment = new DeVaunteListFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }


    private class DeVaunteDatabaseAdapter extends ResourceCursorAdapter {

        public DeVaunteDatabaseAdapter(View view,Context context, Cursor c) {
            super(context, android.R.layout.simple_list_item_1, c);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

//            String item = cursor.getString()
//            String section = cursor.getString()
//            String item_Quantity = cursor.getString()
        }
    }
}
