package school.work.skillswap_b.repository.interfaces;

import school.work.skillswap_b.domain.SkillOffer;

import java.util.List;

public interface SkillOfferPersistenceRepository {

    List<SkillOffer> findAll();

    SkillOffer findById(Long id);

    SkillOffer save(SkillOffer skillOffer);

    SkillOffer update(Long id, SkillOffer skillOffer);

    void deleteById(Long id);

    boolean existsById(Long id);
}