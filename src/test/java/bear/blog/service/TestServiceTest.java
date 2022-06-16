package bear.blog.service;

import bear.blog.domain.TestDomain;
import bear.blog.repository.TestRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestServiceTest {
    @Autowired
    TestService testService;
    @Autowired
    TestRepository testRepository;

    @Test
    public void join() throws Exception {
        // given
        TestDomain testDomain = new TestDomain();
        // when
        Long savedId = testService.join(testDomain);
        // then
        Assertions.assertThat(savedId).isEqualTo(2);
     }
}