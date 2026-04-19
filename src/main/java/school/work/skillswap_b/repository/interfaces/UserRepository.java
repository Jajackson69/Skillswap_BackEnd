package school.work.skillswap_b.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import school.work.skillswap_b.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

}