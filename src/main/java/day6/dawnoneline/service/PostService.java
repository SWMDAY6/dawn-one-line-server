package day6.dawnoneline.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.domain.Post;
import day6.dawnoneline.dto.request.PostSaveRequestDto;
import day6.dawnoneline.dto.response.PostResponseDto;
import day6.dawnoneline.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long createPost(PostSaveRequestDto postSaveRequestDto) {
        return postRepository.createPost(postSaveRequestDto.toEntity());
    }

    public List<PostResponseDto> findAll(Integer offset, Integer limit) {
        List<Post> findAllPostsEntity = postRepository.findAll(offset, limit);
        return postsEntityToDto(findAllPostsEntity);
    }

    public List<PostResponseDto> postsEntityToDto(List<Post> postsEntity) {
        List<PostResponseDto> postResponseDtos = postsEntity.stream()
            .map(p -> new PostResponseDto(p))
            .collect(Collectors.toList());

        return postResponseDtos;
    }
}
