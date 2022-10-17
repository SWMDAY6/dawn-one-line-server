package day6.dawnoneline.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import day6.dawnoneline.domain.Comment;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public void createComment(Comment comment) {
        em.persist(comment);
    }

    public Comment findCommentById(Long commentId) {
        return em.createQuery("SELECT c FROM Comment c "
                + "WHERE c.id = :commentId "
                + "AND c.deletedAt is null", Comment.class)
            .setParameter("commentId", commentId)
            .getSingleResult();
    }

    public List<Comment> findAll(Long postId) {
        return em.createQuery("SELECT c FROM Comment c "
                + "WHERE c.post.id = :postId "
                + "AND c.deletedAt is null", Comment.class)
            .setParameter("postId", postId)
            .getResultList();
    }

    public void deleteComment(Comment comment) {
        comment.setDeletedAt(LocalDateTime.now());
    }

}
