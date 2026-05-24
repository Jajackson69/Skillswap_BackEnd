package school.work.skillswap_b.service.interfaces;

import school.work.skillswap_b.domain.User;

public interface ProfileService {

    User getProfile(Long id);

    User updateProfile(Long id, User user);
}
