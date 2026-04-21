package school.work.skillswap_b.service.interfaces;

import school.work.skillswap_b.domain.SkillOffer;
import java.util.List;

public interface SkillOfferService {

    List<SkillOffer> getAll();

    SkillOffer getById(long id);

    SkillOffer create(SkillOffer skillOffer);

    SkillOffer update(long id, SkillOffer skillOffer);

    void delete(long id);
}