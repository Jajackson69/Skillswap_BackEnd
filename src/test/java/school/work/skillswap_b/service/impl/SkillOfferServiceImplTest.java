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
    void setup() {
        repository = mock(SkillOfferPersistenceRepository.class);
        mapper = mock(SkillOfferDtoMapper.class);
        service = new SkillOfferServiceImpl(repository, mapper);
    }

    @Test
    void getAll_returnsListOfResponses() {
        SkillOffer domain = new SkillOffer();
        SkillOfferResponse response = new SkillOfferResponse();

        when(repository.findAll()).thenReturn(List.of(domain));
        when(mapper.toResponse(domain)).thenReturn(response);

        List<SkillOfferResponse> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository).findAll();
        verify(mapper).toResponse(domain);
    }

    @Test
    void getById_returnsResponse() {
        SkillOffer domain = new SkillOffer();
        SkillOfferResponse response = new SkillOfferResponse();

        when(repository.findById(1L)).thenReturn(domain);
        when(mapper.toResponse(domain)).thenReturn(response);

        SkillOfferResponse result = service.getById(1L);

        assertNotNull(result);
        verify(repository).findById(1L);
    }

    @Test
    void getById_throwsException_whenNotFound() {
        when(repository.findById(1L)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> service.getById(1L));
    }

    @Test
    void create_savesAndReturnsResponse() {
        CreateSkillOfferRequest request = new CreateSkillOfferRequest();
        SkillOffer domain = new SkillOffer();
        SkillOffer saved = new SkillOffer();
        SkillOfferResponse response = new SkillOfferResponse();

        when(mapper.toDomain(request)).thenReturn(domain);
        when(repository.save(domain)).thenReturn(saved);
        when(mapper.toResponse(saved)).thenReturn(response);

        SkillOfferResponse result = service.create(request);

        assertNotNull(result);
        verify(repository).save(domain);
    }

    @Test
    void update_updatesAndReturnsResponse() {
        CreateSkillOfferRequest request = new CreateSkillOfferRequest();
        SkillOffer domain = new SkillOffer();
        SkillOffer updated = new SkillOffer();
        SkillOfferResponse response = new SkillOfferResponse();

        when(repository.existsById(1L)).thenReturn(true);
        when(mapper.toDomain(request)).thenReturn(domain);
        when(repository.update(1L, domain)).thenReturn(updated);
        when(mapper.toResponse(updated)).thenReturn(response);

        SkillOfferResponse result = service.update(1L, request);

        assertNotNull(result);
        verify(repository).update(1L, domain);
    }

    @Test
    void delete_callsRepository() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void delete_throwsException_whenNotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.delete(1L));
    }
}