package day6.dawnoneline.dto.response;

import java.time.LocalDateTime;

import day6.dawnoneline.domain.Comment;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentResponseDto {

    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        content = comment.getContent();
        password = comment.getPassword();
        createdAt = comment.getCreatedAt();
        modifiedAt = comment.getModifiedAt();
    }
}
