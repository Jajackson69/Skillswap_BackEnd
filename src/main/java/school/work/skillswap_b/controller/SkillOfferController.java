package school.work.skillswap_b.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import school.work.skillswap_b.domain.SkillOffer;
import school.work.skillswap_b.dto.CreateSkillOfferRequest;
import school.work.skillswap_b.dto.SkillOfferResponse;
import school.work.skillswap_b.service.SkillOfferService;

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
        public ResponseEntity<SkillOfferResponse> createSkillOffer(@RequestBody CreateSkillOfferRequest request) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));

        }

        @PutMapping("/{id}")
        public ResponseEntity<SkillOfferResponse> updateSkillOffer(@PathVariable Long id, @RequestBody CreateSkillOfferRequest request) {
            return ResponseEntity.ok(service.update(id, request));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSkillOffer(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

