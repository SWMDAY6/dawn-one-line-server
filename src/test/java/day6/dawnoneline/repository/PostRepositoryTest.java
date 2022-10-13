package day6.dawnoneline.repository;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
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

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class PostRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    PostRepository postRepository;

    @BeforeAll
    public void setUp() {
        System.out.println("======= Before =========");
    }

    @Test
    @Transactional
    @DisplayName("게시글 등록 테스트")
    @Rollback(value = false)
    public void PostingTest() {

        // given
        String content = "테스트 게시글입니다.";
        Double longitude = 127.11;
        Double latitude = 38.21;
        String password = "1234";

        Post post = createPost(content, password, latitude, longitude);
        em.persist(post);

        Comment comment1 = createComment("좋아요", "1234");
        em.persist(comment1);

        Comment comment2 = createComment("테스트 댓글입니다.", "1234");
        em.persist(comment2);

        post.addComments(comment1, comment2);
        em.persist(post);

        // // when
        // Integer offset = 0;
        // Integer limit = 100;
        // List<Post> postList = postRepository.findAll(offset, limit);
        // System.out.println("======= POST : " + postList + " ============");
        //
        // // then
        // System.out.println("======== size : " + postList.size() + " =========");
        // Post Testpost = postList.get(0);
        // Assertions.assertEquals(Testpost.getContent(), content);
        // Assertions.assertEquals(Testpost.getPassword(), password);
    }

    private Post createPost(String content, String password, Double latitude, Double longitude) {
        return new Post(content, password, latitude, longitude);
    }

    private Comment createComment(String content, String password) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPassword(password);
        return comment;
    }

    @AfterAll
    public void after() {
        System.out.println("====== After ======");
    }

}