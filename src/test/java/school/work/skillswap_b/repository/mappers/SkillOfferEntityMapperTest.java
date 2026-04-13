package school.work.skillswap_b.repository.mappers;

import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillOfferEntityMapperTest {

    private final SkillOfferEntityMapper mapper = new SkillOfferEntityMapper();

    @Test
    void toEntity_shouldMapAllFieldsCorrectly() {
        // Arrange
        SkillOffer domain = new SkillOffer();
        domain.setTitle("Java");
        domain.setDescription("Learn Java");
        domain.setCategory("Programming");
        domain.setOwnerName("Alice");
        domain.setCreationDate(LocalDateTime.now());
        domain.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        SkillOfferEntity result = mapper.toEntity(domain);

        // Assert
        assertNotNull(result);
        assertEquals(domain.getTitle(), result.getTitle());
        assertEquals(domain.getDescription(), result.getDescription());
        assertEquals(domain.getCategory(), result.getCategory());
        assertEquals(domain.getOwnerName(), result.getOwnerName());
        assertEquals(domain.getCreationDate(), result.getCreationDate());
        assertEquals(domain.getExpirationDate(), result.getExpirationDate());
    }

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        // Arrange
        SkillOfferEntity entity = new SkillOfferEntity();
        entity.setId(1L);
        entity.setTitle("Java");
        entity.setDescription("Learn Java");
        entity.setCategory("Programming");
        entity.setOwnerName("Alice");
        entity.setCreationDate(LocalDateTime.now());
        entity.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        SkillOffer result = mapper.toDomain(entity);

        // Assert
        assertNotNull(result);
        assertEquals(entity.getId(), result.getId());
        assertEquals(entity.getTitle(), result.getTitle());
        assertEquals(entity.getDescription(), result.getDescription());
        assertEquals(entity.getCategory(), result.getCategory());
        assertEquals(entity.getOwnerName(), result.getOwnerName());
        assertEquals(entity.getCreationDate(), result.getCreationDate());
        assertEquals(entity.getExpirationDate(), result.getExpirationDate());
    }
}