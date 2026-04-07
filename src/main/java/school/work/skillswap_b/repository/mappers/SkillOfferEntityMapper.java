package school.work.skillswap_b.repository.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;

@Component
public class SkillOfferEntityMapper {

    public SkillOfferEntity toEntity(SkillOffer domain) {
        if (domain == null) {
            return null;
        }

        SkillOfferEntity entity = new SkillOfferEntity();
        //Id cannot be modified
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setOwnerName(domain.getOwnerName());
        entity.setCreationDate(domain.getCreationDate());
        entity.setExpirationDate(domain.getExpirationDate());
        return entity;

    }

    public SkillOffer toDomain(SkillOfferEntity entity) {
        if (entity == null) {
            return null;
        }

        SkillOffer domain = new SkillOffer();
        domain.setId(entity.getId()); //read id
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        domain.setCategory(entity.getCategory());
        domain.setOwnerName(entity.getOwnerName());
        domain.setCreationDate(entity.getCreationDate());
        domain.setExpirationDate(entity.getExpirationDate());
        return domain;
    }
    
}
