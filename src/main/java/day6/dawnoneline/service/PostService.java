package day6.dawnoneline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.Post;
import day6.dawnoneline.dto.request.PostDistanceRequestDto;
import day6.dawnoneline.dto.request.PostSaveRequestDto;
import day6.dawnoneline.dto.response.PostResponseDto;
import day6.dawnoneline.repository.CommentRepository;
import day6.dawnoneline.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Long createPost(PostSaveRequestDto postSaveRequestDto) {
        String encodePassword = passwordEncoder.encode(postSaveRequestDto.getPassword());
        Post savePost = postSaveRequestDto.toEntity();
        savePost.setPassword(encodePassword);
        return postRepository.createPost(savePost);
    }

    public List<PostResponseDto> findAll(Integer offset, Integer limit) {
        List<Post> findAllPostsEntity = postRepository.findAll(offset, limit);
        return postsEntityToDto(findAllPostsEntity);
    }

    public List<PostResponseDto> findPostsByDistance(PostDistanceRequestDto postDistanceRequestDto) {
        List<Post> findPostsByDistanceEntity = postRepository.findPostsByDistance(postDistanceRequestDto);
        return postsEntityToDto(findPostsByDistanceEntity);
    }

    public List<PostResponseDto> findPostsByIdList(List<Long> postIdList) {
        List<Post> findPostsByIdList = new ArrayList<>();
        for (Long postId : postIdList) {
            Optional<Post> post = postRepository.findPostById(postId);
            if (post.isPresent()) {
                findPostsByIdList.add(post.get());
            }
        }
        return postsEntityToDto(findPostsByIdList);
    }

    @Transactional
    public Long deletePost(Long postId, String enterPassword) {
        Optional<Post> oPost = postRepository.findPostById(postId);
        log.debug("print enter password : {}", enterPassword);
        if (!oPost.isPresent()) {
            throw new NoResultException();
        }
        Post post = oPost.get();
        log.debug("print post password : {}", post.getPassword());
        if (!passwordEncoder.matches(enterPassword, post.getPassword())) {
            throw new BadCredentialsException("The password is wrong!");
        }
        Long deletePostId = postRepository.deletePost(post);
        List<Comment> comments = post.getComments();
        for (Comment comment : comments) {
            commentRepository.deleteComment(comment);
        }
        return deletePostId;
    }

    public List<PostResponseDto> postsEntityToDto(List<Post> postsEntity) {
        List<PostResponseDto> postResponseDtos = postsEntity.stream()
            .map(p -> new PostResponseDto(p))
            .collect(Collectors.toList());

        return postResponseDtos;
    }

}