package school.work.skillswap_b.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.work.skillswap_b.controller.mappers.SkillOfferDtoMapper;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;
import school.work.skillswap_b.repository.interfaces.SkillOfferPersistenceRepository;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;
import school.work.skillswap_b.service.interfaces.SkillOfferService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillOfferServiceImpl implements SkillOfferService {

    private final SkillOfferPersistenceRepository repository;
    private final UserPersistenceRepository userRepository; // ← ajouté pour fetcher le user
    private final SkillOfferDtoMapper mapper;

    @Override
    public List<SkillOfferResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public SkillOfferResponse getById(long id) {
        SkillOffer domain = repository.findById(id);

        if (domain == null) {
            throw new RuntimeException("SkillOffer with id " + id + " not found");
        }

        return mapper.toResponse(domain);
    }

    @Override
    public SkillOfferResponse create(CreateSkillOfferRequest request) {
        User owner = userRepository.findById(request.getUserId());
        SkillOffer domain = mapper.toDomain(request, owner);
        domain.setCreationDate(LocalDateTime.now());

        SkillOffer saved = repository.save(domain);
        return mapper.toResponse(saved);
    }

    @Override
    public SkillOfferResponse update(long id, CreateSkillOfferRequest request) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SkillOffer with id " + id + " not found");
        }

        User owner = userRepository.findById(request.getUserId());
        SkillOffer updated = mapper.toDomain(request, owner);
        SkillOffer saved = repository.update(id, updated);

        return mapper.toResponse(saved);
    }

    @Override
    public void delete(long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("SkillOffer not found");
        }
        repository.deleteById(id);
    }
}