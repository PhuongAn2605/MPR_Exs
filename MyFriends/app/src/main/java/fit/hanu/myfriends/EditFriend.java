package fit.hanu.myfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import fit.hanu.myfriends.adapters.FriendAdapter;
import fit.hanu.myfriends.models.Friend;
import fit.hanu.myfriends.models.FriendMangament;

public class EditFriend extends AppCompatActivity {

    private Friend friend;
    private EditText edtName, edtPhone, edtEmail;
    private Button cancel, save;
    private  int position = -1;
    private Friend fr = new Friend();

    FriendMangament friends = FriendMangament.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_friend);

        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);

        friend = (Friend) getIntent().getSerializableExtra("EDIT FRIEND");
//        List<Friend> friends = fr.getFriendList();
        Log.d("PHUONG", " "+ friend.toString());
//                Log.d("PHUONG_EDIT", " "+ friends.toString());

//        int position = Integer.parseInt(getIntent().getStringExtra("POSITION"));
        for(int i=0; i<friends.size(); i++){
            Friend f = friends.get(i);
            if(f.getName().equalsIgnoreCase(friend.getName()) &&
            f.getEmail().equalsIgnoreCase(friend.getEmail()) &&
            f.getPhone().equalsIgnoreCase(friend.getPhone())){
                position = i;
            }
        }

//        int position = MainActivity.getIndex(friend);

                Log.d("PHUONG", " "+ position);


        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);

        edtName.setText(friend.getName());
        edtPhone.setText(friend.getPhone());
        edtEmail.setText(friend.getEmail());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                Friend f = new Friend(name, phone, email);

                friends.set(position, f);

                Log.d("PHUONG_EDIT", " "+ f.toString());

                Intent intent = new Intent(EditFriend.this, MainActivity.class);
                startActivity(intent);
//                intent.putExtra("FRIEND EDITED", f);
//                intent.putExtra("POSITION EDITED", position);
//
//                setResult(RESULT_OK, intent);
//                finish();
//                Log.d("PHUONG", friends.toString());
//                intent.putExtra("FRIEND EDITED", f);
//                intent.putExtra("POSITION", position);
//                startActivity(intent);

//                Intent intent = new Intent();
//                intent.putExtra("FRIEND EDITED", friend);
//
//                setResult(RESULT_OK, intent);

//                finish();

            }
        });

    }
}