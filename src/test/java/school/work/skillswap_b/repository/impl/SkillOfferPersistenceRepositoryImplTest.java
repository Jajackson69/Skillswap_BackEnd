package school.work.skillswap_b.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;
import school.work.skillswap_b.repository.interfaces.SkillOfferRepository;
import school.work.skillswap_b.repository.mappers.SkillOfferEntityMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillOfferPersistenceRepositoryImplTest {

    private SkillOfferRepository jpaRepository;
    private SkillOfferEntityMapper mapper;
    private SkillOfferPersistenceRepositoryImpl repository;

    @BeforeEach
    void setup() {
        jpaRepository = mock(SkillOfferRepository.class);
        mapper = mock(SkillOfferEntityMapper.class);
        repository = new SkillOfferPersistenceRepositoryImpl(jpaRepository, mapper);
    }

    @Test
    void findAll_returnsMappedDomainObjects() {
        SkillOfferEntity entity = new SkillOfferEntity();
        SkillOffer domain = new SkillOffer();

        when(jpaRepository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        List<SkillOffer> result = repository.findAll();

        assertEquals(1, result.size());
        assertSame(domain, result.getFirst());

        verify(jpaRepository).findAll();
        verify(mapper).toDomain(entity);
    }

    @Test
    void findById_returnsMappedDomainObject() {
        // ========================
        // Arrange
        // ========================
        Long id = 1L;

        SkillOfferEntity entity = new SkillOfferEntity();
        SkillOffer expectedDomain = new SkillOffer();

        when(jpaRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(expectedDomain);

        // ========================
        // Act
        // ========================
        SkillOffer result = repository.findById(id);

        // ========================
        // Assert
        // ========================
        assertNotNull(result);
        assertSame(expectedDomain, result);

        verify(jpaRepository).findById(id);
        verify(mapper).toDomain(entity);
    }

    @Test
    void save_mapsDomainToEntity_andBack() {
        SkillOffer domain = new SkillOffer();
        SkillOfferEntity entity = new SkillOfferEntity();
        SkillOfferEntity savedEntity = new SkillOfferEntity();
        SkillOffer savedDomain = new SkillOffer();

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(savedDomain);

        SkillOffer result = repository.save(domain);

        assertSame(savedDomain, result);

        verify(mapper).toEntity(domain);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
    }

    @Test
    void update_updatesEntityFields_andReturnsMappedDomain() {
        // ========================
        // Arrange
        // ========================
        Long id = 1L;

        SkillOffer domain = new SkillOffer();
        domain.setTitle("New Title");

        SkillOfferEntity entity = new SkillOfferEntity();
        entity.setId(id);
        entity.setTitle("Old Title");

        SkillOfferEntity savedEntity = new SkillOfferEntity();
        savedEntity.setId(id);
        savedEntity.setTitle("New Title");

        SkillOffer expectedResult = new SkillOffer();
        expectedResult.setId(id);
        expectedResult.setTitle("New Title");

        when(jpaRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(expectedResult);

        // ========================
        // Act
        // ========================
        SkillOffer result = repository.update(id, domain);

        // ========================
        // Assert
        // ========================
        // Verify entity was updated
        assertEquals("New Title", entity.getTitle());

        // Verify returned value
        assertNotNull(result);
        assertSame(expectedResult, result);

        // Verify interactions
        verify(jpaRepository).findById(id);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
    }

    @Test
    void deleteById_callsJpaRepository() {
        repository.deleteById(1L);

        verify(jpaRepository).deleteById(1L);
    }

    @Test
    void existsById_callsJpaRepository() {
        when(jpaRepository.existsById(1L)).thenReturn(true);

        boolean result = repository.existsById(1L);

        assertTrue(result);
        verify(jpaRepository).existsById(1L);
    }
}