package day6.dawnoneline.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.Post;
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

    @Transactional
    public Long createPost(PostSaveRequestDto postSaveRequestDto) {
        return postRepository.createPost(postSaveRequestDto.toEntity());
    }

    public List<PostResponseDto> findAll(Integer offset, Integer limit) {
        List<Post> findAllPostsEntity = postRepository.findAll(offset, limit);
        return postsEntityToDto(findAllPostsEntity);
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
    public void deletePost(Long postId) {
        Optional<Post> post = postRepository.findPostById(postId);
        if (post.isPresent()) {
            postRepository.deletePost(post.get());
            List<Comment> comments = post.get().getComments();
            for (Comment comment : comments) {
                commentRepository.deleteComment(comment);
            }
        }
    }

    public List<PostResponseDto> postsEntityToDto(List<Post> postsEntity) {
        List<PostResponseDto> postResponseDtos = postsEntity.stream()
            .map(p -> new PostResponseDto(p))
            .collect(Collectors.toList());

        return postResponseDtos;
    }

}