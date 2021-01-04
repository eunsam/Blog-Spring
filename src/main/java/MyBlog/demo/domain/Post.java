package MyBlog.demo.domain;


import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Post {

    @GeneratedValue
    @Id
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    private LocalDateTime regDate;

    Post(){

    }

}
