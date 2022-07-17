package bear.blog.domain;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Note {
    @Id
    @GeneratedValue
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

    // 글 작성 파일 업로드 메소드 (문자열 합치는 메소드랑 파일 업로드 메소드 공통 코드로 분류해서 작성해야함)
    public String fileUpload(List<MultipartFile> files) throws IOException {
        String basePath = "/upload"; // 파일 업로드될 경로
        String originalName = "";
        StringBuilder test = new StringBuilder();
        StringBuilder name = new StringBuilder();
        if (files.size() >= 1) {
            // 파일 업로드(여러개) 처리 부분
            for (MultipartFile file : files) {
                originalName = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "");
                String filePath = basePath + "/" + originalName;
                if (originalName.equals("")) { // 파일 아에 없을 때도 size가 1이라 orginalName이 공백이면 작동
                    System.out.println("등록된 파일 없음.");
                }
                // 파일 1개 일 때도 size가 1로 들어와서 파일이 1개 있을 때 else문 작동
                else {
                    File dest = new File(filePath);
                    file.transferTo(dest);
                }
//                name.append(originalName).append(" ");
                test.append(fileNameAppend(originalName, name));
            }
        }
        return name.toString();
    }

    public String fileNameAppend(String originalName, StringBuilder name) {

        name.append(originalName).append(" ");

        return name.toString();
    }
}