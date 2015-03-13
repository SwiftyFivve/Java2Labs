package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by jordanweaver on 3/11/15.
 */
public class ListButtonFragment extends Fragment {

    public static final String TAG = "MainButtonFragment.TAG";

    public interface ListPageSelector {
        public void ChangeFrag(String _text);
    }

    private ListPageSelector mUpdater;

    public static ListButtonFragment newInstance(){
        ListButtonFragment listFrag = new ListButtonFragment();
        return listFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_button_frag, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof ListPageSelector){
            mUpdater = (ListPageSelector)activity;
        } else {
            throw new IllegalArgumentException("Main Activity needs to implement ListPageSelector");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button addButton = (Button)getActivity().findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUpdater!=null) {
                    mUpdater.ChangeFrag("AddButtonClicked");
                }
            }
        });
    }
}
