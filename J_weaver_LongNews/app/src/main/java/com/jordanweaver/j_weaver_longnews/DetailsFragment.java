package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jordanweaver on 3/26/15.
 */
public class DetailsFragment extends Fragment {

    public static final String TAG = "DetailsFragment.TAG";

    public static DetailsFragment newInstance(){
        DetailsFragment detailsFragment = new DetailsFragment();
        return detailsFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);


        Intent startingIntent = getActivity().getIntent();

        TextView sourceText = (TextView) view.findViewById(R.id.sourceText);
        TextView titleText = (TextView) view.findViewById(R.id.titleText);
        TextView summaryText = (TextView) view.findViewById(R.id.summaryText);
        TextView urlText = (TextView) view.findViewById(R.id.urlText);

        if(startingIntent.hasExtra(DetailsActivity.Text_Extra_Source) &&
                startingIntent.hasExtra(DetailsActivity.Text_Extra_Title) &&
                startingIntent.hasExtra(DetailsActivity.Text_Extra_Summary) &&
                startingIntent.hasExtra(DetailsActivity.Text_Extra_SourceUrl)){
            sourceText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_Source));
            titleText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_Title));
            summaryText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_Summary));
            urlText.setText(startingIntent.getStringExtra(DetailsActivity.Text_Extra_SourceUrl));

        }

        return view;
    }


}
