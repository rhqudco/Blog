package bear.blog.service;

import bear.blog.domain.Note;
import bear.blog.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;

    @Transactional
    public Long writeNote(Note note) {
        noteRepository.save(note);
        return note.getNo();
    }
}
