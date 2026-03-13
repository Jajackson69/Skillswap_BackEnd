package school.work.skillswap_b.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;
import school.work.skillswap_b.entity.SkillOfferEntity;
import school.work.skillswap_b.repository.SkillOfferRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillOfferService {

    private final SkillOfferRepository repository;

    public List<SkillOfferResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SkillOfferResponse getById(Long id) {
        SkillOfferEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillOffer not found"));
        return toResponse(entity);
    }

    public SkillOfferResponse create(CreateSkillOfferRequest request) {
        SkillOffer domain = toDomain(request);
        if (!domain.isValid()) {
            throw new IllegalArgumentException("Title is obligatory");
        }
        SkillOfferEntity entity = toEntity(domain);
        return toResponse(repository.save(entity));
    }

    public SkillOfferResponse update(Long id, CreateSkillOfferRequest request) {
        SkillOfferEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SkillOffer not found"));

        SkillOffer domain = toDomain(request);
        if (!domain.isValid()) {
            throw new IllegalArgumentException("Title is obligatory");
        }

        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setOwnerName(domain.getOwnerName());

        return toResponse(repository.update(entity));
    }

    public void delete(Long id) {
        if  (!repository.existsById(id)) {
            throw new IllegalArgumentException("SkillOffer not found");

        }
        repository.deleteById(id);
    }

    private SkillOfferResponse toResponse(SkillOfferEntity entity) {
        SkillOfferResponse response = new SkillOfferResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setCategory(entity.getCategory());
        response.setOwnerName(entity.getOwnerName());
        return response;
    }

    private SkillOffer toDomain(CreateSkillOfferRequest request) {
        SkillOffer domain = new SkillOffer();
        domain.setTitle(request.getTitle());
        domain.setDescription(request.getDescription());
        domain.setCategory(request.getCategory());
        domain.setOwnerName(request.getOwnerName());
        return domain;
    }

    private SkillOfferEntity toEntity(SkillOffer domain) {
        SkillOfferEntity entity = new SkillOfferEntity();
        entity.setTitle(domain.getTitle());
        entity.setDescription(domain.getDescription());
        entity.setCategory(domain.getCategory());
        entity.setOwnerName(domain.getOwnerName());
        return entity;
    }
}
