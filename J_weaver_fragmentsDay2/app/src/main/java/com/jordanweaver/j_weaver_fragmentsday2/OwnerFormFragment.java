package com.jordanweaver.j_weaver_fragmentsday2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/4/15.
 */
public class OwnerFormFragment extends Fragment {

    public static final String TAG = "OwnerFormFragment.TAG";


    public static OwnerFormFragment formInstance(String classRequirement){
        OwnerFormFragment formFrag = new OwnerFormFragment();

        return formFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View formView = inflater.inflate(R.layout.owner_form_layout, container, false);

        return formView;


    }

    public void runOwner(ArrayList<FootballStaff> collectiveStaff) {

            EditText ownersTeamName = (EditText) getView().findViewById(R.id.teamNameInput);
            EditText ownersName = (EditText) getView().findViewById(R.id.staffNameInput);
            EditText ownersDivision = (EditText) getView().findViewById(R.id.divisionInput);
            EditText ownersYearsOwn = (EditText) getView().findViewById(R.id.ownershipInput);

            if (!ownersTeamName.getText().toString().equals("") &&
                    !ownersName.getText().toString().equals("") &&
                    !ownersDivision.getText().toString().equals("") &&
                    !ownersYearsOwn.getText().toString().equals("")) {


                Owner owners = new Owner(ownersTeamName.getText().toString(),
                        ownersName.getText().toString(),
                        ownersDivision.getText().toString(),
                        ownersYearsOwn.getText().toString());

                if (collectiveStaff == null){
                    collectiveStaff.add(0, owners);
                }
                else {
                    collectiveStaff.add(collectiveStaff.size(), owners);
                }

                Log.e("Owners Class", collectiveStaff + "");

                try {
                    //FileOutputStream fos = new FileOutputStream(output);
                    FileOutputStream fos = getActivity().openFileOutput("array.txt", Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(collectiveStaff);
                    oos.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }



    }


    public void checkArray(){

        File input = new File("array.txt");

        ArrayList<FootballStaff> collectiveArray = null;

        try{

            FileInputStream fis = getActivity().openFileInput("array.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            collectiveArray = (ArrayList<FootballStaff>) ois.readObject();
            //collectiveArray.add(collectiveArray.size(), owners);
            Log.e("Hope this loaded", collectiveArray+"");
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (collectiveArray == null){
            collectiveArray = new ArrayList<>();
            //collectiveArray.add(0, owners);
        }

        //return collectiveArray;

        runOwner(collectiveArray);

    }
}
