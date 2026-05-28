package school.work.skillswap_b.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.exception.ConflictException;
import school.work.skillswap_b.exception.NotFoundException;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;
import school.work.skillswap_b.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserPersistenceRepository repository;
    // no mapper here anymore

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new ConflictException("Email already exists");
        }
        user.setCreatedAt(LocalDateTime.now());
        return repository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return repository.update(id, user);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}