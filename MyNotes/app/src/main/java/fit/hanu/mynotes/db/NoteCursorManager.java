package fit.hanu.mynotes.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;
import java.util.List;

import fit.hanu.mynotes.models.Note;

public class NoteCursorManager extends CursorWrapper {

    public NoteCursorManager(Cursor cursor) {
        super(cursor);
    }

    public Note getNote(){
        String text = getString(getColumnIndex(DbSchema.NoteTable.Cols.TEXT));
        long id = getLong(getColumnIndex("id"));
        Note note = new Note(id, text);
        return note;
    }

    public Note getNote(Cursor cursor){
        moveToFirst();
        Note note = null;
        if(!isAfterLast()){
//            moveToFirst();
            note = getNote();
        }
        return note;
    }

    public List<Note> getNotes(){
        List<Note> notes = new ArrayList<>();

        moveToFirst();
        while(!isAfterLast()){
            Note note = getNote();
            notes.add(note);

            moveToNext();
        }
        return notes;
    }

    public long getAutoId(Cursor cursor){
        long autoId = getLong(getColumnIndex(DbSchema.NoteTable.Cols.ID));
        return autoId;
    }

}
