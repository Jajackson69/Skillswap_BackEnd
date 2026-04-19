package school.work.skillswap_b.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.entity.UserEntity;
import school.work.skillswap_b.repository.interfaces.UserRepository;
import school.work.skillswap_b.repository.mappers.UserEntityMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPersistenceRepositoryImplTest {

    private UserRepository jpaRepository;
    private UserEntityMapper mapper;
    private UserPersistenceRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        jpaRepository = mock(UserRepository.class);
        mapper = mock(UserEntityMapper.class);
        repository = new UserPersistenceRepositoryImpl(jpaRepository, mapper);
    }

    @Test
    void findAll_shouldReturnMappedDomainList() {
        // Arrange
        UserEntity entity = new UserEntity();
        User domain = new User();

        when(jpaRepository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        // Act
        List<User> result = repository.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(domain, result.get(0));

        verify(jpaRepository).findAll();
        verify(mapper).toDomain(entity);
    }

    @Test
    void findById_shouldReturnMappedDomain_whenEntityExists() {
        // Arrange
        Long id = 1L;
        UserEntity entity = new UserEntity();
        User expected = new User(id);

        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(expected);

        // Act
        User result = repository.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(jpaRepository).findById(id);
        verify(mapper).toDomain(entity);
    }

    @Test
    void findById_shouldThrowException_whenEntityNotFound() {
        // Arrange
        Long id = 99L;
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> repository.findById(id));

        verify(jpaRepository).findById(id);
    }

    @Test
    void save_shouldMapDomainToEntity_andReturnMappedDomain() {
        // Arrange
        User domain = new User();
        UserEntity entity = new UserEntity();
        UserEntity savedEntity = new UserEntity();
        User expected = new User(1L);

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(expected);

        // Act
        User result = repository.save(domain);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(mapper).toEntity(domain);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
    }

    @Test
    void update_shouldUpdateEntity_andReturnMappedDomain_whenEntityExists() {
        // Arrange
        Long id = 1L;

        User domain = new User();
        domain.setFirstName("New Name");
        domain.setLastName("New Last");
        domain.setEmail("new@gmail.com");
        domain.setBio("New bio");

        UserEntity entity = new UserEntity();
        entity.setFirstName("Old Name");

        UserEntity savedEntity = new UserEntity();
        savedEntity.setFirstName("New Name");

        User expected = new User(id);
        expected.setFirstName("New Name");

        when(jpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(expected);

        // Act
        User result = repository.update(id, domain);

        // Assert
        assertNotNull(result);
        assertEquals("New Name", entity.getFirstName());
        assertSame(expected, result);

        verify(jpaRepository).findById(id);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
    }

    @Test
    void update_shouldThrowException_whenEntityNotFound() {
        // Arrange
        Long id = 99L;
        when(jpaRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> repository.update(id, new User()));

        verify(jpaRepository).findById(id);
        verify(jpaRepository, never()).save(any());
    }

    @Test
    void deleteById_shouldCallJpaRepository() {
        // Arrange
        Long id = 1L;

        // Act
        repository.deleteById(id);

        // Assert
        verify(jpaRepository).deleteById(id);
    }

    @Test
    void existsById_shouldReturnTrue_whenEntityExists() {
        // Arrange
        Long id = 1L;
        when(jpaRepository.existsById(id)).thenReturn(true);

        // Act
        boolean result = repository.existsById(id);

        // Assert
        assertTrue(result);
        verify(jpaRepository).existsById(id);
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {
        // Arrange
        String email = "alice@gmail.com";
        when(jpaRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean result = repository.existsByEmail(email);

        // Assert
        assertTrue(result);
        verify(jpaRepository).existsByEmail(email);
    }
}