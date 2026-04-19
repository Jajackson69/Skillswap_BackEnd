package school.work.skillswap_b.service.interfaces;

import school.work.skillswap_b.dto.UserRequest;
import school.work.skillswap_b.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse create(UserRequest request);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);
}