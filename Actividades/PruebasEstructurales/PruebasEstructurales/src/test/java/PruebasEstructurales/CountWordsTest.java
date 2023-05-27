package PruebasEstructurales;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CountWordsTest {

    @Test
    void twoWordsEndingWithS() {   // 1
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }
    @Test
    void noWordsAtAll() {  // 2
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }
    /*@Test
    void wordsThatEndInR() {
        int words = new CountWords().count("letter writter");
        assertThat(words).isEqualTo(2);
    }*/
}