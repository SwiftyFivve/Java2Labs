package com.jordanweaver.j_weaver_fragmentsday2;

/**
 * Created by jordanweaver on 3/4/15.
 */
public class Coach extends FootballStaff {

    String yearsExperience;

    public Coach(String teamName, String name, String division, String yearsExperience) {
        super(teamName, name, division);
        this.yearsExperience = yearsExperience;
    }

    @Override
    public String toString() {
        return name + " - " + teamName + " - " + division+ " - " + yearsExperience;
    }

    public String getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(String yearsExperience) {
        this.yearsExperience = yearsExperience;
    }
}
