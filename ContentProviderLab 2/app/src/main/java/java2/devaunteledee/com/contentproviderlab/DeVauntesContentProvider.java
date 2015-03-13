package java2.devaunteledee.com.contentproviderlab;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class DeVauntesContentProvider extends ContentProvider {
    DeVauntesSQLDatabase deVauntesSQLDatabase;
    UriMatcher deVauntesUriMatcher;


    @Override
    public boolean onCreate() {
        deVauntesSQLDatabase = new DeVauntesSQLDatabase(getContext());
        deVauntesUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        deVauntesUriMatcher.addURI(DeVaunteDataContract.DeVaunte_URI_AUTHORITY,DeVaunteDataContract.DeVaunte_DATA_TABLE, 10);
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int deVaunteUriType = deVauntesUriMatcher.match(uri);
        if (deVaunteUriType == 10) {
            return deVauntesSQLDatabase.getWritableDatabase().query(DeVaunteDataContract.DeVaunte_DATA_TABLE, projection, selection, selectionArgs, null, null, sortOrder);


        }
        throw new IllegalArgumentException("YOU MUST TARGERT DATACONTRACT.DATA_TABLE" +uri);
    }

    @Override
    public String getType(Uri uri) {
        int deVaunteUriType = deVauntesUriMatcher.match(uri);
        if (deVaunteUriType == 10){
            return "vnd.android.cursor.dir/vnd" + DeVaunteDataContract.DeVaunte_URI_AUTHORITY + "," + DeVaunteDataContract.DeVaunte_DATA_TABLE;
        }
        throw new  IllegalArgumentException("This provider does not support single row");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int deVaunteUriType = deVauntesUriMatcher.match(uri);
        if (deVaunteUriType == 10) {
            long id = deVauntesSQLDatabase.getWritableDatabase().insert(DeVaunteDataContract.DeVaunte_DATA_TABLE, null, values);

            return  Uri.parse(DeVaunteDataContract.DeVaunte_URI_STRING + "/" + id);
        }
        throw new IllegalArgumentException("YOU MUST TARGERT DATACONTRACT.DATA_TABLE");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deVaunteUriType = deVauntesUriMatcher.match(uri);
        if (deVaunteUriType == 10) {
            return deVauntesSQLDatabase.getWritableDatabase().delete(DeVaunteDataContract.DeVaunte_DATA_TABLE, selection, selectionArgs);

        }
        throw new IllegalArgumentException("YOU MUST TARGERT DATACONTRACT.DATA_TABLE");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int deVaunteUriType = deVauntesUriMatcher.match(uri);
        if (deVaunteUriType == 10) {
            deVauntesSQLDatabase.getWritableDatabase().update(DeVaunteDataContract.DeVaunte_DATA_TABLE, values, selection,selectionArgs);


        }
        throw new IllegalArgumentException("YOU MUST TARGERT DATACONTRACT.DATA_TABLE");
    }
}
