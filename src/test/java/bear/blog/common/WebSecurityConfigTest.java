package bear.blog.common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WebSecurityConfigTest {

    WebSecurityConfig webSecurityConfig = new WebSecurityConfig();

    @Test
    @DisplayName("패스워드 암호화 테스트")
    void encodeTest() {
        // given
        String rawPW = "1234";

        // when
        String encodePW = webSecurityConfig.getPasswordEncoder().encode(rawPW);

        // then
        assertThat(rawPW).isNotEqualTo(encodePW);

        // print
        System.out.println("rawPW = " + rawPW);
        System.out.println("encodePW = " + encodePW);
    }

    @Test
    @DisplayName("패스워드 일치 테스트")
    void matchTest() {
        // given
        String rawPW = "1234";
        String encodePW = webSecurityConfig.getPasswordEncoder().encode(rawPW);

        // when
        Boolean check = webSecurityConfig.getPasswordEncoder().matches(rawPW, encodePW);

        // then
        assertThat(check).isEqualTo(true);
    }

    @Test
    @DisplayName("패스워드 불일치 테스트")
    void notMatchTest() {
        // given
        String originalPW = "1234";
        String mistakePW = "12345678";
        String encodeFakePW = webSecurityConfig.getPasswordEncoder().encode(mistakePW);

        // when
        Boolean check = webSecurityConfig.getPasswordEncoder().matches(originalPW, encodeFakePW);

        // then
        assertThat(check).isEqualTo(false);

        // print
        System.out.println("originalPW = " + originalPW);
        System.out.println("mistakePW = " + mistakePW);
        System.out.println("encodeFakePW = " + encodeFakePW);
    }
}