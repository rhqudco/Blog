package bear.blog.controller;

import bear.blog.common.WebSecurityConfig;
import bear.blog.domain.Member;
import bear.blog.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/member/login")
    public String loginForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/loginForm";
    }

    @PostMapping("/member/login")
    public String login(MemberForm form, HttpServletRequest request) {
        Member member = memberService.validLogin(form.getId(), form.getPassword());
        if(member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            System.out.println("??????");
            return "redirect:/";
        }
        else {
            System.out.println("??????");
            return "members/loginForm";
        }
    }

    @DeleteMapping("/member/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}