package day6.dawnoneline.dto.request;

import day6.dawnoneline.domain.Post;
import day6.dawnoneline.domain.TimeStamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PostSaveRequestDto extends TimeStamp {
    private String content;
    private String password;
    private Double latitude;
    private Double longitude;

    public Post toEntity() {
        return Post.builder()
            .content(content)
            .password(password)
            .latitude(latitude)
            .longitude(longitude)
            .build();
    }
}
