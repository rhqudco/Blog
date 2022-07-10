package bear.blog.controller;

import bear.blog.domain.Member;
import bear.blog.domain.Note;
import bear.blog.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/note/write")
    public String writeForm(Model model) {
        model.addAttribute("noteForm", new NoteForm());
        return "note/writeForm";
    }
    @PostMapping("/note/writeNote")
    public String writeNote(@Valid NoteForm form, BindingResult result) {
        if(result.hasErrors()) {
            return "note/writeForm";
        }

        // 로그인하면 세션에서 값 받아올 것.
        // 일단 임시로 작성하는 코드
        Member member = new Member();

        Note note = new Note(member.getNickname(), form.getTitle(), form.getContent(), 0, 0,
                LocalDateTime.now(), form.getFileName(), form.getImageName(), form.getSecret(), form.getPassword(),
                form.getCtgNo(), 0, form.getPin());

        noteService.writeNote(note);

        return "redirect:/";
    }
}