package bear.blog.repository;

import bear.blog.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    @Override
    public void save(Member member) {
        if(member.getNo() == null) {
            em.persist(member);
            // no 값이 없기 때문에 (신규 등록이기 때문에) 신규 등록 역할
        } else {
            em.merge(member);
            // no 값이 있기 때문에 (중복 등록이기 때문에) 업데이트 역할
        }
    }

    @Override
    public Member findOne(Long no) {
        return em.find(Member.class, no);
    }

    @Override
    public List<Member> findById(String id) {
        return em.createQuery("Select m From Member m where m.id =:id", Member.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Member> findByNickname(String nickname) {
        return em.createQuery("Select m From Member m where m.nickname =:nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
}