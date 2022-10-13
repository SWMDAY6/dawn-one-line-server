package day6.dawnoneline.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.dto.request.PostSaveRequestDto;
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

}
