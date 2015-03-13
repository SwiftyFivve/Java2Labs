package com.jordanweaver.j_weaver_conentprovider_labfive;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class GroceryDataProvider extends ContentProvider {
    public GroceryDataProvider() {
    }

    DataBaseHelper mDataBase;
    UriMatcher mMatcher;


    @Override
    public boolean onCreate() {
        mDataBase = new DataBaseHelper(getContext());
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(DataContract.URI_AUTHORITY, DataContract.DATA_TABLE, 1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int uriType = mMatcher.match(uri);
        if(uriType == 1){
            return mDataBase.getWritableDatabase().query(DataContract.DATA_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        }

        throw new IllegalArgumentException("You must target the DataContract.DATA_TABLE");
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = mMatcher.match(uri);
        if(uriType == 1){
            mDataBase.getWritableDatabase().delete(DataContract.DATA_TABLE, selection, selectionArgs);
        }
        throw new IllegalArgumentException("Must target DATACONTRACT.DATA_TABLE");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        int uriType = mMatcher.match(uri);
        if(uriType==1){
            return "vnd.android.cursor.dir/vnd."+
                    DataContract.URI_AUTHORITY + "." + DataContract.DATA_TABLE;
        }
        throw new IllegalArgumentException("This provider does not support single row");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = mMatcher.match(uri);
        if(uriType == 1){
            if(values.containsKey(DataContract.ITEM_NAME) &&
                    values.containsKey(DataContract.SECTION) &&
                    values.containsKey(DataContract.ITEM_QUANTITY) &&
                    values.size() == 3){
                //VALIDATE EACH FIELD AND DOES IT HAVE ANY EXTRA KEYS!
                long id = mDataBase.getWritableDatabase().insert(DataContract.DATA_TABLE, null, values);

                return Uri.parse(DataContract.CONTENT_URI_STRING+"/"+id);
            }

        }

        throw new IllegalArgumentException("You must target the DataContract.DATA_TABLE");
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
