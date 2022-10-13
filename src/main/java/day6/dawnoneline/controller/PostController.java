package day6.dawnoneline.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import day6.dawnoneline.dto.request.PostSaveRequestDto;
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

}
