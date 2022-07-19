package bear.blog.service;

import bear.blog.domain.Member;
import bear.blog.repository.MemberRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepositoryImpl memberRepository;

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

    private void validateDuplicateMemberId(Member member) {
        List<Member> findMember = memberRepository.findById(member.getId());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }
    private void validateDuplicateMemberNickname(Member member) {
        List<Member> findMember = memberRepository.findByNickname(member.getNickname());
        if(!findMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }
}