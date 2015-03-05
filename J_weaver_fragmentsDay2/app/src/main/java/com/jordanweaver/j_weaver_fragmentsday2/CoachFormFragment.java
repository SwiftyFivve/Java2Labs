package com.jordanweaver.j_weaver_fragmentsday2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
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
public class CoachFormFragment extends Fragment {

    public static final String TAG = "CoachFormFragment.TAG";


    public static CoachFormFragment formInstance(String classRequirement){
        CoachFormFragment coachFrag = new CoachFormFragment();

        return coachFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View formView = inflater.inflate(R.layout.coach_form_layout, container, false);

        return formView;


    }

    public void runCoach(ArrayList<FootballStaff> collectiveStaff){

            EditText coachTeamName = (EditText) getView().findViewById(R.id.teamNameInput);
            EditText coachName = (EditText) getView().findViewById(R.id.staffNameInput);
            EditText coachDivision = (EditText) getView().findViewById(R.id.divisionInput);
            EditText coachExperience = (EditText) getView().findViewById(R.id.experienceInput);


        if(!coachTeamName.getText().toString().equals("") &&
                !coachName.getText().toString().equals("") &&
                !coachDivision.getText().toString().equals("") &&
                !coachExperience.getText().toString().equals(""))
        {


            Coach coaches = new Coach(coachTeamName.getText().toString(),
                    coachName.getText().toString(),
                    coachDivision.getText().toString(),
                    coachExperience.getText().toString());

            if (collectiveStaff == null){
                collectiveStaff.add(0, coaches);
            }
            else {
                collectiveStaff.add(collectiveStaff.size(), coaches);
            }


            Log.e("Coach Class", coaches + "");


            try {
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
            //collectiveArray.add(collectiveArray.size(), coaches);
            Log.e("Hope this loaded", collectiveArray+"");
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (collectiveArray == null){
            collectiveArray = new ArrayList<>();
            //collectiveArray.add(0, coaches);
        }

        runCoach(collectiveArray);

    }

}
