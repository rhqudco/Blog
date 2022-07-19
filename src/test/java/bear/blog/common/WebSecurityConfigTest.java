package bear.blog.common;

import bear.blog.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


public class WebSecurityConfigTest {

    private MemberService memberService;

    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("PW Encrypt Test")
    public void passwordEncode() {
        // given
        String normalPW = "12345";

        // when
        String encodedPassword = passwordEncoder.encode(normalPW);

        // then
        assertAll(
                () -> assertNotEquals(normalPW, encodedPassword),
                () -> assertTrue(passwordEncoder.matches(normalPW, encodedPassword))

        );
        Assertions.assertThat(normalPW).isNotEqualTo(encodedPassword);
    }

}