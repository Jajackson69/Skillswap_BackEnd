package school.work.skillswap_b.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.repository.interfaces.SkillOfferPersistenceRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillOfferServiceImplTest {

    private SkillOfferPersistenceRepository repository;
    private SkillOfferServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(SkillOfferPersistenceRepository.class);
        service = new SkillOfferServiceImpl(repository);
    }

    @Test
    void getAll_shouldReturnListOfDomains() {
        SkillOffer domain = new SkillOffer();
        when(repository.findAll()).thenReturn(List.of(domain));

        List<SkillOffer> result = service.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(domain, result.get(0));

        verify(repository).findAll();
    }

    @Test
    void getById_shouldReturnDomain_whenOfferExists() {
        Long id = 1L;
        SkillOffer domain = new SkillOffer();
        when(repository.findById(id)).thenReturn(domain);

        SkillOffer result = service.getById(id);

        assertNotNull(result);
        assertSame(domain, result);
        verify(repository).findById(id);
    }

    @Test
    void getById_shouldThrowException_whenOfferNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> service.getById(id));
        verify(repository).findById(id);
    }

    @Test
    void create_shouldSetCreationDateAndSave() {
        SkillOffer domain = new SkillOffer();
        SkillOffer saved = new SkillOffer();
        when(repository.save(domain)).thenReturn(saved);

        SkillOffer result = service.create(domain);

        assertNotNull(result);
        assertSame(saved, result);
        assertNotNull(domain.getCreationDate()); // ✅ service still sets this
        verify(repository).save(domain);
    }

    @Test
    void update_shouldUpdateAndReturnDomain_whenOfferExists() {
        Long id = 1L;
        SkillOffer domain = new SkillOffer();
        SkillOffer updated = new SkillOffer();

        when(repository.existsById(id)).thenReturn(true);
        when(repository.update(id, domain)).thenReturn(updated);

        SkillOffer result = service.update(id, domain);

        assertNotNull(result);
        assertSame(updated, result);
        verify(repository).existsById(id);
        verify(repository).update(id, domain);
    }

    @Test
    void update_shouldThrowException_whenOfferNotFound() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> service.update(id, new SkillOffer()));
        verify(repository).existsById(id);
        verify(repository, never()).update(any(), any());
    }

    @Test
    void delete_shouldCallRepository_whenOfferExists() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);

        service.delete(id);

        verify(repository).existsById(id);
        verify(repository).deleteById(id);
    }

    @Test
    void delete_shouldThrowException_whenOfferNotFound() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.delete(id));
        verify(repository).existsById(id);
        verify(repository, never()).deleteById(any());
    }
}