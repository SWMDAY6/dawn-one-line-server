package day6.dawnoneline.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String content;
    private Double latitude;
    private Double longitude;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        postId = post.getId();
        content = post.getContent();
        latitude = post.getLatitude();
        longitude = post.getLongitude();
        createdAt = post.getCreatedAt();
        modifiedAt = post.getModifiedAt();

        List<Comment> commentList = post.getComments();
        comments = commentList.stream()
            .map(c -> new CommentResponseDto(c))
            .collect(Collectors.toList());
    }
}
