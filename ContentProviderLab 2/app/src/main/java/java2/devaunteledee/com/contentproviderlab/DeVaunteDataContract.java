package java2.devaunteledee.com.contentproviderlab;

import android.net.Uri;

/**
 * Created by devaunteledee on 3/11/15.
 */
public class DeVaunteDataContract {
    public static final String DeVaunte_URI_AUTHORITY = "com.fullsail.DeVaunteLedee.android.provider";
    public static final String DeVaunte_DATA_TABLE = "provider_data";
    public static final String DeVaunte_URI_STRING = "content://" + DeVaunte_URI_AUTHORITY + "/" + DeVaunte_DATA_TABLE;

    public static final Uri DeVaunte_CONTENT_URI = Uri.parse(DeVaunte_URI_STRING);






    public static final String ID = "_id";
    public static final String  FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    public static final String NAMEOFSCHOOL = "schoolname";

}
