package fit.hanu.myfriends.db;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

import fit.hanu.myfriends.models.Friend;


public class FriendManager {
    private static FriendManager instance;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private static final String INSERT_STMT =
            "INSERT INTO " + DbSchema.FriendTable.NAME + "(name, phoneNo, email) VALUES (?,?,?)";

    public static FriendManager getInstance(Context context){
        if(instance == null){
            //init
            instance = new FriendManager(context);
        }
        return instance;
    }

    //DbHelper need pass context
    private FriendManager(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase(); //trigger onCreate | onUpgrade


    }

    public List<Friend> all(){
        String sql = "SELECT * FROM " + DbSchema.FriendTable.NAME;
        Cursor cursor = db.rawQuery(sql, null);

        //from cursor to model objects
        FriendCursorWrapper cursorWrapper = new FriendCursorWrapper(cursor);

        return cursorWrapper.getFriends();
    }

    /**
     * @modifies friend
     * @param friend
     * @return
     */
    public boolean add(Friend friend){
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);

        statement.bindString(1,friend.getName());
        statement.bindString(2,friend.getPhone());
        statement.bindString(3,friend.getEmail());

        long id = statement.executeInsert();//auto generated id
        if(id > 0){//insert success
            friend.setId(id);
            return true;
        }

        return false;
    }
    public boolean delete(long id){
        int result = db.delete(DbSchema.FriendTable.NAME, "id=?", new String[]{id +""});

        return result > 0;
    }
}
