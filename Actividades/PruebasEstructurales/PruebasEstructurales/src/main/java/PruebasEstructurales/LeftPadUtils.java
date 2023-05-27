package PruebasEstructurales;

public class LeftPadUtils {
    private static final String SPACE = " ";

    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     *
     * @param str  una cadena para completar,puede ser null
     * @param size 'longitud' a completar
     * @param padStr  la cadena con la que completar, Puede ser null o vacio
     *                se trata como un solo espacio
     * @return una cadena con un desplazamiento left, cadena original
     *  {@code null} si se entrega una cadena null
     *  Ejemplo: "abc" cadena de entrada, "-" cadena a completar, size=5
     *  Salida: "--abc"
     */
    public static String leftPad(final String str, final int size, String padStr) {
        if (str == null) { // 1
            return null;
        }
        if (isEmpty(padStr)) {
            padStr = SPACE; // 2
        }
        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = size - strLen;
        if (pads <= 0) {
            return str; // 3
        }

        if (pads == padLen) { // 4
            return padStr.concat(str);
        } else if (pads < padLen) { // 5
            return padStr.substring(0, pads).concat(str);
        } else { // 6
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str);
        }
    }

}
