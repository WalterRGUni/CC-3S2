package prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.AutoJuegoGeneral;
import produccion.Celda;
import produccion.EstadoJuego;
import produccion.TipoJugador;

public class TestAutoJuegoGeneral {

  private AutoJuegoGeneral juego;

  @BeforeEach
  public void setUp() {
    juego = new AutoJuegoGeneral(3, TipoJugador.HUMANO, TipoJugador.COMPUTADORA);
  }

  // Criterio de aceptación 9.2
  @Test
  public void testMovimientoComputadoraAutoJuegoGeneral() {
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
    juego.realizarMovimiento(1, 1, Celda.S); // movimiento Humano

    assertEquals(7,
        juego.getNumeroCeldasVacias()); // Celdas vacías = 9 - 1(humano) - 1(computador) = 7
  }

  // Criterio de aceptación 11.1
  @Test
  public void testAutoJuegoGeneralCompletoHumanoAzulComputadoraRojo() {
    juego = new AutoJuegoGeneral(3, TipoJugador.HUMANO, TipoJugador.COMPUTADORA);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);

    while (juego.getEstadoJuego() == EstadoJuego.JUGANDO) {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (juego.getCelda(i, j) == Celda.VACIA) {
            juego.realizarMovimiento(i, j, Celda.S);
          }
        }
      }
    }
    assertEquals(0, juego.getNumeroCeldasVacias()); // Si hay 0 celdas vacías el juego terminó
  }

  // Criterio de aceptación 13.1
  @Test
  public void testAutoJuegoGeneralCompletoComputadoraAzulComputadoraRoja() {
    juego = new AutoJuegoGeneral(3, TipoJugador.COMPUTADORA, TipoJugador.COMPUTADORA);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);
    juego.realizarMovimiento(0, 0, Celda.S); // Sirve para iniciar el juego, la computadora
    // ignora la fila y columna y realiza un movimiento aleatorio
    assertEquals(0, juego.getNumeroCeldasVacias()); // Si hay 0 celdas vacías el juego terminó
  }

  // Criterio de aceptación 14.1
  @Test
  public void testGuardarAutoJuegoGeneral() {
    Random random = new Random();
    Celda celda;

    juego.setJuegoDebeGuardarse(true);
    juego.setEstadoJuego(EstadoJuego.JUGANDO);

    while (juego.getEstadoJuego() == EstadoJuego.JUGANDO) {
      celda = random.nextInt(2) == 0 ? Celda.S : Celda.O;
      outer:
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (juego.getCelda(i, j) == Celda.VACIA) {
            juego.realizarMovimiento(i, j, celda);
            break outer;
          }
        }
      }
    }
    StringBuilder juegoleido = new StringBuilder();
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader("juegoGuardado.txt"));
      String linea;
      while ((linea = bufferedReader.readLine()) != null) {
        juegoleido.append(linea).append("\n");
      }
    } catch (FileNotFoundException e) {
      System.out.println("Archivo no encontrado");
    } catch (IOException e) {
      System.out.println("Falla al leer línea");
    }
    assertEquals(juego.getJuegoGuardado().toString(), juegoleido.toString());
  }
}
