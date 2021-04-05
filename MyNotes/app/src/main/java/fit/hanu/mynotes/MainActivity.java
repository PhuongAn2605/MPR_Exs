package fit.hanu.mynotes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import fit.hanu.mynotes.adapter.NoteAdapter;
import fit.hanu.mynotes.db.NoteManager;
import fit.hanu.mynotes.models.Note;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNotes;
    private List<Note> notes;
    private NoteAdapter noteAdapter;
    private NoteManager noteManager;
    private static final int ADD_NOTE = 1;
    private static final int EDIT_NOTE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteManager = NoteManager.getInstance(this);
        notes = noteManager.all();
        Log.i("MAINNOTE", " " + notes.toString());

        noteAdapter = new NoteAdapter(notes, this, MainActivity.this);
        rvNotes = findViewById(R.id.rvNotes);
        rvNotes.setAdapter(noteAdapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addNote){
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivityForResult(intent, ADD_NOTE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( resultCode == RESULT_OK && requestCode == EDIT_NOTE || resultCode == RESULT_OK && requestCode == ADD_NOTE){
//            recreate();
            notes.clear();
            notes.addAll(noteManager.all());

            noteAdapter.notifyDataSetChanged();
        }
//        if(resultCode == RESULT_OK && requestCode == ADD_NOTE){
//            notes.clear();
//            notes.addAll(noteManager.all());
//
//            noteAdapter.notifyDataSetChanged();
//            recreate();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}