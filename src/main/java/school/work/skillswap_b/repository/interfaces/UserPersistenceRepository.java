package school.work.skillswap_b.repository.interfaces;

import school.work.skillswap_b.domain.User;
import java.util.List;

public interface UserPersistenceRepository {

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    User update(Long id, User user);

    void deleteById(Long id);

    boolean existsById(Long id);

    boolean existsByEmail(String email);
}