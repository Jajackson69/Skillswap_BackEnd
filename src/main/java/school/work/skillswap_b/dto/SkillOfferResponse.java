package school.work.skillswap_b.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import school.work.skillswap_b.domain.AvailabilityDay;
import school.work.skillswap_b.domain.Format;

import java.time.LocalDateTime;
import java.util.Set;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "category",
        "owner",
        "format",
        "availability",
        "creationDate",
        "expirationDate"
})
@Getter
@Setter
public class SkillOfferResponse {
    private Long id;
    private String title;
    private String description;
    private String category;
    private UserResponse owner;
    private Format format;
    private Set<AvailabilityDay> availability;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime expirationDate;
}
