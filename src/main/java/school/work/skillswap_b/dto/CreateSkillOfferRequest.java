package school.work.skillswap_b.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateSkillOfferRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String title;
    @NotBlank(message = "Description is required")
    @Size(min = 3, max = 200, message = "Description must be between 3 and 200 characters")
    private String description;
    @NotBlank(message = "Category is required")
    private String category;
    private Long userId;
    private LocalDateTime expirationDate;
}
