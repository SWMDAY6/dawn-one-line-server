package day6.dawnoneline.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return em.createQuery("SELECT p FROM Post p "
                    + "WHERE p.deletedAt is null",
                Post.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }

    public Optional<Post> findPostById(Long postId) {
        return Optional.ofNullable(em.createQuery("SELECT p FROM Post p "
                + "WHERE p.id = :postId "
                + "AND p.deletedAt is null", Post.class)
            .setParameter("postId", postId)
            .getSingleResult());
    }

    public void deletePost(Post post) {
        post.setDeletedAt(LocalDateTime.now());
    }
}
