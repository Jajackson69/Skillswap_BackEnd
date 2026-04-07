package school.work.skillswap_b.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;
import school.work.skillswap_b.service.interfaces.SkillOfferService;

import java.util.List;

@RestController
@RequestMapping("/api/skill-offers")
@RequiredArgsConstructor
public class SkillOfferController {

    private final SkillOfferService service;

    @GetMapping
    public ResponseEntity<List<SkillOfferResponse>> getAllSkillOffers() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillOfferResponse> getSkillOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<SkillOfferResponse> createSkillOffer(
            @Valid @RequestBody CreateSkillOfferRequest request) {

        SkillOfferResponse response = service.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillOfferResponse> updateSkillOffer(
            @PathVariable Long id,
            @Valid @RequestBody CreateSkillOfferRequest request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkillOffer(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}