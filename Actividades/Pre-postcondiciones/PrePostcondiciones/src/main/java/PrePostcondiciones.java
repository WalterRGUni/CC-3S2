public class PrePostcondiciones {

  /**
   * @param list precondición: list.length > 0
   * @return q postcondición: q[i] = list[list.length - 1 - i] para cada i en [0, list.length - 1)
   */
  int[] reverse(int[] list) {
    int[] q = new int[list.length];
    for (int i = 0; i < list.length; i++) {
      q[i] = list[list.length - 1 - i];
    }
    return q;
  }

  /**
   * @param list precondición: list.length > 0
   * @return ind postcondición: list[ind] = key si key está en list caso contratio ind = -1
   */
  int linearSearch(int[] list, int key) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == key) {
        return i;
      }
    }
    return -1;
  }

  /**
   * @param letra precondición: letra es un caracter
   * @return q postcondición: q es true si letra es vocal en otro caso es false
   */
  boolean isVowel(char letra) {
    switch (letra) {
      case 'a':
      case 'e':
      case 'i':
      case 'o':
      case 'u':
      case 'A':
      case 'E':
      case 'I':
      case 'O':
      case 'U':
        return true;
      default:
        return false;
    }
  }

  /**
   * @param year precondición: year > 0
   * @return q postcondición: q = True si year es bisiesto y q = False caso contrario
   */
  boolean isLeapYear(int year) {
    if (year % 4 == 0) {
      if (year % 100 == 0) {
        if (year % 400 == 0) {
          return true;
        } else {
          return false;
        }
      } else {
        return true;
      }
    } else {
      return false;
    }
  }

  /**
   * @param a precondición: a > 0
   * @param b precondición: b > 0
   * @param c precondición: c > 0
   * @return t postcondición t = SCALENE si el triángulo de lados a, b, c es escaleno. t = ISOSCELES
   * si el triángulo de lados a, b, c es isósceles. t = EQUILATERAL si el triángulo de lados a, b, c
   * es equilátero.
   */
  TriangleType reportTriangleType(int a, int b, int c) {
    if (a == b || b == c || a == c) {
      if (a == b && b == c) {
        return TriangleType.EQUILATERAL;
      } else {
        return TriangleType.ISOSCELES;
      }
    }
    return TriangleType.SCALENE;
  }

  enum TriangleType {
    SCALENE, ISOSCELES, EQUILATERAL
  }
}
