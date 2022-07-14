package bear.blog.service;

import bear.blog.domain.Note;
import bear.blog.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    public String setFilesName(List<MultipartFile> files) throws IOException {
        Note note = new Note();
        return note.fileUpload(files);
    }
    public String setImagesName(List<MultipartFile> files) throws IOException {
        Note note = new Note();
        return note.fileUpload(files);
    }
}
