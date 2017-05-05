package annie.com.quizapp.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Annie on 28/04/2017.
 */

public class DatabaseEnOpenHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "enQuestions.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseEnOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }
}
