package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity implements ContentListFragment.UpdateDetails,
        SchoolAddForm.SchoolSelector,
        ListButtonFragment.ListPageSelector {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.buttonContainer,
                    ListButtonFragment.newInstance(), ListButtonFragment.TAG).commit();
            getFragmentManager().beginTransaction().replace(R.id.listContainer,
                    ContentListFragment.newInstance(), ContentListFragment.TAG).commit();
        }

    }

    @Override
    public void getPosition(int _position) {

    }

    @Override
    public void ChangeFrag(String _text) {
        switch (_text){
            case "AddButtonClicked":
                findViewById(R.id.buttonContainer).setVisibility(View.GONE);
                findViewById(R.id.listContainer).setVisibility(View.GONE);
                findViewById(R.id.formContainer).setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.formContainer,
                        SchoolAddForm.newInstance(), SchoolAddForm.TAG).commit();
                break;
            case "SchoolBackButton":
                findViewById(R.id.formContainer).setVisibility(View.GONE);
                findViewById(R.id.listContainer).setVisibility(View.VISIBLE);
                findViewById(R.id.buttonContainer).setVisibility(View.VISIBLE);
        }

    }
}
