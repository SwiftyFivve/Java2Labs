package com.jordanweaver.j_weaver_multipleactivites_labsix;

import android.content.Context;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jordanweaver on 3/17/15.
 */
public class DataHelperClass {
Profile profile;
    Context mContext;

    public DataHelperClass(Context context){
        this.mContext = context;
    }

    public void onSave(JSONArray _profile){

        try {
            FileOutputStream fos = mContext.openFileOutput("Json.txt", Context.MODE_PRIVATE);

            JSONArray saveArray = _profile;
            //saveArray.put(0, _profile);
            fos.write(saveArray.toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public JSONArray onLoad(){
        String data = null;
        JSONArray jsonProfile = null;

        try {

            FileInputStream fis = mContext.openFileInput("Json.txt");
            data = IOUtils.toString(fis);
            jsonProfile = new JSONArray(data);
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(jsonProfile == null){
            return jsonProfile = new JSONArray();
        }
        return jsonProfile;
    }

}
