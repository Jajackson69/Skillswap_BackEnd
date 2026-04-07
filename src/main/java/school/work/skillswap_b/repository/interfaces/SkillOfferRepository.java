package school.work.skillswap_b.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import school.work.skillswap_b.entity.SkillOfferEntity;

public interface SkillOfferRepository extends JpaRepository<SkillOfferEntity, Long> {
}