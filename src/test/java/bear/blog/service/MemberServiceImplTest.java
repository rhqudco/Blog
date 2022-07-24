package bear.blog.service;

import bear.blog.common.WebSecurityConfig;
import bear.blog.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;


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
        assertThat(findMember).isNotNull();
    }

    @Test
    @DisplayName("패스워드 암호화 로그인 실패")
    public void validPasswordFailTest() {
        // given
        Member member = new Member();
        member.setId("test");
        member.setPassword(webSecurityConfig.getPasswordEncoder().encode("1234"));
        memberService.join(member);

        // when
        Member findMember = memberService.validLogin("test", "12345");

        // then
        assertThat(findMember).isNull();
    }

    @Test
    @DisplayName("로그아웃 테스트")
    public void logoutTest(HttpServletRequest request) {
        // given
        Member member = new Member();
        member.setId("test");
        member.setPassword(webSecurityConfig.getPasswordEncoder().encode("1234"));
        memberService.join(member);
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", member);

        // when
        request.getSession().invalidate();

        // then
        assertThat(session).isNull();

    }
}