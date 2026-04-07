package school.work.skillswap_b.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillOfferTest {

    @Test
    void isExpired_returnsTrue_whenExpirationDateIsPast() {
        SkillOffer offer = new SkillOffer();
        offer.setExpirationDate(LocalDateTime.now().minusDays(1));

        assertTrue(offer.isExpired());
    }

    @Test
    void isExpired_returnsFalse_whenExpirationDateIsFuture() {
        SkillOffer offer = new SkillOffer();
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        assertFalse(offer.isExpired());
    }

    @Test
    void isExpired_returnsFalse_whenExpirationDateIsNull() {
        SkillOffer offer = new SkillOffer();
        offer.setExpirationDate(null);

        assertFalse(offer.isExpired());
    }

    @Test
    void isActive_returnsTrue_whenNotExpired_andTitlePresent() {
        SkillOffer offer = new SkillOffer();
        offer.setTitle("Java");
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        assertTrue(offer.isActive());
    }

    @Test
    void isActive_returnsFalse_whenExpired() {
        SkillOffer offer = new SkillOffer();
        offer.setTitle("Java");
        offer.setExpirationDate(LocalDateTime.now().minusDays(1));

        assertFalse(offer.isActive());
    }

    @Test
    void isActive_returnsFalse_whenTitleIsNull() {
        SkillOffer offer = new SkillOffer();
        offer.setTitle(null);
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        assertFalse(offer.isActive());
    }

    @Test
    void isActive_returnsFalse_whenTitleIsEmpty() {
        SkillOffer offer = new SkillOffer();
        offer.setTitle("");
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        assertFalse(offer.isActive());
    }
}