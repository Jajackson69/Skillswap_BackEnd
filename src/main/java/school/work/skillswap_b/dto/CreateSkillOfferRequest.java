package school.work.skillswap_b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSkillOfferRequest {
    private String title;
    private String description;
    private String category;
    private String ownerName;
}
