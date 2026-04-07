package school.work.skillswap_b.controller.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;

@Component
public class SkillOfferDtoMapper {

    public SkillOffer toDomain(CreateSkillOfferRequest request) {
        if (request == null) return null;

        SkillOffer domain = new SkillOffer();
        domain.setTitle(request.getTitle());
        domain.setDescription(request.getDescription());
        domain.setCategory(request.getCategory());
        domain.setOwnerName(request.getOwnerName());
        domain.setExpirationDate(request.getExpirationDate());
        return domain;
    }

    public SkillOfferResponse toResponse(SkillOffer domain) {
        if (domain == null) return null;

        SkillOfferResponse response = new SkillOfferResponse();
        response.setId(domain.getId());
        response.setTitle(domain.getTitle());
        response.setDescription(domain.getDescription());
        response.setCategory(domain.getCategory());
        response.setOwnerName(domain.getOwnerName());
        response.setCreationDate(domain.getCreationDate());
        response.setExpirationDate(domain.getExpirationDate());
        return response;

    }
}
