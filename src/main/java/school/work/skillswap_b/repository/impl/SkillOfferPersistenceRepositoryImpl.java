package school.work.skillswap_b.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;
import school.work.skillswap_b.repository.interfaces.SkillOfferRepository;
import school.work.skillswap_b.repository.interfaces.SkillOfferPersistenceRepository;
import school.work.skillswap_b.repository.mappers.SkillOfferEntityMapper;
import school.work.skillswap_b.repository.mappers.UserEntityMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillOfferPersistenceRepositoryImpl implements SkillOfferPersistenceRepository {

    private final SkillOfferRepository jpaRepository;
    private final SkillOfferEntityMapper mapper;
    private final UserEntityMapper userEntityMapper;

    @Override
    public List<SkillOffer> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public SkillOffer findById(Long id) {
        SkillOfferEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillOffer not found with id " + id));

        return mapper.toDomain(entity);
    }

    @Override
    public SkillOffer save(SkillOffer domain) {
        SkillOfferEntity entity = mapper.toEntity(domain);
        SkillOfferEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public SkillOffer update(Long id, SkillOffer domain) {
        SkillOfferEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillOffer not found"));

        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setOwner(userEntityMapper.toEntity(domain.getOwner()));
        entity.setCreationDate(domain.getCreationDate());
        entity.setExpirationDate(domain.getExpirationDate());

        SkillOfferEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }
}