package school.work.skillswap_b.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.work.skillswap_b.controller.mappers.ProfileDtoMapper;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.ProfileResponse;
import school.work.skillswap_b.dto.UpdateProfileRequest;
import school.work.skillswap_b.service.interfaces.ProfileService;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileDtoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long id) {
        User domain = profileService.getProfile(id);
        return ResponseEntity.ok(mapper.toResponse(domain));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponse> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProfileRequest request) {
        User domain = mapper.toDomain(request);
        User updated = profileService.updateProfile(id, domain);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }
}
