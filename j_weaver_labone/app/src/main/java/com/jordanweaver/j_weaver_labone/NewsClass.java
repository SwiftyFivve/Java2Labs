package com.jordanweaver.j_weaver_labone;

import java.io.Serializable;

/**
 * Created by jordanweaver on 3/2/15.
 */
public class NewsClass implements Serializable {

    String title;

    @Override
    public String toString(){
        return title;
    }

    public NewsClass(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
