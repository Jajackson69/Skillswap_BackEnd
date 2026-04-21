package school.work.skillswap_b.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;

import java.util.List;
//fixed this
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserPersistenceRepository repository;
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(UserPersistenceRepository.class);
        service = new UserServiceImpl(repository); // no mapper
    }

    @Test
    void getAll_shouldReturnListOfDomains() {
        User domain = new User(1L);
        when(repository.findAll()).thenReturn(List.of(domain));

        List<User> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(domain, result.get(0));

        verify(repository).findAll();
    }

    @Test
    void getById_shouldReturnDomain_whenUserExists() {
        Long id = 1L;
        User domain = new User(id);
        when(repository.findById(id)).thenReturn(domain);

        User result = service.getById(id);

        assertNotNull(result);
        assertSame(domain, result);
        verify(repository).findById(id);
    }

    @Test
    void create_shouldSetCreatedAtAndSave() {
        User domain = new User();
        domain.setEmail("alice@gmail.com");

        User saved = new User(1L);

        when(repository.existsByEmail("alice@gmail.com")).thenReturn(false);
        when(repository.save(domain)).thenReturn(saved);

        User result = service.create(domain);

        assertNotNull(result);
        assertSame(saved, result);
        assertNotNull(domain.getCreatedAt());

        verify(repository).existsByEmail("alice@gmail.com");
        verify(repository).save(domain);
    }

    @Test
    void create_shouldThrowException_whenEmailAlreadyExists() {
        User domain = new User();
        domain.setEmail("alice@gmail.com");

        when(repository.existsByEmail("alice@gmail.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> service.create(domain));

        verify(repository).existsByEmail("alice@gmail.com");
        verify(repository, never()).save(any());
    }

    @Test
    void update_shouldUpdateAndReturnDomain_whenUserExists() {
        Long id = 1L;
        User domain = new User();
        User updated = new User(id);

        when(repository.existsById(id)).thenReturn(true);
        when(repository.update(id, domain)).thenReturn(updated);

        User result = service.update(id, domain);

        assertNotNull(result);
        assertSame(updated, result);
        verify(repository).existsById(id);
        verify(repository).update(id, domain);
    }

    @Test
    void update_shouldThrowException_whenUserNotFound() {
        Long id = 99L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.update(id, new User()));

        verify(repository).existsById(id);
        verify(repository, never()).update(any(), any());
    }

    @Test
    void delete_shouldCallRepository_whenUserExists() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        service.delete(id);

        verify(repository).existsById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void delete_shouldThrowException_whenUserNotFound() {
        Long id = 99L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.delete(id));

        verify(repository).existsById(id);
        verify(repository, never()).deleteById(any());
    }
}