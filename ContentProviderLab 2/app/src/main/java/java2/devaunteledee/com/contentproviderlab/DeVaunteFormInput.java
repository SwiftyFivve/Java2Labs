package java2.devaunteledee.com.contentproviderlab;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class DeVaunteFormInput extends Fragment implements View.OnClickListener {
    EditText nameOfItem;
    EditText section;
    EditText quantity;

    public static final  String Tag = "DeVaunteFormInput.Tag";

    public static DeVaunteFormInput newInstance(){

        DeVaunteFormInput deVaunteFormInput = new DeVaunteFormInput();

        Log.i("a","a......testing deVaunteFormInput");

        return deVaunteFormInput;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listformlayout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.Save).setOnClickListener(this);
        nameOfItem = (EditText) getView().findViewById(R.id.nameOfItem);
        section = (EditText) getView().findViewById(R.id.section);
        quantity = (EditText) getView().findViewById(R.id.quantity);
    }

   @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Save ){
            ContentResolver resolver = getActivity().getContentResolver();
            ContentValues values = new ContentValues();
            values.put(DataContract.ITEM_NAME,nameOfItem.getText().toString());
            values.put(DataContract.SECTION,section.getText().toString());
            values.put(DataContract.ITEM_QUANTITY,quantity.getText().toString());



            resolver.insert(DataContract.CONTENT_URI,values);


              Toast.makeText(getActivity(), "WORKING", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().replace(R.id.container, DeVaunteListFragment.newInstance(),DeVaunteListFragment.TAG).commit();



        }

    }
}
