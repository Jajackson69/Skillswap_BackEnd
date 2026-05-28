package school.work.skillswap_b.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.exception.NotFoundException;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;
import school.work.skillswap_b.service.interfaces.ProfileService;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserPersistenceRepository repository;

    @Override
    public User getProfile(Long id) {
        return repository.findById(id);
    }

    @Override
    public User updateProfile(Long id, User user) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Profile not found with id " + id);
        }
        return repository.update(id, user);
    }
}
