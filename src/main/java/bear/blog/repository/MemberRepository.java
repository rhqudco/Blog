package bear.blog.repository;

import bear.blog.domain.Member;

import java.util.List;

public interface MemberRepository {
    void save(Member member);
    Member findOne(Long no);
    List<Member> findById(String id);
    List<Member> findByNickname(String nickname);
}
