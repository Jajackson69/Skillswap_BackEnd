package school.work.skillswap_b.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.work.skillswap_b.controller.mappers.UserDtoMapper;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.UserRequest;
import school.work.skillswap_b.dto.UserResponse;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserPersistenceRepository repository;
    private UserDtoMapper mapper;
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(UserPersistenceRepository.class);
        mapper = mock(UserDtoMapper.class);
        service = new UserServiceImpl(repository, mapper);
    }

    @Test
    void getAll_shouldReturnListOfResponses() {
        // Arrange
        User domain = new User(1L);
        UserResponse response = new UserResponse();

        when(repository.findAll()).thenReturn(List.of(domain));
        when(mapper.toResponse(domain)).thenReturn(response);

        // Act
        List<UserResponse> result = service.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(response, result.get(0));

        verify(repository).findAll();
        verify(mapper).toResponse(domain);
    }

    @Test
    void getById_shouldReturnResponse_whenUserExists() {
        // Arrange
        Long id = 1L;
        User domain = new User(id);
        UserResponse expected = new UserResponse();

        when(repository.findById(id)).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(expected);

        // Act
        UserResponse result = service.getById(id);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(repository).findById(id);
        verify(mapper).toResponse(domain);
    }

    @Test
    void create_shouldSaveAndReturnResponse() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setEmail("alice@gmail.com");

        User domain = new User();
        User saved = new User(1L);
        UserResponse expected = new UserResponse();

        when(repository.existsByEmail("alice@gmail.com")).thenReturn(false);
        when(mapper.toDomain(request)).thenReturn(domain);
        when(repository.save(domain)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(expected);

        // Act
        UserResponse result = service.create(request);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(repository).existsByEmail("alice@gmail.com");
        verify(mapper).toDomain(request);
        verify(repository).save(domain);
        verify(mapper).toResponse(saved);
    }

    @Test
    void create_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setEmail("alice@gmail.com");

        when(repository.existsByEmail("alice@gmail.com")).thenReturn(true);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.create(request));

        verify(repository).existsByEmail("alice@gmail.com");
        verify(repository, never()).save(any());
    }

    @Test
    void update_shouldUpdateAndReturnResponse_whenUserExists() {
        // Arrange
        Long id = 1L;
        UserRequest request = new UserRequest();

        User domain = new User();
        User updated = new User(id);
        UserResponse expected = new UserResponse();

        when(repository.existsById(id)).thenReturn(true);
        when(mapper.toDomain(request)).thenReturn(domain);
        when(repository.update(id, domain)).thenReturn(updated);
        when(mapper.toResponse(updated)).thenReturn(expected);

        // Act
        UserResponse result = service.update(id, request);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(repository).existsById(id);
        verify(mapper).toDomain(request);
        verify(repository).update(id, domain);
        verify(mapper).toResponse(updated);
    }

    @Test
    void update_shouldThrowException_whenUserNotFound() {
        // Arrange
        Long id = 99L;
        when(repository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.update(id, new UserRequest()));

        verify(repository).existsById(id);
        verify(repository, never()).update(any(), any());
    }

    @Test
    void delete_shouldCallRepository_whenUserExists() {
        // Arrange
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        // Act
        service.delete(id);

        // Assert
        verify(repository).existsById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void delete_shouldThrowException_whenUserNotFound() {
        // Arrange
        Long id = 99L;
        when(repository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.delete(id));

        verify(repository).existsById(id);
        verify(repository, never()).deleteById(any());
    }
}