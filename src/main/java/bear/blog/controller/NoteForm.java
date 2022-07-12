package bear.blog.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoteForm {
    private String nickName;
    private String title;
    private String content;
    private int view;
    private int noteLike;
    private LocalDateTime date;
    private String fileName;
    private String imageName;
    private String secret;
    private String password;
    private int ctgNo;
    private int commentCount;
    private String pin;

    public void mergeFileName() {

    }
}
