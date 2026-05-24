package school.work.skillswap_b.controller.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.ProfileResponse;
import school.work.skillswap_b.dto.UpdateProfileRequest;

@Component
public class ProfileDtoMapper {

    public User toDomain(UpdateProfileRequest request) {
        if (request == null) return null;

        User domain = new User();
        domain.setFirstName(request.getFirstName());
        domain.setLastName(request.getLastName());
        domain.setBio(request.getBio());
        return domain;
    }

    public ProfileResponse toResponse(User domain) {
        if (domain == null) return null;

        ProfileResponse response = new ProfileResponse();
        response.setId(domain.getId());
        response.setFirstName(domain.getFirstName());
        response.setLastName(domain.getLastName());
        response.setBio(domain.getBio());
        response.setMemberSince(domain.getCreatedAt());
        return response;
    }
}
