package school.work.skillswap_b.repository.mappers;

import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.entity.SkillOfferEntity;
import school.work.skillswap_b.entity.UserEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillOfferEntityMapperTest {

    private final UserEntityMapper userEntityMapper = new UserEntityMapper();
    private final SkillOfferEntityMapper mapper = new SkillOfferEntityMapper(userEntityMapper);

    @Test
    void toEntity_shouldMapAllFieldsCorrectly() {
        // Arrange
        User owner = new  User(1L);
        owner.setFirstName("Alice");

        SkillOffer domain = new SkillOffer();
        domain.setTitle("Java");
        domain.setDescription("Learn Java");
        domain.setCategory("Programming");
        domain.setOwner(owner);
        domain.setCreationDate(LocalDateTime.now());
        domain.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        SkillOfferEntity result = mapper.toEntity(domain);

        // Assert
        assertNotNull(result);
        assertEquals(domain.getTitle(), result.getTitle());
        assertEquals(domain.getDescription(), result.getDescription());
        assertEquals(domain.getCategory(), result.getCategory());
        assertEquals(domain.getOwner().getFirstName(), result.getOwner().getFirstName());
        assertEquals(domain.getCreationDate(), result.getCreationDate());
        assertEquals(domain.getExpirationDate(), result.getExpirationDate());
    }

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        // Arrange
        UserEntity ownerEntity = new UserEntity();
        ownerEntity.setFirstName("Alice");

        SkillOfferEntity entity = new SkillOfferEntity();
        entity.setTitle("Java");
        entity.setDescription("Learn Java");
        entity.setCategory("Programming");
        entity.setOwner(ownerEntity);
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
        assertEquals(entity.getOwner().getFirstName(), result.getOwner().getFirstName());
        assertEquals(entity.getCreationDate(), result.getCreationDate());
        assertEquals(entity.getExpirationDate(), result.getExpirationDate());
    }
}