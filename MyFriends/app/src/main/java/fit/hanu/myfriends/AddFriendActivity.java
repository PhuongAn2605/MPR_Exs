package fit.hanu.myfriends;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import fit.hanu.myfriends.db.FriendManager;
import fit.hanu.myfriends.models.Friend;
import fit.hanu.myfriends.models.FriendMangament;

public class AddFriendActivity extends AppCompatActivity {
    private EditText txtName, txtEmail, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ImageButton btnOK = findViewById(R.id.btnOK);
        ImageButton btnCancel = findViewById(R.id.btnCancel);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);

//        String name = txtName.getText().toString();
//        String email = txtEmail.getText().toString();
//        String phone = txtPhone.getText().toString();

        List<Friend> friends = FriendMangament.getInstance().getList();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddFriendActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Are you sure to add this friend to Friend List?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                confirmAddFriend();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void confirmAddFriend(){
        String name = txtName.getText().toString();
//        Log.d("NAME", "" + name);
        String email = txtEmail.getText().toString();
//        Log.d("NAME", "" + email);

        String phone = txtPhone.getText().toString();
//        Log.d("NAME", "" + phone);

        //validate data
        if(name.equals("") || email.equals("") || phone.equals("")){
            Toast.makeText(this, "Missing values!", Toast.LENGTH_LONG).show();
        }else{
            Friend friend = new Friend(name, phone, email);

            //save in db
            FriendManager friendManager = FriendManager.getInstance(this);
            friendManager.add(friend);

            Toast.makeText(this, "New friend inserted!", Toast.LENGTH_LONG).show();

            setResult(RESULT_OK);
            finish();
        }
//                Friend friend = new Friend("Phuong", "857394", "dhfsj");

//                friends.add(0, friend);
//                Intent intent = new Intent(AddFriendActivity.this, MainActivity.class);
//                startActivity(intent);

//        Intent intent = new Intent();
//        intent.putExtra("FRIEND", friend);
//
//        setResult(RESULT_OK, intent);
//        //finish current activity and back to previous one
//        finish();
    }
}