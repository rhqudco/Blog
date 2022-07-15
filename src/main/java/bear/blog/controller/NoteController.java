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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
    public String writeNote(@Valid NoteForm form, BindingResult result,
                            @RequestParam("files") List<MultipartFile> files,
                            @RequestParam("images") List<MultipartFile> images) throws IOException {
        if(result.hasErrors()) {
            return "note/writeForm";
        }

        // 로그인하면 세션에서 값 받아올 것.
        // 일단 임시로 작성하는 코드
        Member member = new Member();



        String filesName = noteService.fileUpload(files);
        String imagesName = noteService.fileUpload(images);

        Note note = new Note(member.getNickname(), form.getTitle(), form.getContent(), 0, 0,
                LocalDateTime.now(), filesName, imagesName, form.getSecret(), form.getPassword(),
                form.getCtgNo(), 0, form.getPin());

        noteService.writeNote(note);

        return "redirect:/";
    }
}