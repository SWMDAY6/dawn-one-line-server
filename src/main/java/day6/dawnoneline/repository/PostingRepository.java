package day6.dawnoneline.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import day6.dawnoneline.domain.Posting;

@Repository
public class PostingRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Posting posting){
        em.persist(posting);
        return posting.getId();
    }

    public Posting find(Long id){
        return em.find(Posting.class, id);
    }
}
