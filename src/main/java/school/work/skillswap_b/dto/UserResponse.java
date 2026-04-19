package school.work.skillswap_b.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonPropertyOrder({"id", "firstName", "lastName", "email", "bio", "createdAt"})
public class UserResponse {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String bio;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
}