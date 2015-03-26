package com.jordanweaver.j_weaver_longnews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity {

    Menu actionItems;

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

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        

    }
}
