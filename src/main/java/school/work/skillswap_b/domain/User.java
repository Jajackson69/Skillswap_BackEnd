package school.work.skillswap_b.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class User {

    @Setter(AccessLevel.NONE)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private LocalDateTime createdAt;

    public User(Long id) {
        this.id = id;
    }
}