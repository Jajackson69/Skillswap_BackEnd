package school.work.skillswap_b.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SkillOffer {
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private String category;
    private String ownerName;
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    public boolean isExpired() {
        return expirationDate != null && LocalDateTime.now().isAfter(expirationDate);
    }

    public boolean isActive() {
        return !isExpired() && title != null && !title.isEmpty();
    }

}
