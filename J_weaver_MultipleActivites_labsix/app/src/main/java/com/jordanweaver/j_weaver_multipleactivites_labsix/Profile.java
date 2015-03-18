package com.jordanweaver.j_weaver_multipleactivites_labsix;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jordanweaver on 3/16/15.
 */
public class Profile {

    public String firstName;
    public String lastName;
    public int age;

//    public Profile profile (JSONObject json){
//
//        try {
//            firstName = json.getString("first");
//            lastName = json.getString("last");
//            age = json.getInt("age");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return
//    }

    public JSONObject convertToJson(String firstName, String lastName, int age){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("first", firstName);
            jsonObject.put("last", lastName);
            jsonObject.put("age", age);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

}
