package belov.daniil.petproject.learningnow;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LearningNow";
    public static final String TABLE_DICTIONARY = "Dictionary";

    public static final String KEY_ID = "_id";
    public static final String KEY_FOREIGN_WORD = "foreign_word";
    public static final String KEY_TRANSLATE_WORD = "translate_word";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery =
                "CREATE TABLE " + TABLE_DICTIONARY + " " +
                "(" +
                        KEY_ID + " INTEGER PRIMARY KEY," +
                        KEY_FOREIGN_WORD + " TEXT," +
                        KEY_TRANSLATE_WORD + " TEXT" +
                 ")";
        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropQuery = "DROP TABLE IF EXISTS " + TABLE_DICTIONARY;

        db.execSQL(dropQuery);
        onCreate(db);

    }
}
