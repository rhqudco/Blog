package bear.blog.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

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
    private Date date;
    private String fileName;
    private String imageName;
    private String secret;
    private String password;
    private int ctgNo;
    private int commentCount;
    private String pin;
}
