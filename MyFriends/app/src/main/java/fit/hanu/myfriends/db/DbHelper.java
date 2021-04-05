package fit.hanu.myfriends.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "friends.db";
    public static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //run for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables
        db.execSQL("CREATE TABLE " + DbSchema.FriendTable.NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSchema.FriendTable.cols.NAME + " TEXT, " +
                DbSchema.FriendTable.cols.EMAIL + " TEXT, " +
                DbSchema.FriendTable.cols.PHONE_NO + " TEXT " +
                ")" );

        //other tables here
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w("My friends", "My Friends: updating Db; dropping/recreating tables.");

        //drop existing tables
        db.execSQL("DROP TABLE IF EXISTS " + DbSchema.FriendTable.NAME);

        //other table here

        //create table again
        onCreate(db);
    }
}
