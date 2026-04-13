package school.work.skillswap_b.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonPropertyOrder({
        "id",
        "title",
        "description",
        "category",
        "ownerName",
        "creationDate",
        "expirationDate"
})
@Getter
@Setter
public class SkillOfferResponse {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String ownerName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime creationDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime expirationDate;
}
