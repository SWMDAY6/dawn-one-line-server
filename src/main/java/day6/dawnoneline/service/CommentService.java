package day6.dawnoneline.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.Post;
import day6.dawnoneline.dto.request.CommentRequestDto;
import day6.dawnoneline.dto.response.CommentResponseDto;
import day6.dawnoneline.repository.CommentRepository;
import day6.dawnoneline.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public Long createComment(CommentRequestDto commentRequestDto) {
        Optional<Post> post = postRepository.findPostById(commentRequestDto.getPostId());

        if (post.isPresent()) {
            Comment comment = commentRequestDto.toEntity();
            commentRepository.createComment(comment);
            post.get().addComment(comment);
        }
        return post.orElseThrow().getId();
    }

    public List<CommentResponseDto> findAllComments(Long postId) {
        List<Comment> findAllComments = commentRepository.findAll(postId);
        return commentsEntityToDto(findAllComments);
    }

    @Transactional
    public Long deleteComment(Long commentId) {
        Comment comment = commentRepository.findCommentById(commentId);
        commentRepository.deleteComment(comment);
        return comment.getId();
    }

    public List<CommentResponseDto> commentsEntityToDto(List<Comment> commentsEntity) {
        return commentsEntity.stream()
            .map(c -> new CommentResponseDto(c))
            .collect(Collectors.toList());
    }
}
