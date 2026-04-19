package school.work.skillswap_b.repository.mappers;

import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.entity.UserEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperTest {

    private final UserEntityMapper mapper = new UserEntityMapper();

    @Test
    void toEntity_shouldMapAllFieldsCorrectly() {
        // Arrange
        User domain = new User(1L);
        domain.setFirstName("Alice");
        domain.setLastName("Dupont");
        domain.setEmail("alice@gmail.com");
        domain.setBio("I love coding");
        domain.setCreatedAt(LocalDateTime.now());

        // Act
        UserEntity result = mapper.toEntity(domain);

        // Assert
        assertNotNull(result);
        assertEquals(domain.getFirstName(), result.getFirstName());
        assertEquals(domain.getLastName(), result.getLastName());
        assertEquals(domain.getEmail(), result.getEmail());
        assertEquals(domain.getBio(), result.getBio());
        assertEquals(domain.getCreatedAt(), result.getCreatedAt());
    }

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        // Arrange
        UserEntity entity = new UserEntity();
        entity.setFirstName("Alice");
        entity.setLastName("Dupont");
        entity.setEmail("alice@gmail.com");
        entity.setBio("I love coding");
        entity.setCreatedAt(LocalDateTime.now());

        // Act
        User result = mapper.toDomain(entity);

        // Assert
        assertNotNull(result);
        assertEquals(entity.getFirstName(), result.getFirstName());
        assertEquals(entity.getLastName(), result.getLastName());
        assertEquals(entity.getEmail(), result.getEmail());
        assertEquals(entity.getBio(), result.getBio());
        assertEquals(entity.getCreatedAt(), result.getCreatedAt());
    }

    @Test
    void toEntity_shouldReturnNull_whenDomainIsNull() {
        // Arrange & Act
        UserEntity result = mapper.toEntity(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toDomain_shouldReturnNull_whenEntityIsNull() {
        // Arrange & Act
        User result = mapper.toDomain(null);

        // Assert
        assertNull(result);
    }
}