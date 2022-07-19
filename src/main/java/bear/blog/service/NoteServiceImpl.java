package bear.blog.service;

import bear.blog.domain.Note;
import bear.blog.repository.NoteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoteServiceImpl implements NoteService{

    private final NoteRepositoryImpl noteRepository;

    @Override
    @Transactional
    public Long writeNote(Note note) {
        noteRepository.save(note);
        return note.getNo();
    }

    @Override
    public String fileUpload(List<MultipartFile> files) throws IOException {
        Note note = new Note();
        return note.fileUpload(files);
    }
}
