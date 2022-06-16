package bear.blog.repository;

import bear.blog.domain.TestDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class TestRepository {
    private final EntityManager em;

    public void save(TestDomain domain) {
        em.persist(domain);
    }
}