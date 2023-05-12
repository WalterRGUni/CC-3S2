package PruebasEspecificaciones;

import org.junit.jupiter.api.Test;

import static PruebasEspecificaciones.StringUtils.substringsBetween;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilsTest {

    @Test void strIsNullOrEmpty() {
        assertThat(substringsBetween(null, "a", "b")).isEqualTo(null); // T1
        assertThat(substringsBetween("", "a", "b")).isEqualTo(new String[]{}); // T2
    }

    @Test
    void openIsNullOrEmpty() {
        // Completa
    }

    @Test
    void closeIsNullOrEmpty() {
        // Completa
    }

    @Test
    void strOfLength1() {
        // Completa
    }

    @Test
    void openAndCloseOfLength1() {
        // Completa
    }

    @Test
    void openAndCloseTagsOfDifferentSizes() {
       // Completa
    }

    @Test
    void noSubstringBetweenOpenAndCloseTags() {
        // Completa
    }

    @Test
    void closeTagAppearingMultipleTimes() {
        // Completa
    }
}
