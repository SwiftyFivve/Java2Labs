package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity implements NewsFeedFrag.UpdateView{

    Menu actionItems;

    //public static final String ACTION_DETAILS = "com.fullsail.android.ACTION_DETAILS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.feedContainer,
                    NewsFeedFrag.newInstance(), NewsFeedFrag.TAG).commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        actionItems = menu;

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_favorites) {

            getFragmentManager().beginTransaction().replace(R.id.feedContainer,
                    FavoritesFragment.newInstance(), FavoritesFragment.TAG).commit();

            actionItems.findItem(R.id.action_favorites).setVisible(false);
            actionItems.findItem(R.id.action_feed).setVisible(true);

        }

        if (id == R.id.action_feed){

            getFragmentManager().beginTransaction().replace(R.id.feedContainer,
                    NewsFeedFrag.newInstance(), NewsFeedFrag.TAG).commit();


            actionItems.findItem(R.id.action_feed).setVisible(false);
            actionItems.findItem(R.id.action_favorites).setVisible(true);

        }

        if(id == R.id.action_about){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("About");
            alert.setMessage("ReviewAssignment \nJordan Weaver \n3/26/2015");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });

            alert.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeFrag(String _view, int _position) {

        if(_view.equals("DetailsView")){

            NewsObject selectedNews = null;
            ArrayList<NewsObject> selectedArray = new ArrayList<>();
            DataBaseHelper helper = new DataBaseHelper();

            ArrayList<NewsObject> loadedNews = helper.loadArray("SavedFeed.txt", this);
            for(int i=0; i<loadedNews.size(); i++){
                if(i == _position){
                    selectedNews = loadedNews.get(i);
                }
            }

            if(selectedNews != null) {
                //Log.e("selected News Title", selectedNews.title);

                Intent intent = new Intent(this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.Text_Extra_Source, selectedNews.source);
                intent.putExtra(DetailsActivity.Text_Extra_Title, selectedNews.title);
                intent.putExtra(DetailsActivity.Text_Extra_Summary, selectedNews.summary);
                intent.putExtra(DetailsActivity.Text_Extra_SourceUrl, selectedNews.sourceUrl);
                intent.putExtra(DetailsActivity.Object_Extra_NewsObject, selectedNews);
                startActivity(intent);
            }

        }


    }
}
