package school.work.skillswap_b.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "skill_offers")
public class SkillOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity owner; //changed ownername to match user entity
    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;
}
