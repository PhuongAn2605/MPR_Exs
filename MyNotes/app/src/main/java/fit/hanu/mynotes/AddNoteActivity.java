package fit.hanu.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import fit.hanu.mynotes.db.NoteManager;
import fit.hanu.mynotes.models.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText txtAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        txtAdd = findViewById(R.id.txtAdd);

    }

    @Override
    public void onBackPressed() {

        String newNote = txtAdd.getText().toString();
        if(newNote.equals("")){
            Toast.makeText(this, "Missing content", Toast.LENGTH_LONG).show();
        }else{
            NoteManager noteManager = NoteManager.getInstance(this);
            Note note = new Note(newNote);

            Toast.makeText(this, "New note added", Toast.LENGTH_LONG).show();
            noteManager.add(note);
            setResult(RESULT_OK);
        }
        super.onBackPressed();
    }
}