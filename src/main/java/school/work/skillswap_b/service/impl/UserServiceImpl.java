package school.work.skillswap_b.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.work.skillswap_b.controller.mappers.UserDtoMapper;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.dto.UserRequest;
import school.work.skillswap_b.dto.UserResponse;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;
import school.work.skillswap_b.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistenceRepository repository;
    private final UserDtoMapper mapper;

    @Override
    public List<UserResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse getById(Long id) {
        User user = repository.findById(id);
        return mapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User domain = mapper.toDomain(request);
        domain.setCreatedAt(LocalDateTime.now());
        User saved = repository.save(domain);
        return mapper.toResponse(saved);
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        User domain = mapper.toDomain(request);
        User saved = repository.update(id, domain);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}