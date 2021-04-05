package fit.hanu.mynotes.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import fit.hanu.mynotes.models.Note;


//CRUD here, work with database
public class NoteManager {
    private static NoteManager instance;

    private static final String INSERT_STMT = "INSERT INTO " + DbSchema.NoteTable.NAME + "(text) VALUES (?)";
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    private NoteManager(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static NoteManager getInstance(Context context) {
        if (instance == null) {
            instance = new NoteManager(context);
        }
        return instance;
    }

    public List<Note> all() {
        String sql = "SELECT * FROM notes";
        Cursor cursor = db.rawQuery(sql, null);
        NoteCursorManager cursorManager = new NoteCursorManager(cursor);

        return cursorManager.getNotes();

    }

//    public long getAutoId(Note note){
//        long autoId = getLong
//    }

    public boolean add(Note note){
        SQLiteStatement statement = db.compileStatement(INSERT_STMT);

        statement.bindString(1,note.getText());

        long id = statement.executeInsert();
        Log.i("ADD_NOTE", " " + id);
//        statement.executeUpdateDelete();

        if(id  > 0){
            //important to set id for a new note because id is auto increment
            note.setId(id);
            Log.i("ADD_NOTE_SET", " " + note.getId());

            return true;
        }else{
            return false;
        }
    }

    public Note findByID(long id) {
//        SQLiteDatabase db = get
//
        String sql = "SELECT * FROM notes WHERE id = ?";
//        if(id == 0){
//
//        }
        Cursor cursor = db.rawQuery(sql, new String[]{id +""});
        NoteCursorManager cursorManager = new NoteCursorManager(cursor);

        return cursorManager.getNote(cursor);
    }

    public boolean update(Note note){
        ContentValues cv = new ContentValues();
        cv.put(DbSchema.NoteTable.Cols.TEXT, note.getText());
        long result = db.update(DbSchema.NoteTable.NAME, cv, "id=?", new String[]{String.valueOf(note.getId())});
        Log.i("RESULT", " " + result);
        return result > 0;
    }

    public boolean delete(long id){
        int result = db.delete(DbSchema.NoteTable.NAME, "id= ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    }
