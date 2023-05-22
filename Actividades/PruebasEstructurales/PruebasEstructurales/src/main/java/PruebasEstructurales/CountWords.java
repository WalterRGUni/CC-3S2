package PruebasEstructurales;

public class CountWords {
    public int count(String str) {
        int words = 0;
        char last = ' ';
        for (int i = 0; i < str.length(); i++) {    // 1
            if (!Character.isLetter(str.charAt(i)) &&    // 2
                    (last == 's' || last == 'r')) {
                words++;
            }
            last = str.charAt(i);     //3
        }
        if (last == 'r' || last == 's') {
            words++;
        }
        return words;
    }
}
