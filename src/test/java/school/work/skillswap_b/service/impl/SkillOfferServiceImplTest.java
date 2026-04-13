package school.work.skillswap_b.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.work.skillswap_b.controller.mappers.SkillOfferDtoMapper;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;
import school.work.skillswap_b.repository.interfaces.SkillOfferPersistenceRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillOfferServiceImplTest {

    private SkillOfferPersistenceRepository repository;
    private SkillOfferDtoMapper mapper;
    private SkillOfferServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(SkillOfferPersistenceRepository.class);
        mapper = mock(SkillOfferDtoMapper.class);
        service = new SkillOfferServiceImpl(repository, mapper);
    }

    @Test
    void getAll_shouldReturnListOfResponses() {
        // Arrange
        SkillOffer domain = new SkillOffer();
        SkillOfferResponse response = new SkillOfferResponse();

        when(repository.findAll()).thenReturn(List.of(domain));
        when(mapper.toResponse(domain)).thenReturn(response);

        // Act
        List<SkillOfferResponse> result = service.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(response, result.get(0));

        verify(repository).findAll();
        verify(mapper).toResponse(domain);
    }

    @Test
    void getById_shouldReturnResponse_whenOfferExists() {
        // Arrange
        Long id = 1L;
        SkillOffer domain = new SkillOffer();
        SkillOfferResponse expected = new SkillOfferResponse();

        when(repository.findById(id)).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(expected);

        // Act
        SkillOfferResponse result = service.getById(id);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(repository).findById(id);
        verify(mapper).toResponse(domain);
    }

    @Test
    void getById_shouldThrowException_whenOfferNotFound() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(null);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> service.getById(id));

        verify(repository).findById(id);
    }

    @Test
    void create_shouldSaveAndReturnResponse() {
        // Arrange
        CreateSkillOfferRequest request = new CreateSkillOfferRequest();
        SkillOffer domain = new SkillOffer();
        SkillOffer saved = new SkillOffer();
        SkillOfferResponse expected = new SkillOfferResponse();

        when(mapper.toDomain(request)).thenReturn(domain);
        when(repository.save(domain)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(expected);

        // Act
        SkillOfferResponse result = service.create(request);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(mapper).toDomain(request);
        verify(repository).save(domain);
        verify(mapper).toResponse(saved);
    }

    @Test
    void update_shouldUpdateAndReturnResponse_whenOfferExists() {
        // Arrange
        Long id = 1L;
        CreateSkillOfferRequest request = new CreateSkillOfferRequest();

        SkillOffer domain = new SkillOffer();
        SkillOffer updated = new SkillOffer();
        SkillOfferResponse expected = new SkillOfferResponse();

        when(repository.existsById(id)).thenReturn(true);
        when(mapper.toDomain(request)).thenReturn(domain);
        when(repository.update(id, domain)).thenReturn(updated);
        when(mapper.toResponse(updated)).thenReturn(expected);

        // Act
        SkillOfferResponse result = service.update(id, request);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(repository).existsById(id);
        verify(mapper).toDomain(request);
        verify(repository).update(id, domain);
        verify(mapper).toResponse(updated);
    }

    @Test
    void delete_shouldCallRepository_whenOfferExists() {
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
    void delete_shouldThrowException_whenOfferNotFound() {
        // Arrange
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.delete(id));

        verify(repository).existsById(id);
    }
}