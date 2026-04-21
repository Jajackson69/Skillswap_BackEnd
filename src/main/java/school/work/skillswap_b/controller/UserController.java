package school.work.skillswap_b.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.work.skillswap_b.controller.mappers.UserDtoMapper;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.UserRequest;
import school.work.skillswap_b.dto.UserResponse;
import school.work.skillswap_b.service.interfaces.UserService;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper mapper; // mapper lives here now

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> response = userService.getAll()
                .stream()
                .map(mapper::toResponse)      // Domain to DTO
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        User domain = userService.getById(id);
        return ResponseEntity.ok(mapper.toResponse(domain)); // Domain to DTO
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
        User domain = mapper.toDomain(request);              // DTO to Domain
        User saved = userService.create(domain);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request) {
        User domain = mapper.toDomain(request);              // DTO to Domain
        User saved = userService.update(id, domain);
        return ResponseEntity.ok(mapper.toResponse(saved));  // Domain to DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}