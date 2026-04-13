package school.work.skillswap_b.controller.mappers;

import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillOfferDtoMapperTest {

    private final SkillOfferDtoMapper mapper = new SkillOfferDtoMapper();

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        // Arrange
        CreateSkillOfferRequest request = new CreateSkillOfferRequest();
        request.setTitle("Java");
        request.setDescription("Learn Java");
        request.setCategory("Programming");
        request.setOwnerName("Alice");
        request.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        SkillOffer result = mapper.toDomain(request);

        // Assert
        assertNotNull(result);
        assertEquals(request.getTitle(), result.getTitle());
        assertEquals(request.getDescription(), result.getDescription());
        assertEquals(request.getCategory(), result.getCategory());
        assertEquals(request.getOwnerName(), result.getOwnerName());
        assertEquals(request.getExpirationDate(), result.getExpirationDate());
    }

    @Test
    void toResponse_shouldMapAllFieldsCorrectly() {
        // Arrange
        SkillOffer domain = new SkillOffer();
        domain.setId(1L);
        domain.setTitle("Java");
        domain.setDescription("Learn Java");
        domain.setCategory("Programming");
        domain.setOwnerName("Alice");
        domain.setCreationDate(LocalDateTime.now());
        domain.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        SkillOfferResponse result = mapper.toResponse(domain);

        // Assert
        assertNotNull(result);
        assertEquals(domain.getId(), result.getId());
        assertEquals(domain.getTitle(), result.getTitle());
        assertEquals(domain.getDescription(), result.getDescription());
        assertEquals(domain.getCategory(), result.getCategory());
        assertEquals(domain.getOwnerName(), result.getOwnerName());
        assertEquals(domain.getCreationDate(), result.getCreationDate());
        assertEquals(domain.getExpirationDate(), result.getExpirationDate());
    }
}