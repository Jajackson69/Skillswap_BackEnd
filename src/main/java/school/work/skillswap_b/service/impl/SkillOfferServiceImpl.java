package school.work.skillswap_b.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.exception.NotFoundException;
import school.work.skillswap_b.repository.interfaces.SkillOfferPersistenceRepository;
import school.work.skillswap_b.service.interfaces.SkillOfferService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillOfferServiceImpl implements SkillOfferService {

    private final SkillOfferPersistenceRepository repository;
    //no mapper here anymore

    @Override
    public List<SkillOffer> getAll() {
        return repository.findAll();
    }

    @Override
    public SkillOffer getById(long id) {
        SkillOffer domain = repository.findById(id);
        if (domain == null) {
            throw new NotFoundException("SkillOffer with id " + id + " not found");
        }
        return domain;
    }

    @Override
    public SkillOffer create(SkillOffer skillOffer) {
        skillOffer.setCreationDate(LocalDateTime.now());
        return repository.save(skillOffer);
    }

    @Override
    public SkillOffer update(long id, SkillOffer skillOffer) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("SkillOffer with id " + id + " not found");
        }
        return repository.update(id, skillOffer);
    }

    @Override
    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("SkillOffer with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}