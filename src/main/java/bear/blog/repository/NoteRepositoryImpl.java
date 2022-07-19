package bear.blog.repository;

import bear.blog.domain.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class NoteRepositoryImpl implements NoteRepository{

    private final EntityManager em;

    @Override
    public void save(Note note) {
        em.persist(note);
    }
}
