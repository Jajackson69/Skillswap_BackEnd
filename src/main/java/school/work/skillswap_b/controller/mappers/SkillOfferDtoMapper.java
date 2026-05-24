package school.work.skillswap_b.controller.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;

@Component
public class SkillOfferDtoMapper {

    private final UserDtoMapper userDtoMapper;

    public SkillOfferDtoMapper(UserDtoMapper userDtoMapper) {
        this.userDtoMapper = userDtoMapper;
    }

    public SkillOffer toDomain(CreateSkillOfferRequest request, User  owner) {
        if (request == null) return null;

        SkillOffer domain = new SkillOffer();
        domain.setTitle(request.getTitle());
        domain.setDescription(request.getDescription());
        domain.setCategory(request.getCategory());
        domain.setOwner(owner);
        domain.setExpirationDate(request.getExpirationDate());
        domain.setFormat(request.getFormat());
        domain.setAvailability(request.getAvailability());
        return domain;
    }

    public SkillOfferResponse toResponse(SkillOffer domain) {
        if (domain == null) return null;

        SkillOfferResponse response = new SkillOfferResponse();
        response.setId(domain.getId());
        response.setTitle(domain.getTitle());
        response.setDescription(domain.getDescription());
        response.setCategory(domain.getCategory());
        response.setOwner(userDtoMapper.toResponse(domain.getOwner()));
        response.setFormat(domain.getFormat());
        response.setAvailability(domain.getAvailability());
        response.setCreationDate(domain.getCreationDate());
        response.setExpirationDate(domain.getExpirationDate());
        return response;

    }
}
