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
public class PlayerFormFragment extends Fragment{

    public static final String TAG = "PlayerFormFragment.TAG";


    public static PlayerFormFragment formInstance(String classRequirement){
        PlayerFormFragment playerFrag = new PlayerFormFragment();

        return playerFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View formView = inflater.inflate(R.layout.player_form_layout, container, false);

        return formView;


    }

    public void runPlayer(ArrayList<FootballStaff> collectiveStaff){
        EditText playerTeamName = (EditText) getView().findViewById(R.id.teamNameInput);
        EditText playerName = (EditText) getView().findViewById(R.id.staffNameInput);
        EditText playerDivision = (EditText) getView().findViewById(R.id.divisionInput);
        EditText playerPosition = (EditText) getView().findViewById(R.id.positionInput);

        if(!playerTeamName.getText().toString().equals("") &&
                !playerName.getText().toString().equals("") &&
                !playerDivision.getText().toString().equals("") &&
                !playerPosition.getText().toString().equals("")){


            Player players = new Player(playerTeamName.getText().toString(),
                    playerName.getText().toString(),
                    playerDivision.getText().toString(),
                    playerPosition.getText().toString());


            if (collectiveStaff == null){
                collectiveStaff.add(0, players);
            }
            else {
                collectiveStaff.add(collectiveStaff.size(), players);
            }

            Log.e("Players Class", players.toString() + "");

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
            //collectiveArray.add(collectiveArray.size(), players);
            Log.e("Hope this loaded", collectiveArray+"");
            ois.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        if (collectiveArray == null){
            collectiveArray = new ArrayList<>();
            //collectiveArray.add(0, players);
        }

        runPlayer(collectiveArray);

    }

}
