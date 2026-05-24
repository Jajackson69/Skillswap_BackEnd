package school.work.skillswap_b.repository.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;

@Component
public class SkillOfferEntityMapper {

    private final UserEntityMapper userEntityMapper;

    public SkillOfferEntityMapper(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }
    public SkillOfferEntity toEntity(SkillOffer domain) {
        if (domain == null) {
            return null;
        }

        SkillOfferEntity entity = new SkillOfferEntity();
        //Id cannot be modified
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setOwner(userEntityMapper.toEntity(domain.getOwner()));
        entity.setCreationDate(domain.getCreationDate());
        entity.setExpirationDate(domain.getExpirationDate());
        entity.setFormat(domain.getFormat());
        entity.setAvailability(domain.getAvailability() != null ? domain.getAvailability() : new java.util.HashSet<>());
        return entity;

    }

    public SkillOffer toDomain(SkillOfferEntity entity) {
        if (entity == null) {
            return null;
        }

        SkillOffer domain = new SkillOffer(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setDescription(entity.getDescription());
        domain.setCategory(entity.getCategory());
        domain.setOwner(userEntityMapper.toDomain(entity.getOwner()));
        domain.setCreationDate(entity.getCreationDate());
        domain.setExpirationDate(entity.getExpirationDate());
        domain.setFormat(entity.getFormat());
        domain.setAvailability(entity.getAvailability() != null ? entity.getAvailability() : new java.util.HashSet<>());
        return domain;
    }
    
}
