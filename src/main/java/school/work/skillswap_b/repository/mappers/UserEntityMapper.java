package school.work.skillswap_b.repository.mappers;

import org.springframework.stereotype.Component;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.entity.UserEntity;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        entity.setBio(domain.getBio());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User domain = new User(entity.getId());
        domain.setFirstName(entity.getFirstName());
        domain.setLastName(entity.getLastName());
        domain.setEmail(entity.getEmail());
        domain.setBio(entity.getBio());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }
}