package fit.hanu.myfriends.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fit.hanu.myfriends.EditFriend;
import fit.hanu.myfriends.R;
import fit.hanu.myfriends.db.FriendManager;
import fit.hanu.myfriends.models.Friend;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder>{
    private static List<Friend> friends;
    private Context context;
    public final int FRIEND_EDIT = 1;

    public FriendAdapter(List<Friend> friends){
        this.friends = friends;
        this.context = context;
    }

//    public static List<Friend> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(List<Friend> friends) {
//        this.friends = friends;
//    }

    @NonNull
    @Override
    public FriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //activity to display
        Context context = parent.getContext();//activity main

        //layoutInflater: layout in xml -> java object refs
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_friend, parent, false);

        //manage view holder for each item
        return new FriendHolder(itemView, context);

    }

    @Override
    public void onBindViewHolder(@NonNull FriendHolder holder, int position) {
        Friend friend = friends.get(position);

        //bind data with view template
        holder.bind(friend);
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }


    public class FriendHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private ImageButton btnCall, btnSendEmail, btnSendSMS, btnEdit, btnDelete;
        private Context context;

        public FriendHolder(@NonNull View itemView, Context context) {
            super(itemView);

            this.context = context;

            tvName = itemView.findViewById(R.id.tvName);
            btnSendEmail = itemView.findViewById(R.id.btnSendEmail);
            btnSendSMS = itemView.findViewById(R.id.btnSendSMS);
            btnCall = itemView.findViewById(R.id.btnCall);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

//            Intent intent = getIntent().getSerialisabeExtra("FRIEND EDITED");
        }


        public void bind(Friend friend){
            tvName.setText(friend.getName());

            //handle event

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+friend.getPhone()));

                    //start activity from which activity
                    context.startActivity(intent);


                }
            });

            btnSendSMS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("smsto: " + friend.getPhone());
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//                    intent.setData(Uri.parse("send sms: " + friend.getPhone()));

                    context.startActivity(intent);
                }
            });

            btnSendEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("mailto:" + friend.getEmail());
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(uri);
//                    intent.setData(Uri.parse("emailto:" + friend.getEmail()));
//                    context.startActivity(Intent.createChooser(intent, "Send email"));
                    context.startActivity(intent);
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = friends.indexOf(friend);

                    Intent intent = new Intent(context, EditFriend.class);
                    intent.putExtra("EDIT FRIEND", friend);
//                    intent.putExtra("POSITION", position);

                    context.startActivity(intent);

                    notifyItemChanged(position);
//                    ((Activity) context).startActivityForResult(intent, FRIEND_EDIT );
                }
            });


            //delete this friend
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = friends.indexOf(friend);
                    new AlertDialog.Builder(context)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Delete")
                            .setMessage("Are you sure to delete?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    friends.remove(friend);
                                    //delete from database
                                    FriendManager friendManager = FriendManager.getInstance(context);
                                    friendManager.delete(friend.getId());
                                    Toast.makeText(context, "Deleted friend " + friend.getName(), Toast.LENGTH_LONG).show();
                                    notifyItemRemoved(position);
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
//                    friends.remove(friend);
//                    //notify recyclerview to update this change
//                    notifyDataSetChanged();
//
//                    //optimize
//                    notifyItemRemoved(position);
                }
            });



        }

//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if(resultCode == RESULT_OK &&)
//        }
    }
}
