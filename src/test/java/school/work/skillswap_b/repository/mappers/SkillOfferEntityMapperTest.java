package school.work.skillswap_b.repository.mappers;

import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillOfferEntityMapperTest {

    private final SkillOfferEntityMapper mapper = new SkillOfferEntityMapper();

    @Test
    void toEntity_mapsDomainToEntity() {
        SkillOffer domain = new SkillOffer();
        domain.setTitle("Java");
        domain.setDescription("Learn Java");
        domain.setCategory("Programming");
        domain.setOwnerName("Alice");
        domain.setCreationDate(LocalDateTime.now());
        domain.setExpirationDate(LocalDateTime.now().plusDays(1));

        SkillOfferEntity entity = mapper.toEntity(domain);

        assertEquals(domain.getTitle(), entity.getTitle());
        assertEquals(domain.getDescription(), entity.getDescription());
        assertEquals(domain.getCategory(), entity.getCategory());
        assertEquals(domain.getOwnerName(), entity.getOwnerName());
        assertEquals(domain.getCreationDate(), entity.getCreationDate());
        assertEquals(domain.getExpirationDate(), entity.getExpirationDate());
    }

    @Test
    void toDomain_mapsEntityToDomain() {
        SkillOfferEntity entity = new SkillOfferEntity();
        entity.setId(1L);
        entity.setTitle("Java");
        entity.setDescription("Learn Java");
        entity.setCategory("Programming");
        entity.setOwnerName("Alice");
        entity.setCreationDate(LocalDateTime.now());
        entity.setExpirationDate(LocalDateTime.now().plusDays(1));

        SkillOffer domain = mapper.toDomain(entity);

        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getTitle(), domain.getTitle());
        assertEquals(entity.getDescription(), domain.getDescription());
        assertEquals(entity.getCategory(), domain.getCategory());
        assertEquals(entity.getOwnerName(), domain.getOwnerName());
        assertEquals(entity.getCreationDate(), domain.getCreationDate());
        assertEquals(entity.getExpirationDate(), domain.getExpirationDate());
    }
}