package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class NewsFeedFrag extends ListFragment implements NetworkUtils.DisplayData{

    public static final String TAG = "NewsFeedFrag.TAG";

    public static NewsFeedFrag newInstance(){
        NewsFeedFrag newsFeedFrag = new NewsFeedFrag();
        return newsFeedFrag;
    }

    public interface UpdateView{
        public void changeFrag(String _view, int _position);
    }

    public UpdateView mUpdate;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof UpdateView){
            mUpdate = (UpdateView)activity;
        } else {
            throw new IllegalArgumentException("Activity must implement the TextUpdater Interface");
        }


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mUpdate.changeFrag("DetailsView", position);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NetworkUtils connectNetwork = new NetworkUtils(getActivity(), this);
        connectNetwork.execute("http://api.feedzilla.com/v1/categories/3/subcategories/65/articles.json");


    }

    @Override
    public void updateArray(ArrayList<NewsObject> _news) {

        ArrayList<String> newsTitle = new ArrayList<>();


        DataBaseHelper helper = new DataBaseHelper();
        ArrayList<NewsObject> feedArray = helper.loadArray("SavedFeed.txt", getActivity());

        for(int i=0; i<_news.size(); i++){

            if(feedArray.size() == 0){
                feedArray.add(0, _news.get(i));
            } else {
                feedArray.add(feedArray.size(), _news.get(i));
                Log.e("Saved Array", feedArray+"");
            }




            if(newsTitle.size() == 0){
                newsTitle.add(0, _news.get(i).title);
            } else {
                newsTitle.add(newsTitle.size(), _news.get(i).title);
            }



        }

        helper.saveArray(feedArray, "SavedFeed.txt", getActivity());

        ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, newsTitle);

        setListAdapter(newAdapter);

    }


}
