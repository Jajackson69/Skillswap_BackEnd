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
    void setUp() {
        jpaRepository = mock(SkillOfferRepository.class);
        mapper = mock(SkillOfferEntityMapper.class);
        repository = new SkillOfferPersistenceRepositoryImpl(jpaRepository, mapper);
    }

    @Test
    void findAll_shouldReturnMappedDomainList() {
        // Arrange
        SkillOfferEntity entity = new SkillOfferEntity();
        SkillOffer domain = new SkillOffer();

        when(jpaRepository.findAll()).thenReturn(List.of(entity));
        when(mapper.toDomain(entity)).thenReturn(domain);

        // Act
        List<SkillOffer> result = repository.findAll();

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
        SkillOfferEntity entity = new SkillOfferEntity();
        SkillOffer expected = new SkillOffer();

        when(jpaRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(mapper.toDomain(entity)).thenReturn(expected);

        // Act
        SkillOffer result = repository.findById(id);

        // Assert
        assertNotNull(result);
        assertSame(expected, result);

        verify(jpaRepository).findById(id);
        verify(mapper).toDomain(entity);
    }

    @Test
    void save_shouldMapDomainToEntity_andReturnMappedDomain() {
        // Arrange
        SkillOffer domain = new SkillOffer();
        SkillOfferEntity entity = new SkillOfferEntity();
        SkillOfferEntity savedEntity = new SkillOfferEntity();
        SkillOffer expected = new SkillOffer();

        when(mapper.toEntity(domain)).thenReturn(entity);
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(expected);

        // Act
        SkillOffer result = repository.save(domain);

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

        SkillOffer domain = new SkillOffer();
        domain.setTitle("New Title");

        SkillOfferEntity entity = new SkillOfferEntity();
        entity.setId(id);
        entity.setTitle("Old Title");

        SkillOfferEntity savedEntity = new SkillOfferEntity();
        savedEntity.setId(id);
        savedEntity.setTitle("New Title");

        SkillOffer expected = new SkillOffer();
        expected.setId(id);
        expected.setTitle("New Title");

        when(jpaRepository.findById(id)).thenReturn(java.util.Optional.of(entity));
        when(jpaRepository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDomain(savedEntity)).thenReturn(expected);

        // Act
        SkillOffer result = repository.update(id, domain);

        // Assert
        assertNotNull(result);
        assertEquals("New Title", entity.getTitle());
        assertSame(expected, result);

        verify(jpaRepository).findById(id);
        verify(jpaRepository).save(entity);
        verify(mapper).toDomain(savedEntity);
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
}