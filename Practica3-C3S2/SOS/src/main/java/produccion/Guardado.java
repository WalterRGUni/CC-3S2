package produccion;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Guardado {

  /**
   * Guarda el juego en el archivo juegoGuardado.txt
   * @param juegoGuardado cadena de texto que contiene las juagadas realizadas en el juego
   */
  public static void guardarJuego(StringBuilder juegoGuardado) {
    try (PrintWriter out = new PrintWriter("juegoGuardado.txt")) {
      out.print(juegoGuardado);
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Añade la jugada actual a la cadena de texto juegoGuardado
   * @param fila fila de la jugada
   * @param columna columna de la jugada
   * @param valorCelda valor de la celda de la jugada
   * @param turno turno actual
   * @param juegoGuardado cadena de texto que guarda las jugadas
   */
  public static void guardarJugada(int fila, int columna, Celda valorCelda, Turno turno, StringBuilder juegoGuardado) {
    juegoGuardado.append(String.format("%s: (%d, %d) -> %s%n", turno, fila, columna, valorCelda));
  }
}
