package com.jordanweaver.j_weaver_fragmentsday2;

import java.io.Serializable;

/**
 * Created by jordanweaver on 3/4/15.
 */
public class Player extends FootballStaff implements Serializable {

    String position;


    public Player(String teamName, String name, String division, String position) {
        super(teamName, name, division);
        this.position = position;
    }

    @Override
    public String toString() {
        return name + " - " + teamName + " - " + division+ " - " + position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
