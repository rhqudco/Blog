package bear.blog.service;

import bear.blog.common.WebSecurityConfig;
import bear.blog.domain.Member;
import bear.blog.repository.MemberRepository;
import bear.blog.repository.MemberRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceImplTest {

    MemberService memberService;

    @Test
    @DisplayName("로그인 테스트")
    void validLoginTest() {

        WebSecurityConfig webSecurityConfig = new WebSecurityConfig();

        // given
        Member member = new Member();
        member.setId("test");
        member.setPassword(webSecurityConfig.getPasswordEncoder().encode("1234"));

        // when
        Member loginMember = memberService.validLogin(member.getId(), member.getPassword());

        // then
        Assertions.assertThat(loginMember).isNotNull();

    }

}