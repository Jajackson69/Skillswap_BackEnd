package school.work.skillswap_b.controller.mappers;

import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.UserRequest;
import school.work.skillswap_b.dto.UserResponse;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoMapperTest {

    private final UserDtoMapper mapper = new UserDtoMapper();

    @Test
    void toDomain_shouldMapAllFieldsCorrectly() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setFirstName("Alice");
        request.setLastName("Dupont");
        request.setEmail("alice@gmail.com");
        request.setBio("I love coding");

        // Act
        User result = mapper.toDomain(request);

        // Assert
        assertNotNull(result);
        assertEquals(request.getFirstName(), result.getFirstName());
        assertEquals(request.getLastName(), result.getLastName());
        assertEquals(request.getEmail(), result.getEmail());
        assertEquals(request.getBio(), result.getBio());
    }

    @Test
    void toResponse_shouldMapAllFieldsCorrectly() {
        // Arrange
        User domain = new User(1L);
        domain.setFirstName("Alice");
        domain.setLastName("Dupont");
        domain.setEmail("alice@gmail.com");
        domain.setBio("I love coding");
        domain.setCreatedAt(LocalDateTime.now());

        // Act
        UserResponse result = mapper.toResponse(domain);

        // Assert
        assertNotNull(result);
        assertEquals(domain.getId(), result.getId());
        assertEquals(domain.getFirstName(), result.getFirstName());
        assertEquals(domain.getLastName(), result.getLastName());
        assertEquals(domain.getEmail(), result.getEmail());
        assertEquals(domain.getBio(), result.getBio());
        assertEquals(domain.getCreatedAt(), result.getCreatedAt());
    }

    @Test
    void toDomain_shouldReturnNull_whenRequestIsNull() {
        // Arrange & Act
        User result = mapper.toDomain(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toResponse_shouldReturnNull_whenDomainIsNull() {
        // Arrange & Act
        UserResponse result = mapper.toResponse(null);

        // Assert
        assertNull(result);
    }
}