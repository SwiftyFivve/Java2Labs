package java2.devaunteledee.com.contentproviderlab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by devaunteledee on 3/11/15.
 */
public class DeVauntesSQLDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_FILE = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
            DeVaunteDataContract.DeVaunte_DATA_TABLE + " (" +
            DeVaunteDataContract.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DeVaunteDataContract.FIRST_NAME +" TEXT, " +
            DeVaunteDataContract.LAST_NAME + " TEXT, " +
            DeVaunteDataContract.NAMEOFSCHOOL + " TEXT)";



    public DeVauntesSQLDatabase(Context context) {
        super(context,DATABASE_FILE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
