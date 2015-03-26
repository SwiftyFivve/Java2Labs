package com.jordanweaver.reviewassignment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by jordanweaver on 3/25/15.
 */
public class FeedFragment extends Fragment {

    public static final String TAG = "FeedFragment.TAG";

    public static FeedFragment newInstance(){
        FeedFragment frag = new FeedFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Spinner feedSpinner = (Spinner) getActivity().findViewById(R.id.feedChanger);

        ListView feedList = (ListView) getActivity().findViewById(R.id.listFeed);

    }
}
