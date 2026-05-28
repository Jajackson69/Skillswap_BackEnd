package school.work.skillswap_b.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import school.work.skillswap_b.domain.User;
import school.work.skillswap_b.entity.UserEntity;
import school.work.skillswap_b.exception.NotFoundException;
import school.work.skillswap_b.repository.interfaces.UserPersistenceRepository;
import school.work.skillswap_b.repository.interfaces.UserRepository;
import school.work.skillswap_b.repository.mappers.UserEntityMapper;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPersistenceRepositoryImpl implements UserPersistenceRepository {

    private final UserRepository jpaRepository;
    private final UserEntityMapper mapper;

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        return mapper.toDomain(entity);
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public User update(Long id, User user) {
        UserEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));

        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        // Only overwrite email when one was actually supplied.
        // Profile updates do not carry an email, so guarding this
        // prevents the email from being wiped to null on profile edits.
        if (user.getEmail() != null) {
            entity.setEmail(user.getEmail());
        }
        entity.setBio(user.getBio());

        UserEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}