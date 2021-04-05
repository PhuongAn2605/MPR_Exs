package fit.hanu.mynotes.models;

import java.io.Serializable;
import java.util.Objects;

public class Note implements Serializable {
    private long id;
    private String text;

    public Note(String text) {
        this.text = text;
    }

    public Note(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                Objects.equals(text, note.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
