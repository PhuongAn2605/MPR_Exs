package fit.hanu.mynotes.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

import fit.hanu.mynotes.EditNoteActivity;
import fit.hanu.mynotes.R;
import fit.hanu.mynotes.db.NoteManager;
import fit.hanu.mynotes.models.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes;
    private Context context;
    private Activity activity;
    private final int EDIT_NOTE = 2;

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    public NoteAdapter(List<Note> notes, Context context, Activity activity) {
        this.notes = notes;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_note, parent, false);

        return new NoteHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = this.notes.get(position);
        holder.bind(note,position);
    }

    @Override
    public int getItemCount() {
        return this.notes.size();
    }

//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }

    public class NoteHolder extends ViewHolder {

        private TextView tvNote;
        private Context context;
        public NoteHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;

            tvNote = itemView.findViewById(R.id.tvNote);
        }



        public void bind(Note note, int position) {
//            Log.i("POSITION", " " + position);
            tvNote.setText(note.getText());
//            Log.i("ID_NOTE_BEGIN", "" + note.getId());


            //click to edit/view
            tvNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("POSITION", " " + position);

                    Intent intent = new Intent(context, EditNoteActivity.class);
                    long id = note.getId();
                    Log.i("ID_NOTE_LONG", "" + note.getId());


                    intent.putExtra("ID", String.valueOf(id));
//                    intent.putExtra("TEXT", note.getText());
                    Log.i("ID_NOTE", "" + id);

                    activity.startActivityForResult(intent, EDIT_NOTE);

                }
            });

            tvNote.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
//                    NoteManager noteManager = NoteManager.getInstance(context);
//                    noteManager.delete(note.getId());
//
//                    notes.remove(note);
//                    notifyDataSetChanged();
//                    return true;
                    new AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure to delete this note?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                   NoteManager.getInstance(context).delete(note.getId());
                                   notes.remove(note);
                                   notifyDataSetChanged();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
                    return true;
                }
            });
        }


    }
}
