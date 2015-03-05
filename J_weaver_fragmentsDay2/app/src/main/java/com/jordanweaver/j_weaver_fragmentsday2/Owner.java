package com.jordanweaver.j_weaver_fragmentsday2;

import java.io.Serializable;

/**
 * Created by jordanweaver on 3/4/15.
 */
public class Owner extends FootballStaff implements Serializable{

    String yearsOwnership;

    public Owner(String teamName, String name, String division, String yearsOwnership) {
        super(teamName, name, division);
        this.yearsOwnership = yearsOwnership;
    }

    @Override
    public String toString() {
        return name + " - " + teamName + " - " + division+ " - " + yearsOwnership;
    }

    public String getYearsOwnership() {
        return yearsOwnership;
    }

    public void setYearsOwnership(String yearsOwnership) {
        this.yearsOwnership = yearsOwnership;
    }
}
