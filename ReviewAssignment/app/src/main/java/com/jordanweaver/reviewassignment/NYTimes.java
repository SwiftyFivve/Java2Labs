package com.jordanweaver.reviewassignment;

/**
 * Created by devaunteledee on 3/25/15.
 */
public class NYTimes {

    String articleName;
    String  authorName;
    String description;


    public NYTimes(String articleName, String authorName, String description) {
        this.articleName = articleName;
        this.authorName = authorName;
        this.description = description;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
