package day6.dawnoneline.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import day6.dawnoneline.domain.Post;
import day6.dawnoneline.dto.request.PostDistanceRequestDto;

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

    public List<Post> findPostsByDistance(PostDistanceRequestDto postDistanceRequestDto) {
        String query = "SELECT p FROM Post p "
            + "WHERE p.deletedAt is null "
            + "AND ST_Distance_Sphere(point(p.longitude,p.latitude), point(:x,:y)) <= :distance "
            + "ORDER BY ST_Distance_Sphere(point(p.longitude,p.latitude), point(:x,:y))";

        return em.createQuery(query, Post.class)
            .setParameter("x", postDistanceRequestDto.getLongitude())
            .setParameter("y", postDistanceRequestDto.getLatitude())
            .setParameter("distance", postDistanceRequestDto.getDistance() * 1000)
            .setFirstResult(postDistanceRequestDto.getOffset())
            .setMaxResults(postDistanceRequestDto.getLimit())
            .getResultList();
    }

    public Optional<Post> findPostById(Long postId) {
        return Optional.ofNullable(em.createQuery("SELECT p FROM Post p "
                + "WHERE p.id = :postId "
                + "AND p.deletedAt is null", Post.class)
            .setParameter("postId", postId)
            .getSingleResult());
    }

    public Long deletePost(Post post) {
        post.setDeletedAt(LocalDateTime.now());
        return post.getId();
    }

}
