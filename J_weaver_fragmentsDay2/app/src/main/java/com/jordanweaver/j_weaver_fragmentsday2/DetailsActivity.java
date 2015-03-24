package com.jordanweaver.j_weaver_fragmentsday2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jordanweaver on 3/23/15.
 */
public class DetailsActivity extends Activity {

    public static final String ARGS_FRAGMENT = "DetailsList.ARGS_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.scrollView).setVisibility(View.GONE);

        if (savedInstanceState == null){

            DetailsFragment detsFrag = DetailsFragment.detailsInstance(true);
            getFragmentManager().beginTransaction().replace(R.id.detailsContainer,detsFrag,
                    DetailsFragment.TAG).commit();


//            }
        }
    }
}
