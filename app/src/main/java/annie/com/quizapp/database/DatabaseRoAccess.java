package annie.com.quizapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import annie.com.quizapp.model.Question;

/**
 * Created by Annie on 28/04/2017.
 */

public class DatabaseRoAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseRoAccess instance;
    private static String TABLE = "Questions";

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */

    private DatabaseRoAccess(Context context) {
        this.openHelper = new DatabaseRoOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseUKAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */


    public static DatabaseRoAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseRoAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all questions from the database.
     *
     * @return a List of questions
     */


    public List<Question> getRomanianQuestions() {

        List<Question> questionsList = new ArrayList<Question>();
        Cursor cursor = database.rawQuery("SELECT * from Questions", null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setId(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptionA(cursor.getString(3));
                quest.setOptionB(cursor.getString(4));
                quest.setOptionC(cursor.getString(5));
                quest.setOptionD(cursor.getString(6));
                quest.setDifficulty(cursor.getString(7));

                questionsList.add(quest);

            } while (cursor.moveToNext());
        }

        return questionsList;
    }

    public List<Question> getDifficultyRomanianQuestions(String difficulty) {

        List<Question> questionsList = new ArrayList<Question>();
        //Cursor cursor = database.rawQuery("SELECT * from ? where difficulty = ?", new String[] {TABLE, difficulty});
        //rawQuery("SELECT * FROM ? where Category = ?", new String[] {tableName, categoryex});

       /* String sqlQuery = "select * from Appointment where start_date='"
                + startDate + "' order by start_date,time(start_date) ASC";

        cursor = getReadableDatabase().rawQuery(sqlQuery, null);*/

        String query="select * from "+TABLE+" where difficulty='"+ difficulty +"' ";
        Cursor cursor=database.rawQuery(query,null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setId(cursor.getInt(0));
                quest.setQuestion(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptionA(cursor.getString(3));
                quest.setOptionB(cursor.getString(4));
                quest.setOptionC(cursor.getString(5));
                quest.setOptionD(cursor.getString(6));
                quest.setDifficulty(cursor.getString(7));

                questionsList.add(quest);

            } while (cursor.moveToNext());
        }

        return questionsList;
    }

    public int rowCount() {
        int row = 0;
        String selectQuery = "SELECT * FROM Questions";
        Cursor cursor = database.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
    }
}
