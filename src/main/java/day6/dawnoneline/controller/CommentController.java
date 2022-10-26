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

import day6.dawnoneline.dto.request.CommentRequestDto;
import day6.dawnoneline.dto.response.CommentResponseDto;
import day6.dawnoneline.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "댓글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/posts/comments")
    @ApiOperation(value = "댓글 추가")
    public Long createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return commentService.createComment(commentRequestDto);
    }

    @GetMapping("/posts/{postId}/comments")
    @ApiOperation(value = "해당 게시글 댓글 조회")
    public List<CommentResponseDto> findAllComments(@PathVariable Long postId) {
        return commentService.findAllComments(postId);
    }

    @DeleteMapping("/posts/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제")
    public Long deleteComment(@PathVariable Long commentId, @RequestParam String password) {
        return commentService.deleteComment(commentId, password);
    }
}
