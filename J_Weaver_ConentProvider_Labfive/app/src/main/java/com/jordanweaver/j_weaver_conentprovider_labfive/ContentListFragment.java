package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;

/**
 * Created by jordanweaver on 3/11/15.
 */
public class ContentListFragment extends ListFragment {
    
    public static final String TAG = "ContentListFragment.TAG";

    public interface UpdateDetails {
        public void getPosition(int _position);
    }

    private UpdateDetails mPosition;
    
    public static ContentListFragment newInstance(){
        ContentListFragment contentListFrag = new ContentListFragment();
        return contentListFrag;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


    }


    private class ContentAdapter extends ResourceCursorAdapter{

        public ContentAdapter(Context context, Cursor c) {
            super(context, android.R.layout.simple_list_item_1, c, 0);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }
    }
}
