package school.work.skillswap_b.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillOffer {
    private String title;
    private String description;
    private String category;
    private String ownerName;

    public boolean isValid() {
        return title != null && !title.isEmpty();
    }
}
