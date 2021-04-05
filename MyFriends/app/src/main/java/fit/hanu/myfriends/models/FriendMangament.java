package fit.hanu.myfriends.models;

import java.util.ArrayList;
import java.util.List;

public class FriendMangament {

    private List<Friend> friendList = new ArrayList<>();
    private static FriendMangament instance = null;

    private FriendMangament(){
    }

    public static FriendMangament getInstance(){
        if(instance == null)
        {
            instance = new FriendMangament();
            instance.add(new Friend(" Phuong An", "012345", "anphuong@gmail.com"));
            instance.add(new Friend("Mai Lien", "012345", "anphuong@gmail.com"));
            instance.add(new Friend("Nguyen Phuong", "012345", "anphuong@gmail.com"));
            instance.add(new Friend("Quang An", "012345", "anphuong@gmail.com"));
        }
        return instance;
    }

    public void add(Friend friend)
    {
        this.friendList.add(friend);
    }

    public void remove(Friend friend)
    {
        this.friendList.remove(friend);
    }

    public Friend get(int position)
    {
        return this.friendList.get(position);
    }

    public int size(){
        return this.friendList.size();
    }

    public List<Friend> getList()
    {
        return friendList;
    }

    public void set (int i, Friend friend)
    {
        this.friendList.set(i,friend);
    }
}
