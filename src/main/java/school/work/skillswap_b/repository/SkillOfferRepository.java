package school.work.skillswap_b.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import school.work.skillswap_b.entity.SkillOfferEntity;

import java.util.List;
import java.util.Optional;

@Repository
public class SkillOfferRepository {

    @PersistenceContext
    private EntityManager em;

    public List<SkillOfferEntity> findAll() {
        return em
                .createQuery("SELECT s FROM SkillOfferEntity s", SkillOfferEntity.class)
                .getResultList();
    }

    public Optional<SkillOfferEntity> findById(long id) {
        SkillOfferEntity entity = em.find(SkillOfferEntity.class, id);
        return Optional.ofNullable(entity);
    }

    @Transactional
    public SkillOfferEntity save(SkillOfferEntity entity) {
        em.persist(entity);
        return entity;
    }

    @Transactional
    public SkillOfferEntity update(SkillOfferEntity entity) {
        return em.merge(entity);
    }


    @Transactional
    public void deleteById(long id) {
        SkillOfferEntity entity = em.find(SkillOfferEntity.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Transactional
    public boolean existsById(long id) {
        return em.find(SkillOfferEntity.class, id) != null;
    }
}
