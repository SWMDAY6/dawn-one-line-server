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

import day6.dawnoneline.dto.request.PostSaveRequestDto;
import day6.dawnoneline.dto.response.PostResponseDto;
import day6.dawnoneline.service.PostService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PostController {

    private final PostService postService;

    @PostMapping("/boards/posts")
    public Long createPost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        return postService.createPost(postSaveRequestDto);
    }

    @GetMapping("/boards/posts")
    public List<PostResponseDto> findAll(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
        @RequestParam(value = "limit", defaultValue = "100") Integer limit) {
        return postService.findAll(offset, limit);
    }

    @GetMapping("/boards/posts/id")
    public List<PostResponseDto> findPostByIdList(
        @RequestParam(value = "postIdList", required = false) List<Long> postIdList) {
        return postService.findPostsByIdList(postIdList);
    }

    @DeleteMapping("/boards/posts/{postId}")
    public Long deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return postId;
    }
}
