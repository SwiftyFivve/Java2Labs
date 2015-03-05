package com.jordanweaver.j_weaver_fragmentsday2;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/4/15.
 */
public class DetailsFragment extends ListFragment {
    public static final String TAG = "DetailsFragment.TAG";

    public static DetailsFragment detailsInstance(){
        DetailsFragment detsFrag = new DetailsFragment();
        return detsFrag;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] testAdapter = new String[]{};
        ArrayAdapter<String> detailsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testAdapter);

        setEmptyText("No Data");

        setListAdapter(detailsAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    public void loadData(){
        File input = new File("array.txt");

        ArrayList<FootballStaff> collectiveArray = null;


        try {
            FileInputStream fis = getActivity().openFileInput("array.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            collectiveArray = (ArrayList<FootballStaff>)ois.readObject();
            Log.e("Hope this loaded", collectiveArray+"");
            ArrayAdapter<FootballStaff> detailsAdapter = new ArrayAdapter<FootballStaff>(getActivity(), android.R.layout.simple_list_item_1, collectiveArray);
            setListAdapter(detailsAdapter);
            ois.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
