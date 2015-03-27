package com.jordanweaver.j_weaver_longnews;


//
//
//
//Jordan Weaver
//
//
//


import java.io.Serializable;

/**
 * Created by jordanweaver on 3/24/15.
 */
public class NewsObject implements Serializable {

    String source;
    String title;
    String summary;
    String sourceUrl;

    public NewsObject(String source, String title, String summary, String sourceUrl) {
        this.source = source;
        this.title = title;
        this.summary = summary;
        this.sourceUrl = sourceUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
