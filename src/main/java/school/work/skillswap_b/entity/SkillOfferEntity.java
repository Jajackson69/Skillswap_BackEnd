package school.work.skillswap_b.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import school.work.skillswap_b.domain.AvailabilityDay;
import school.work.skillswap_b.domain.Format;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private UserEntity owner;

    private LocalDateTime creationDate;
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private Format format;

    @ElementCollection(targetClass = AvailabilityDay.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "skill_offer_availability", joinColumns = @JoinColumn(name = "skill_offer_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Set<AvailabilityDay> availability = new HashSet<>();
}
