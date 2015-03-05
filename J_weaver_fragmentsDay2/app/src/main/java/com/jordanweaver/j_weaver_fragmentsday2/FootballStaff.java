package com.jordanweaver.j_weaver_fragmentsday2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jordanweaver on 3/4/15.
 */
public class FootballStaff implements Serializable{

    String teamName;
    String name;
    String division;


    public FootballStaff(String teamName, String name, String division) {
        this.teamName = teamName;
        this.name = name;
        this.division = division;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}
