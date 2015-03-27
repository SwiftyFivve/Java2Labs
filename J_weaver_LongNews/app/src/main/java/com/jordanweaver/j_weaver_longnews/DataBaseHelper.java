package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/26/15.
 */
public class DataBaseHelper {




    public static ArrayList<NewsObject> loadArray(String _nameFile, Context mContext){

        ArrayList<NewsObject> savedNews = null;


        try {
            FileInputStream fis = mContext.openFileInput(_nameFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedNews = (ArrayList<NewsObject>)ois.readObject();
            fis.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(savedNews == null){
            return savedNews = new ArrayList<>();
        }

        return savedNews;
    }



    public static void saveArray(ArrayList<NewsObject> _news, String _nameFile,  Context mContext){

        try {
            ArrayList<NewsObject> saveArray = _news;
            FileOutputStream fos = mContext.openFileOutput(_nameFile, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(saveArray);
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
