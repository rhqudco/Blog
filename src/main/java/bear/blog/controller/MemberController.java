package bear.blog.controller;

import bear.blog.common.WebSecurityConfig;
import bear.blog.domain.Member;
import bear.blog.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;
    private final WebSecurityConfig passwordEncrypt;

    @GetMapping("/member/join")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/joinForm";
    }

    @PostMapping("/member/join")
    public String createMember(@Valid MemberForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "members/joinForm";
        }
        String encryptPW = passwordEncrypt.getPasswordEncoder().encode(form.getPassword());
        Member member = new Member(form.getId(), encryptPW, form.getName(), form.getNickname(), form.getEmail(), form.getProfileImage(), form.getIntroduce());

        memberService.join(member);

        return "redirect:/";
    }
}
