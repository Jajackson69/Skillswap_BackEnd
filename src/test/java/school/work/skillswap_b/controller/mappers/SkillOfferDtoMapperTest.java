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
    void toDomain_mapsRequestToDomain() {
        CreateSkillOfferRequest request = new CreateSkillOfferRequest();
        request.setTitle("Java");
        request.setDescription("Learn Java");
        request.setCategory("Programming");
        request.setOwnerName("Alice");
        request.setExpirationDate(LocalDateTime.now().plusDays(1));

        SkillOffer domain = mapper.toDomain(request);

        assertEquals(request.getTitle(), domain.getTitle());
        assertEquals(request.getDescription(), domain.getDescription());
        assertEquals(request.getCategory(), domain.getCategory());
        assertEquals(request.getOwnerName(), domain.getOwnerName());
        assertEquals(request.getExpirationDate(), domain.getExpirationDate());
    }

    @Test
    void toResponse_mapsDomainToResponse() {
        SkillOffer domain = new SkillOffer();
        domain.setId(1L);
        domain.setTitle("Java");
        domain.setDescription("Learn Java");
        domain.setCategory("Programming");
        domain.setOwnerName("Alice");
        domain.setCreationDate(LocalDateTime.now());
        domain.setExpirationDate(LocalDateTime.now().plusDays(1));

        SkillOfferResponse response = mapper.toResponse(domain);

        assertEquals(domain.getId(), response.getId());
        assertEquals(domain.getTitle(), response.getTitle());
        assertEquals(domain.getDescription(), response.getDescription());
        assertEquals(domain.getCategory(), response.getCategory());
        assertEquals(domain.getOwnerName(), response.getOwnerName());
        assertEquals(domain.getCreationDate(), response.getCreationDate());
        assertEquals(domain.getExpirationDate(), response.getExpirationDate());
    }
}