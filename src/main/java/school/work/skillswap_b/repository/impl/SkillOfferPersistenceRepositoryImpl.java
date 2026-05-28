package school.work.skillswap_b.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.entity.SkillOfferEntity;
import school.work.skillswap_b.entity.UserEntity;
import school.work.skillswap_b.exception.NotFoundException;
import school.work.skillswap_b.repository.interfaces.SkillOfferRepository;
import school.work.skillswap_b.repository.interfaces.SkillOfferPersistenceRepository;
import school.work.skillswap_b.repository.interfaces.UserRepository;
import school.work.skillswap_b.repository.mappers.SkillOfferEntityMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SkillOfferPersistenceRepositoryImpl implements SkillOfferPersistenceRepository {

    private final SkillOfferRepository jpaRepository;
    private final UserRepository userRepository;
    private final SkillOfferEntityMapper mapper;

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
                .orElseThrow(() -> new NotFoundException("SkillOffer not found with id " + id));

        return mapper.toDomain(entity);
    }

    @Override
    public SkillOffer save(SkillOffer domain) {
        SkillOfferEntity entity = mapper.toEntity(domain);

        UserEntity ownerRef = userRepository.getReferenceById(domain.getOwner().getId());
        entity.setOwner(ownerRef);

        SkillOfferEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public SkillOffer update(Long id, SkillOffer domain) {
        SkillOfferEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SkillOffer not found with id " + id));

        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setOwner(userRepository.getReferenceById(domain.getOwner().getId()));
        // creationDate is set once at creation and must never be reassigned on update.
        // The update request carries no creationDate, so touching it here wiped it to null.
        entity.setExpirationDate(domain.getExpirationDate());
        entity.setFormat(domain.getFormat());
        entity.setAvailability(domain.getAvailability() != null ? domain.getAvailability() : new java.util.HashSet<>());

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