package bear.blog.service;

import bear.blog.domain.Member;

public interface MemberService {
    Long join(Member member);
    Member findOne(Long memberNo);
    Member validLogin(String id, String password);
}
