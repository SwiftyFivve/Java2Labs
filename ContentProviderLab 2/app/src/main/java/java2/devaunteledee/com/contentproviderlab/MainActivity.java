package java2.devaunteledee.com.contentproviderlab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("LOG IS WORKING ", "LOG IS WORKING");

        DeVaunteFormInput deVaunteFormInput = DeVaunteFormInput.newInstance();

//            getFragmentManager().beginTransaction().replace(R.id.container, DeVaunteListFragment.newInstance(),DeVaunteListFragment.TAG).commit();
        getFragmentManager().beginTransaction().replace(R.id.container, deVaunteFormInput,DeVaunteFormInput.Tag).commit();

        if (savedInstanceState == null){


            /*DeVaunteFormInput deVaunteFormInput = DeVaunteFormInput.newInstance();

//            getFragmentManager().beginTransaction().replace(R.id.container, DeVaunteListFragment.newInstance(),DeVaunteListFragment.TAG).commit();
            getFragmentManager().beginTransaction().replace(R.id.container, deVaunteFormInput,DeVaunteFormInput.Tag).commit();*/

        }
    }


}
