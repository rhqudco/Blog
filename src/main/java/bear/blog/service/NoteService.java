package bear.blog.service;

import bear.blog.domain.Note;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoteService {
    Long writeNote(Note note);
    String fileUpload(List<MultipartFile> files) throws IOException;
}
