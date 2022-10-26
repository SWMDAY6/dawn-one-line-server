package day6.dawnoneline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long createComment(CommentRequestDto commentRequestDto) {
        Optional<Post> post = postRepository.findPostById(commentRequestDto.getPostId());

        if (!post.isPresent()) {
            throw new NoSuchElementException();
        }
        String encodePassword = passwordEncoder.encode(commentRequestDto.getPassword());
        Comment comment = commentRequestDto.toEntity();
        comment.setPassword(encodePassword);
        commentRepository.createComment(comment);
        post.get().addComment(comment);

        return post.get().getId();
    }

    public List<CommentResponseDto> findAllComments(Long postId) {
        List<Comment> findAllComments = commentRepository.findAll(postId);
        return commentsEntityToDto(findAllComments);
    }

    @Transactional
    public Long deleteComment(Long commentId, String enterPassword) {
        Optional<Comment> oComment = commentRepository.findCommentById(commentId);

        if (!oComment.isPresent()) {
            throw new NoResultException();
        }
        Comment comment = oComment.get();
        if (!passwordEncoder.matches(enterPassword, comment.getPassword())) {
            throw new BadCredentialsException("The password is wrong!");
        }
        Long deletedCommentId = commentRepository.deleteComment(comment);
        return deletedCommentId;
    }

    public List<CommentResponseDto> commentsEntityToDto(List<Comment> commentsEntity) {
        return commentsEntity.stream()
            .map(c -> new CommentResponseDto(c))
            .collect(Collectors.toList());
    }
}
