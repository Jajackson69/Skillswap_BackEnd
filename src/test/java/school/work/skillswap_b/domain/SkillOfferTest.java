package school.work.skillswap_b.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SkillOfferTest {

    @Test
    void isExpired_shouldReturnTrue_whenExpirationDateIsInPast() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setExpirationDate(LocalDateTime.now().minusDays(1));

        // Act
        boolean result = offer.isExpired();

        // Assert
        assertTrue(result);
    }

    @Test
    void isExpired_shouldReturnFalse_whenExpirationDateIsInFuture() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        boolean result = offer.isExpired();

        // Assert
        assertFalse(result);
    }

    @Test
    void isExpired_shouldReturnFalse_whenExpirationDateIsNull() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setExpirationDate(null);

        // Act
        boolean result = offer.isExpired();

        // Assert
        assertFalse(result);
    }

    @Test
    void isActive_shouldReturnTrue_whenNotExpiredAndTitleIsValid() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setTitle("Java");
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        boolean result = offer.isActive();

        // Assert
        assertTrue(result);
    }

    @Test
    void isActive_shouldReturnFalse_whenExpired() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setTitle("Java");
        offer.setExpirationDate(LocalDateTime.now().minusDays(1));

        // Act
        boolean result = offer.isActive();

        // Assert
        assertFalse(result);
    }

    @Test
    void isActive_shouldReturnFalse_whenTitleIsNull() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setTitle(null);
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        boolean result = offer.isActive();

        // Assert
        assertFalse(result);
    }

    @Test
    void isActive_shouldReturnFalse_whenTitleIsEmpty() {
        // Arrange
        SkillOffer offer = new SkillOffer();
        offer.setTitle("");
        offer.setExpirationDate(LocalDateTime.now().plusDays(1));

        // Act
        boolean result = offer.isActive();

        // Assert
        assertFalse(result);
    }
}