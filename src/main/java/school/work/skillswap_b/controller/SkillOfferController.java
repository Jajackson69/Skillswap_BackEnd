package school.work.skillswap_b.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import school.work.skillswap_b.controller.mappers.SkillOfferDtoMapper;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;
import school.work.skillswap_b.service.interfaces.SkillOfferService;

import java.util.List;

@RestController
@RequestMapping("/api/skill-offers")
@RequiredArgsConstructor
public class SkillOfferController {

    private final SkillOfferService service;
    private final SkillOfferDtoMapper mapper;       //Mapper in controller now
    private final UserPersistenceRepository userRepository;

    @GetMapping
    public ResponseEntity<List<SkillOfferResponse>> getAllSkillOffers() {
        List<SkillOfferResponse> response = service.getAll()
                .stream()
                .map(mapper::toResponse)            // Domain to DTO
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillOfferResponse> getSkillOfferById(@PathVariable Long id) {
        SkillOffer domain = service.getById(id);
        return ResponseEntity.ok(mapper.toResponse(domain)); // Domain to DTO
    }

    @PostMapping
    public ResponseEntity<SkillOfferResponse> createSkillOffer(
            @Valid @RequestBody CreateSkillOfferRequest request) {

        User owner = userRepository.findById(request.getUserId());
        SkillOffer domain = mapper.toDomain(request, owner);       // DTO to Domain
        SkillOffer saved = service.create(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillOfferResponse> updateSkillOffer(
            @PathVariable Long id,
            @Valid @RequestBody CreateSkillOfferRequest request) {

        User owner = userRepository.findById(request.getUserId());
        SkillOffer domain = mapper.toDomain(request, owner);       // DTO to Domain
        SkillOffer saved = service.update(id, domain);
        return ResponseEntity.ok(mapper.toResponse(saved));        // Domain to DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkillOffer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}