package bear.blog.service;

import bear.blog.common.WebSecurityConfig;
import bear.blog.domain.Member;
import bear.blog.repository.MemberRepository;
import bear.blog.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final WebSecurityConfig webSecurityConfig;

    // 회원 가입
    @Override
    @Transactional
    public Long join(Member member){
        validateDuplicateMemberId(member);
        validateDuplicateMemberNickname(member);
        memberRepository.save(member);
        return member.getNo();
    }

    @Override
    public Member findOne(Long memberNo) {
        return memberRepository.findOne(memberNo);
    }

    @Override
    public Member validLogin(String id, String password) {
        validateExistentId(id);
        List<Member> findMember = memberRepository.findById(id);
        Member member = findMember.get(0);
        if (validateEqualPassword(password, member.getPassword())) {
            return member;
        }
        else {
            return null;
        }
    }

    // 비밀번호 일치 검사
    @Override
    public boolean validateEqualPassword(String rawPassword, String encodedPassword) {
        return webSecurityConfig.getPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    // 로그인시 아이디 존재유무 검사
    private void validateExistentId(String id) {
        List<Member> findMember = memberRepository.findById(id);
        if(findMember.isEmpty()) {
            throw new IllegalStateException("존재하지 않는 아이디 입니다.");
        }
    }

    // 아이디 중복 검사
    private void validateDuplicateMemberId(Member member) {
        List<Member> findMember = memberRepository.findById(member.getId());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }
    // 닉네임 중복 검사
    private void validateDuplicateMemberNickname(Member member) {
        List<Member> findMember = memberRepository.findByNickname(member.getNickname());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }
}