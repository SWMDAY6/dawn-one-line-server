package day6.dawnoneline.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class PostDistanceRequestDto {
    private Integer distance; // 조회할 km 거리
    private Double latitude; // 유저 위도
    private Double longitude; // 유저 경도
    private Integer offset; // 조회 시작할 행 인덱스
    private Integer limit; // 조회할 행의 수
}
