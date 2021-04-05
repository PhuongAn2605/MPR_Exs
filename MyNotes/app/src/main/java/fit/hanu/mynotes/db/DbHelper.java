package fit.hanu.mynotes.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//create database
//SQLiteDatabase
public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "test-notes7.db";
    private static final int DATABASE_VERSION = 3;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DbSchema.NoteTable.NAME + "(" + DbSchema.NoteTable.Cols.ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.NoteTable.Cols.TEXT + " TEXT" +
                ")");

//        db.execSQL("INSERT INTO " + DbSchema.NoteTable.NAME + "(text) VALUES('Example note1')");
//        db.execSQL("INSERT INTO " + DbSchema.NoteTable.NAME + "(text) VALUES('Example note2')");
//        db.execSQL("INSERT INTO " + DbSchema.NoteTable.NAME + "(text) VALUES('Example note3')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.NoteTable.NAME);

        onCreate(db);
    }
}
