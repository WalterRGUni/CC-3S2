package produccion;

import static produccion.Guardado.guardarJuego;
import static produccion.Guardado.guardarJugada;

public class JuegoGeneral extends JuegoSimple {

  private int puntosJugadorAzul; // puntos del jugador azul
  private int puntosJugadorRojo; // puntos del jugador rojo

  public JuegoGeneral(int tamanioTablero) {
    super(tamanioTablero);
    this.puntosJugadorAzul = 0;
    this.puntosJugadorRojo = 0;
  }

  /**
   * Llena la celda en la fila y columna seleccionada con el valor dado por valor Celda
   *
   * @param fila fila de celda en la que se realiza el movimiento
   * @param columna columna de celda en la que se realiza el movimiento
   * @param valorCelda valor 'S' u 'O' que se pondrá en la celda
   */
  @Override
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (isCeldaValida(fila, columna)) {
      setCelda(fila, columna, valorCelda);
      if (juegoDebeGuardarse()) {
        guardarJugada(fila, columna, valorCelda, getTurno(), getJuegoGuardado());
      }
      actualizarEstadoJuego(fila, columna);
      if (hizoSos(fila, columna)) {
        actualizarPuntos();
      } else if (getEstadoJuego() == EstadoJuego.JUGANDO) {
        cambiarTurno();
      } else if (juegoDebeGuardarse()) {
        guardarJuego(getJuegoGuardado());
      }
    }
  }

  /**
   * Actualiza el estado de juego según haya ganado el azul, rojo o haya un empate
   *
   * @param fila fila del movimiento actual
   * @param columna columna del movimiento actual
   */
  @Override
  public void actualizarEstadoJuego(int fila, int columna) {
    boolean juegoTermino = true;
    for (int fil = 0; fil < getTotalFilas(); ++fil) {
      for (int col = 0; col < getTotalColumnas(); ++col) {
        if (getCelda(fil, col) == Celda.VACIA) {
          juegoTermino = false;
        }
      }
    }
    if (juegoTermino) {
      if (puntosJugadorAzul > puntosJugadorRojo) {
        setEstadoJuego(EstadoJuego.GANO_AZUL);
      } else if (puntosJugadorRojo > puntosJugadorAzul) {
        setEstadoJuego(EstadoJuego.GANO_ROJO);
      } else {
        setEstadoJuego(EstadoJuego.EMPATE);
      }
    }
  }

  /** Incrementa un punto al jugador que hizo SOS */
  public void actualizarPuntos() {
    if (getTurno() == Turno.AZUL) {
      puntosJugadorAzul++;
    } else {
      puntosJugadorRojo++;
    }
  }
}