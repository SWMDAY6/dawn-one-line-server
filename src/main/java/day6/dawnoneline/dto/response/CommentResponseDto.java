package day6.dawnoneline.dto.response;

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.TimeStamp;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentResponseDto extends TimeStamp {

    private String content;
    private String password;

    public CommentResponseDto(Comment comment) {
        content = comment.getContent();
        password = comment.getPassword();
    }
}
