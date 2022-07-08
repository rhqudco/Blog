package bear.blog.repository;

import bear.blog.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long no) {
        return em.find(Member.class, no);
    }

    public List<Member> findById(String id) {
        return em.createQuery("Select m From Member m where m.id =:id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Member> findByNickname(String nickname) {
        return em.createQuery("Select m From Member m where m.nickname =:nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
}