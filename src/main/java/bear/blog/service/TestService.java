package bear.blog.service;

import bear.blog.domain.TestDomain;
import bear.blog.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    // 테스트 회원 가입
    @Transactional
    public Long join(TestDomain domain) {
        testRepository.save(domain);
        return domain.getId();
    }
}