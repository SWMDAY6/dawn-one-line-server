package day6.dawnoneline.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import day6.dawnoneline.domain.Post;
import day6.dawnoneline.dto.request.CommentRequestDto;
import day6.dawnoneline.dto.request.PostSaveRequestDto;
import day6.dawnoneline.service.CommentService;
import day6.dawnoneline.service.PostService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@Slf4j
class PostRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @BeforeAll
    public void setUp() {
        System.out.println("======= Before =========");
    }

    @Test
    @Transactional
    @DisplayName("게시글 등록 및 조회 테스트")
    @Rollback(value = false)
    public void CreatePostTest() {

        // given
        String content = "테스트 게시글입니다.";
        Double longitude = 127.11;
        Double latitude = 38.21;
        String password = "1234";

        PostSaveRequestDto postSaveRequestDto = new PostSaveRequestDto(content, password, latitude, longitude);
        Long postId = postService.createPost(postSaveRequestDto);

        CommentRequestDto commentRequestDto1 = new CommentRequestDto();
        commentRequestDto1.setPostId(1L);
        commentRequestDto1.setContent("좋아요");
        commentRequestDto1.setPassword("1234");
        commentService.createComment(commentRequestDto1);

        CommentRequestDto commentRequestDto2 = new CommentRequestDto();
        commentRequestDto2.setPostId(1L);
        commentRequestDto2.setContent("테스트 댓글입니다.");
        commentRequestDto2.setPassword("1234");
        commentService.createComment(commentRequestDto2);

        // when
        Integer offset = 0;
        Integer limit = 100;
        List<Post> postList = postRepository.findAll(offset, limit);

        // then
        Post Testpost = postList.get(postList.size() - 1);
        Assertions.assertEquals(Testpost.getContent(), content);
        Assertions.assertEquals(Testpost.getPassword(), password);
    }

    @Test
    @Rollback(value = true)
    public void deletePostTest() {
        postService.deletePost(1L);
    }

    @AfterAll
    public void after() {
        System.out.println("====== After ======");
    }

}