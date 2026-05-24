package school.work.skillswap_b.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import school.work.skillswap_b.domain.AvailabilityDay;
import school.work.skillswap_b.domain.Format;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CreateSkillOfferRequest {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category must be between 2 and 50 characters")
    private String category;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;

    @Future(message = "Expiration date must be in the future")
    private LocalDateTime expirationDate;

    private Format format;

    private Set<AvailabilityDay> availability = new HashSet<>();
}