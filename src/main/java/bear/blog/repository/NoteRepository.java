package bear.blog.repository;

import bear.blog.domain.Note;

public interface NoteRepository {
    void save(Note note);
}
