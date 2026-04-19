package school.work.skillswap_b.controller.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.UserRequest;
import school.work.skillswap_b.dto.UserResponse;

import java.time.LocalDateTime;

@Component
public class UserDtoMapper {

    public User toDomain(UserRequest request) {
        if (request == null) return null;

        User domain = new User();
        domain.setFirstName(request.getFirstName());
        domain.setLastName(request.getLastName());
        domain.setEmail(request.getEmail());
        domain.setBio(request.getBio());
        return domain;
    }

    public UserResponse toResponse(User domain) {
        if (domain == null) return null;

        UserResponse response = new UserResponse();
        response.setId(domain.getId());
        response.setFirstName(domain.getFirstName());
        response.setLastName(domain.getLastName());
        response.setEmail(domain.getEmail());
        response.setBio(domain.getBio());
        response.setCreatedAt(domain.getCreatedAt());
        return response;
    }
}