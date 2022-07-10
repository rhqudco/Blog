package bear.blog.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Note {
    @Id @GeneratedValue
    private Long no;

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

    public Note(String nickName, String title, String content, int view, int noteLike, LocalDateTime date, String fileName, String imageName, String secret, String password, int ctgNo, int commentCount, String pin) {
        this.nickName = nickName;
        this.title = title;
        this.content = content;
        this.view = view;
        this.noteLike = noteLike;
        this.date = date;
        this.fileName = fileName;
        this.imageName = imageName;
        this.secret = secret;
        this.password = password;
        this.ctgNo = ctgNo;
        this.commentCount = commentCount;
        this.pin = pin;
    }

    public Note() {
    }
}