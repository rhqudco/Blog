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
        String inputPW = "1234";

        // when
        Boolean check = webSecurityConfig.getPasswordEncoder().matches(inputPW, encodePW);

        // then
        assertThat(check).isEqualTo(true);
    }

    @Test
    @DisplayName("패스워드 불일치 테스트")
    void notMatchTest() {
        // given
        String originalPW = "1234"; // 설정한 PW의 원본
        String inputPW = "123456"; // 입력한 PW의 원본
        String encodePW = webSecurityConfig.getPasswordEncoder().encode(originalPW); // 설정한 PW를 인코딩한 값

        // when
        Boolean check = webSecurityConfig.getPasswordEncoder().matches(inputPW, encodePW); // 입력한 PW와 설정한 PW를 인코딩한 값을 matches()를 통해 비교한다.
        // originalPW와 inputPW가 다르기 때문에 false 나와야 함.

        // then
        assertThat(check).isEqualTo(false);
    }
}