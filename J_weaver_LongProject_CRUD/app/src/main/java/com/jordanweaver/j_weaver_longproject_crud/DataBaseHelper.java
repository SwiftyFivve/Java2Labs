package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import android.content.Context;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/12/15.
 */
public class DataBaseHelper {

    Context mContext;
    DisplayData mData;
    //, DisplayData mData

    public DataBaseHelper(Context context){
        this.mContext = context;
        //this.mData = mData;
    }

    public interface DisplayData {
        public void updateData(ArrayList<CustomObject> _usersArray);
    }

    public ArrayList<CustomObject> checkArray(){

        ArrayList<CustomObject> usersArray = null;

        try {
            FileInputStream fis = mContext.openFileInput("array.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            usersArray = (ArrayList<CustomObject>)ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(usersArray == null){
            usersArray = new ArrayList<>();
        }

        //Run add method here
        //saveProfile(usersArray, fName, lName, mPhone);

        return usersArray;
    }

    public void saveProfile(ArrayList<CustomObject> profileArray, String fName, String lName, String mPhone){

        CustomObject profile = new CustomObject(fName, lName, mPhone);

        if(profileArray == null){
            profileArray.add(0, profile);
        } else {
            profileArray.add(profileArray.size(), profile);
        }

        try {
            FileOutputStream fos = mContext.openFileOutput("array.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(profileArray);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onSave(ArrayList<CustomObject> profileArray){

        try {
            FileOutputStream fos = mContext.openFileOutput("array.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(profileArray);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
