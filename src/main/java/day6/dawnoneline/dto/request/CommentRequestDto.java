package day6.dawnoneline.dto.request;

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.TimeStamp;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentRequestDto extends TimeStamp {

    private Long commentId;
    private Long postId;
    private String content;
    private String password;

    public Comment toEntity() {
        return Comment.builder()
            .content(content)
            .password(password)
            .build();
    }
}
