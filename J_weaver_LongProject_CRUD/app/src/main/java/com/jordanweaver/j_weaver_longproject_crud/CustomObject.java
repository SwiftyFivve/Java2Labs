package com.jordanweaver.j_weaver_longproject_crud;


// JORDAN WEAVER


import java.io.Serializable;

/**
 * Created by jordanweaver on 3/12/15.
 */
public class CustomObject implements Serializable{

    String fName;
    String lName;
    String mPhone;

    public CustomObject(String fName, String lName, String mPhone) {
        this.fName = fName;
        this.lName = lName;
        this.mPhone = mPhone;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }
}
