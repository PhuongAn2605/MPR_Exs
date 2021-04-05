package fit.hanu.myfriends;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import fit.hanu.myfriends.adapters.FriendAdapter;
import fit.hanu.myfriends.db.FriendManager;
import fit.hanu.myfriends.models.Friend;
import fit.hanu.myfriends.models.FriendMangament;

public class MainActivity extends AppCompatActivity {
    private static List<Friend> friends;
    private RecyclerView rvFriends;
    public static final int FRIEND_ADDED = 1;
    private Friend friend = new Friend();
//    private List<Friend> friends;
    private FriendAdapter friendAdapter;
    private FriendManager friendManager;

//    FriendMangament friendList = FriendMangament.getInstance();
//    List<Friend> friends = friendList.getList();


//    public static List<Friend> getFriends() {
//        return friends;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFriends = findViewById(R.id.rvFriends);

        //demo data
//        friends = new ArrayList<>();
//        friends.add(new Friend("Phuong An", "0387782469", "anphuong@gmail.com"));
//        friends.add(new Friend("Mai Lien", "0387782469", "anphuong@gmail.com"));
//        friends.add(new Friend("Hong Giang", "0387782469", "anphuong@gmail.com"));
//        friends.add(new Friend("Thu Ha", "0387782469", "anphuong@gmail.com"));

        friendManager = FriendManager.getInstance(this);
        friends = friendManager.all();


//        friends = friend.getFriendList();
        //setup recycler view
        //adapter
        friendAdapter = new FriendAdapter(friends);
        rvFriends.setAdapter(friendAdapter);

        //layout manager
        rvFriends.setLayoutManager(new LinearLayoutManager(this));

        //handle add action
        ImageButton btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriendActivity.class);
                startActivityForResult(intent, FRIEND_ADDED);

            }
        });

//        Log.d("PHUONG_MAIN", friends.toString());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//            Log.d("RESULT CODE", ""+ REQUEST_OK);
        if(resultCode == RESULT_OK && requestCode == FRIEND_ADDED){
            //refresh data
            friends.clear();
            friends.addAll(friendManager.all());
            friendAdapter.notifyDataSetChanged();

        }


    }
}