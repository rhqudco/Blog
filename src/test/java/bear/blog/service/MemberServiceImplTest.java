package bear.blog.service;

import bear.blog.common.WebSecurityConfig;
import bear.blog.domain.Member;
import bear.blog.repository.MemberRepository;
import bear.blog.repository.MemberRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImplTest {

    @Autowired
    WebSecurityConfig webSecurityConfig;
    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("패스워드 암호화 로그인")
    public void validPasswordTest() {
        // given
        Member member = new Member();
        member.setId("test");
        member.setPassword(webSecurityConfig.getPasswordEncoder().encode("1234"));
        memberService.join(member);

        // when
        Member findMember = memberService.validLogin("test", "1234");

        // then
        Assertions.assertThat(findMember).isNotNull();
    }
}