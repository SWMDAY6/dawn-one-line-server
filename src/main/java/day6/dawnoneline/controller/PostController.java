package day6.dawnoneline.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import day6.dawnoneline.dto.request.PostDistanceRequestDto;
import day6.dawnoneline.dto.request.PostSaveRequestDto;
import day6.dawnoneline.dto.response.PostResponseDto;
import day6.dawnoneline.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/boards/posts")
    @ApiOperation(value = "게시글 생성")
    public Long createPost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        return postService.createPost(postSaveRequestDto);
    }

    @GetMapping("/boards/posts")
    @ApiOperation(value = "전체 게시글 조회")
    public List<PostResponseDto> findAll(
        @ApiParam(value = "몇번째 행부터 출력할지 선택", example = "1") @RequestParam(value = "offset", defaultValue = "0") Integer offset,
        @ApiParam(value = "출력할 행의 수", example = "100") @RequestParam(value = "limit", defaultValue = "100") Integer limit) {
        return postService.findAll(offset, limit);
    }

    @GetMapping("/boards/posts/distance/{distance}")
    @ApiOperation(value = "입력한 km 내의 게시물 조회")
    public List<PostResponseDto> findPostsByDistance(
        @ApiParam(value = "조회할 km 거리", required = true, example = "5") @PathVariable Integer distance,
        @ApiParam(value = "유저 위도", required = true, example = "37.504135") @RequestParam("latitude") Double latitude,
        @ApiParam(value = "유저 경도", required = true, example = "127.044819") @RequestParam("longitude") Double longitude,
        @ApiParam(value = "조회 시작할 행 인덱스", example = "0") @RequestParam(value = "offset", defaultValue = "0") Integer offset,
        @ApiParam(value = "조회할 행의 수", example = "100") @RequestParam(value = "limit", defaultValue = "100") Integer limit) {

        PostDistanceRequestDto postDistanceRequestDto = new PostDistanceRequestDto(distance, latitude, longitude,
            offset, limit);

        return postService.findPostsByDistance(postDistanceRequestDto);
    }

    @GetMapping("/boards/posts/id")
    @ApiOperation(value = "게시글 ID 리스트로 게시글 조회. 내 글 모아보기 기능으로 사용")
    public List<PostResponseDto> findPostByIdList(
        @RequestParam(value = "postIdList", required = false) List<Long> postIdList) {
        return postService.findPostsByIdList(postIdList);
    }

    @DeleteMapping("/boards/posts/{postId}")
    @ApiOperation(value = "게시글 삭제")
    public Long deletePost(@PathVariable Long postId, @RequestParam String password) {
        return postService.deletePost(postId, password);
    }

}
