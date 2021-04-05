package fit.hanu.myfriends.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import fit.hanu.myfriends.models.Friend;

public class FriendCursorWrapper extends CursorWrapper {

    public FriendCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Friend getFriend(){
       long id = getLong(getColumnIndex("id"));

        String name = getString(getColumnIndex(DbSchema.FriendTable.cols.NAME));
        String phoneNo = getString(getColumnIndex(DbSchema.FriendTable.cols.PHONE_NO));
        String email = getString(getColumnIndex(DbSchema.FriendTable.cols.EMAIL));

        Friend friend = new Friend(name, phoneNo, email);

        return friend;
    }
    public List<Friend> getFriends(){
        List<Friend> friends = new ArrayList<>();
        moveToFirst();
        while(!isAfterLast()){
            Friend friend = getFriend();
            friends.add(friend);
            moveToNext();
        }
        return friends;
    }
}
