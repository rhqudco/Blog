package bear.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    private Long no;

    private String id;
    private String password;
    private String name;
    private String nickname;
    private String email;
    private String profileImage;
    private String introduce;

    public Member(String id, String password, String name, String nickname, String email, String profileImage, String introduce) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.introduce = introduce;
    }

    public Member() {

    }
}
