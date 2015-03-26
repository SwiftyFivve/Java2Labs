package java2.devaunteledee.com.reviewassignment;

/**
 * Created by devaunteledee on 3/25/15.
 */
public class NYTimes {

    String articleName;
    String  authorName;
    String published_date;


    public NYTimes(String articleName, String authorName, String description) {
        this.articleName = articleName;
        this.authorName = authorName;
        this.published_date = description;
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

    public String getPublished_date() {
        return published_date;
    }

    @Override
    public String toString() {
        return articleName + '\n'  +
                authorName + '\n' +
               published_date + '\n'
               ;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }
}
