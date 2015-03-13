package java2.devaunteledee.com.contentproviderlab;

import android.net.Uri;

/**
 * Created by jordanweaver on 3/11/15.
 */
public final class DataContract {


    public static final String URI_AUTHORITY = "com.fullsail.jweaver.android.provider";
    public static final String DATA_TABLE = "grocery_data";
    public static final String CONTENT_URI_STRING = "content://"+URI_AUTHORITY+"/"+DATA_TABLE;

    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

    public static final String _ID = "_id";

    public static final String ITEM_NAME = "item";
    public static final String SECTION = "section";
    public static final String ITEM_QUANTITY = "quantity";


}
