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

import day6.dawnoneline.domain.Comment;
import day6.dawnoneline.domain.Post;
import day6.dawnoneline.service.PostService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PostRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

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
        String content = "테스트 게시글4입니다.";
        Double longitude = 127.11;
        Double latitude = 38.21;
        String password = "1234";

        Post post = new Post(content, password, latitude, longitude);

        Comment comment1 = new Comment("좋아요", "1234");
        em.persist(comment1);

        Comment comment2 = new Comment("테스트 댓글입니다.", "1234");
        em.persist(comment2);

        post.addComments(comment1, comment2);
        Long postId = postRepository.createPost(post);

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
    @Rollback(value = false)
    public void deletePostTest() {
        postService.deletePost(2L);
    }

    @AfterAll
    public void after() {
        System.out.println("====== After ======");
    }

}