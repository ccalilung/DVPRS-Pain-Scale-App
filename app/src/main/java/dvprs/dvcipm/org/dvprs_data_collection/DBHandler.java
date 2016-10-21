package dvprs.dvcipm.org.dvprs_data_collection;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "scores.db";
    public static final String TABLE_SCORES = "scores";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DVPRS = "dvprs";
    public static final String COLUMN_ACTIVITY = "activity";
    public static final String COLUMN_SLEEP = "sleep";
    public static final String COLUMN_MOOD = "mood";
    public static final String COLUMN_STRESS = "stress";
    public static final String COLUMN_QA1 = "qa_one";
    public static final String COLUMN_QA2 = "qa_two";
    public static final String COLUMN_QA3 = "qa_three";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_SCORES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_DVPRS + " INTEGER, " +
                COLUMN_ACTIVITY + " INTEGER, " +
                COLUMN_SLEEP + " INTEGER, " +
                COLUMN_MOOD + " INTEGER, " +
                COLUMN_STRESS + " INTEGER, " +
                COLUMN_QA1 + " INTEGER, " +
                COLUMN_QA2 + " INTEGER, " +
                COLUMN_QA3 + " INTEGER" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database
    public void addScore(Scores scores) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DVPRS, scores.get_dvprs());
        values.put(COLUMN_ACTIVITY, scores.get_activity());
        values.put(COLUMN_SLEEP, scores.get_sleep());
        values.put(COLUMN_MOOD, scores.get_mood());
        values.put(COLUMN_STRESS, scores.get_stress());
        values.put(COLUMN_QA1, scores.get_qa1());
        values.put(COLUMN_QA2, scores.get_qa2());
        values.put(COLUMN_QA3, scores.get_qa3());
        Log.d("String Message: ", "Values = " + values);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_SCORES, null, values);
        sqLiteDatabase.close();
    }

    //Print out values as a string
    public String dbToString() {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SCORES + " LIMIT 10 ORDER BY " + COLUMN_ID + " DESC";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("id")) != null){
                dbString += "ID: ";
                dbString += c.getString(c.getColumnIndex("id"));
                dbString += "DVPRS: ";
                dbString += c.getString(c.getColumnIndex("dvprs"));
                dbString += "Activity: ";
                dbString += c.getString(c.getColumnIndex("activity"));
                dbString += "Sleep: ";
                dbString += c.getString(c.getColumnIndex("sleep"));
                dbString += "Mood: ";
                dbString += c.getString(c.getColumnIndex("mood"));
                dbString += "Stress: ";
                dbString += c.getString(c.getColumnIndex("stress"));
                dbString += "QA1: ";
                dbString += c.getString(c.getColumnIndex("qa1"));
                dbString += "QA2: ";
                dbString += c.getString(c.getColumnIndex("qa2"));
                dbString += "QA3: ";
                dbString += c.getString(c.getColumnIndex("qa3"));
                dbString += "\n";
            }
        }
        sqLiteDatabase.close();
        return dbString;
    }

}
