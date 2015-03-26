package java2.devaunteledee.com.reviewassignment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class FeedFragment extends Fragment implements NewsAsyncTask.Adapterhelp{
    String url;
    Spinner feedSpinner;
    String[] newsSections;
    NewsAsyncTask newsAsyncTask;
    newsAdapter NewsAdapter;
    ListView feedList;
    public static final String TAG = "FeedFragment.TAG";

    public static FeedFragment newInstance(){
        FeedFragment frag = new FeedFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
//        View view = inflater.inflate(R.layout.feed_fragment, container, false);
//        return view;
//return  inflater.inflate(R.layout.feed_fragment, container, false);
        return  inflater.inflate(R.layout.feed_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

         feedSpinner = (Spinner) getActivity().findViewById(R.id.feedChanger);

         feedList = (ListView) getActivity().findViewById(R.id.listFeed);

        newsSections = new String[]{
                "Arts",
                "Sports",
                "Technology"
        };

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,newsSections);
feedSpinner.setAdapter(spinnerAdapter);

feedSpinner.setOnItemSelectedListener(itemClick);




        try{
            url = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/" + URLEncoder.encode(feedSpinner.getSelectedItem().toString(), "UTF-8") + "/7.json?api-key=275111f94eeda6a75b1b3194b0538572:11:71453443";


        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        if (isConnected()){

            NewsAsyncTask newsAsyncTask = new NewsAsyncTask(getActivity(),this );

            newsAsyncTask.execute(url);


            NewsAdapter = new newsAdapter(getActivity(),newsAsyncTask.newsArray);
            Log.i("NEWS ADAPTER", "NEWS " + NewsAdapter);

            feedList.setAdapter(NewsAdapter);


        }
        else{
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("ATTENTION!");
            builder.setMessage("Problem with network connection.");
            builder.setCancelable(true);
            builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (isConnected()) {
                        try {

                            newsAsyncTask.execute("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/" + URLEncoder.encode(feedSpinner.getSelectedItem().toString(), "UTF-8") + "/7.json?api-key=275111f94eeda6a75b1b3194b0538572:11:71453443");

                        }catch (UnsupportedEncodingException e){
                            e.printStackTrace();
                        }
                    }
                    else{
                        builder.show();
                    }
                }
            });

            builder.show();
        }


    }
    AdapterView.OnItemSelectedListener itemClick = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            try{
                url = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/" + URLEncoder.encode(feedSpinner.getSelectedItem().toString(), "UTF-8") + "/7.json?api-key=275111f94eeda6a75b1b3194b0538572:11:71453443";


            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            if (isConnected()){

//                NewsAsyncTask newsAsyncTask = new NewsAsyncTask(getActivity(),);
                newsAsyncTask.execute(url);


                NewsAdapter = new newsAdapter(getActivity(),newsAsyncTask.newsArray);
                Log.i("NEWS ADAPTER", "NEWS " + NewsAdapter);

                feedList.setAdapter(NewsAdapter);


            }
            else{
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("ATTENTION!");
                builder.setMessage("Problem with network connection.");
                builder.setCancelable(true);
                builder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (isConnected()) {
                            try {

                                newsAsyncTask.execute("http://api.nytimes.com/svc/mostpopular/v2/mostviewed/" + URLEncoder.encode(feedSpinner.getSelectedItem().toString(), "UTF-8") + "/7.json?api-key=275111f94eeda6a75b1b3194b0538572:11:71453443");

                            }catch (UnsupportedEncodingException e){
                                e.printStackTrace();
                            }
                        }
                        else{
                            builder.show();
                        }
                    }
                });

                builder.show();
            }
        };

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
    private boolean isConnected(){
        ConnectivityManager mgr = (ConnectivityManager)getActivity().getSystemService((Context.CONNECTIVITY_SERVICE));

        if (mgr != null){
            NetworkInfo info = mgr.getActiveNetworkInfo();

            if (info != null && info.isConnected()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateAdapter(ArrayList _ARRAYLIST) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,_ARRAYLIST);
        feedList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
