package day6.dawnoneline.repository;

import java.util.List;

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

    public List<Post> findAll(Integer offset, Integer limit) {
        return em.createQuery("SELECT p FROM Post p",
                Post.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }
}
