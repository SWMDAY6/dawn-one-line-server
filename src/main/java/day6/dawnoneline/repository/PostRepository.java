package day6.dawnoneline.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import day6.dawnoneline.domain.Post;

@Repository
public class PostRepository {
    @PersistenceContext
    private EntityManager em;

    public Long createPost(Post post) {
        em.persist(post);
        return post.getId();
    }

}
