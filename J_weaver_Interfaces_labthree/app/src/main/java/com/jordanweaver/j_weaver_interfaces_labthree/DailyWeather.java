package com.jordanweaver.j_weaver_interfaces_labthree;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by jordanweaver on 3/6/15.
 */
public class DailyWeather implements Serializable {

    String high;
    String low;
    String conditions;
    String weekday;
    String fullDate;
    String icon;

    public DailyWeather(String high, String low, String conditions, String weekday, String fullDate, String icon) {
        this.high = high;
        this.low = low;
        this.conditions = conditions;
        this.weekday = weekday;
        this.fullDate = fullDate;
        this.icon = icon;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}




