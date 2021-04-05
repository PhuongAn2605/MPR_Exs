package fit.hanu.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fit.hanu.mynotes.db.NoteManager;
import fit.hanu.mynotes.models.Note;

public class EditNoteActivity extends AppCompatActivity {

    private Note note;
    private EditText txtNote;
    private NoteManager noteManager;
    private long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtNote = findViewById(R.id.txtNote);

        Intent intent = getIntent();
        if(intent.hasExtra("ID")){

            id = Long.parseLong(intent.getStringExtra("ID"));
//            long id = 0;
            Log.i("EDIT_ID", "ID" + id);
//            String text = intent.getStringExtra("TEXT");


            noteManager = NoteManager.getInstance(this);
            note = noteManager.findByID(id);
            Log.i("NOTE_EDIT", "NOTE" + note.getText());
            txtNote.setText(note.getText());

        }
    }

    @Override
    public void onBackPressed() {
        String editText = txtNote.getText().toString();
//        Log.i("TEXT_EDIT", " " + editText);
        //important to set id for a new note because id is auto increment
        note.setId(id);
        note.setText(editText);
        boolean flag = noteManager.update(note);
//        Log.i("FLAG", " " + flag);
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}