package com.jordanweaver.j_weaver_longnews;

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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.news_feed_layout, container, false);
//        return view;
//    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NetworkUtils connectNetwork = new NetworkUtils(getActivity(), this);
        connectNetwork.execute("http://api.feedzilla.com/v1/categories/3/subcategories/65/articles.json");


    }

    @Override
    public void updateArray(ArrayList<NewsObject> _news) {

        ArrayList<String> newsTitle = new ArrayList<>();

        for(int i=0; i<_news.size(); i++){
            //Log.e("Work!!", _news.get(i).title);
            if(newsTitle.size() == 0){
                newsTitle.add(0, _news.get(i).title);
            } else {
                newsTitle.add(newsTitle.size(), _news.get(i).title);
            }

            ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, newsTitle);

            setListAdapter(newAdapter);

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

//        NetworkUtils disconnectNetwork = new NetworkUtils(getActivity(), this);
//        disconnectNetwork.cancel(true);
//
//        Log.e("Check cancel", disconnectNetwork.isCancelled()+"");

    }
}
