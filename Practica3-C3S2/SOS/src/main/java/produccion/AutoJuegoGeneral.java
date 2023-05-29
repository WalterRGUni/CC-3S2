package produccion;

import java.util.Random;

public class AutoJuegoGeneral extends JuegoGeneral implements AutoJuego {

  private TipoJugador jugadorAzul; // Tipo de jugador(Computadora o Humano)
  private TipoJugador jugadorRojo; // Tipo de jugador(Computadora o Humano)

  public AutoJuegoGeneral(int tamanio, TipoJugador jugadorAzul, TipoJugador jugadorRojo) {
    super(tamanio);
    this.jugadorAzul = jugadorAzul;
    this.jugadorRojo = jugadorRojo;
  }

  @Override
  public TipoJugador getTipoJugador(String jugador) {
    if (jugador.equals("Azul")) {
      return jugadorAzul;
    } else if (jugador.equals("Rojo")) {
      return jugadorRojo;
    } else {
      return null;
    }
  }

  /**
   * Realiza un movimiento en un AutoJuegoGeneral
   *
   * @param fila    fila de celda en la que se realiza el movimiento
   * @param columna columna de celda en la que se realiza el movimiento
   * @param celda   valor 'S' u 'O' que se pondrá en la celda
   */
  @Override
  public void realizarMovimiento(int fila, int columna, Celda celda) {
    // Si aun no inicia el juego no realiza movimiento
    if (getEstadoJuego() != EstadoJuego.JUGANDO) {
      return;
    }
    // Si ambos jugadores son la computadora continúa realizando jugadas hasta que ya no haya celdas vacías
    if (jugadorAzul == TipoJugador.COMPUTADORA && jugadorRojo == TipoJugador.COMPUTADORA) {
      while (getEstadoJuego() == EstadoJuego.JUGANDO) {
        realizarAutoMovimiento();
      }
    } else if (getTurno() == Turno.AZUL && jugadorAzul == TipoJugador.COMPUTADORA) {
      while (realizarAutoMovimiento()) { // Si la computadora hace SOS, continúa jugando
        realizarAutoMovimiento();
      }
    } else if (getTurno() == Turno.ROJO && jugadorRojo == TipoJugador.COMPUTADORA) {
      while (realizarAutoMovimiento()) { // Si la computadora hace SOS, continúa jugando
        realizarAutoMovimiento();
      }
    } else if (getTurno() == Turno.AZUL && jugadorAzul == TipoJugador.HUMANO) {
      super.realizarMovimiento(fila, columna, celda);
      if(!hizoSos(fila, columna)){
        while (realizarAutoMovimiento()) { // Si la computadora hace SOS, continúa jugando
          realizarAutoMovimiento();
        }
      }
    } else if (getTurno() == Turno.ROJO && jugadorRojo == TipoJugador.HUMANO) {
      super.realizarMovimiento(fila, columna, celda);
      if(!hizoSos(fila, columna)){
        while (realizarAutoMovimiento()) { // Si la computadora hace SOS, continúa jugando
          realizarAutoMovimiento();
        }
      }
    }
  }

  /**
   * Intenta realizar una jugada SOS y si no puede realiza un movimiento aleatorio
   *
   * @return true si realizó una jugada SOS y false si hizo un movimiento aleatorio
   */
  @Override
  public boolean realizarAutoMovimiento() {
    if (!realizaJugadaSos()) {
      realizarMovimientoAleatorio();
      return false;
    }
    return true;
  }

  /**
   * Verifica en todo el tablero si al insertar un valor realizará SOS
   *
   * @return true si realiza SOS o false en caso contrario
   */
  public boolean realizaJugadaSos() {
    for (int i = 0; i < getTotalFilas(); i++) {
      for (int j = 0; j < getTotalColumnas(); j++) {
        if (getCelda(i, j) == Celda.VACIA) {
          setCelda(i, j, Celda.S);
          if (hizoSos(i, j)) {
            setCelda(i, j, Celda.VACIA);
            super.realizarMovimiento(i, j, Celda.S);
            return true;
          }
          setCelda(i, j, Celda.O);
          if (hizoSos(i, j)) {
            setCelda(i, j, Celda.VACIA);
            super.realizarMovimiento(i, j, Celda.O);
            return true;
          }
          setCelda(i, j, Celda.VACIA);
        }
      }
    }
    return false;
  }

  @Override
  public void realizarMovimientoAleatorio() {
    Random random = new Random();
    Celda celda = random.nextInt(2) == 0 ? Celda.S : Celda.O;
    int numeroCeldas = getNumeroCeldasVacias();
    if (numeroCeldas > 0) {
      int movimientoObjetivo = random.nextInt(numeroCeldas);
      int index = 0;
      for (int fil = 0; fil < getTotalFilas(); ++fil) {
        for (int col = 0; col < getTotalColumnas(); ++col) {
          if (getCelda(fil, col) == Celda.VACIA) {
            if (movimientoObjetivo == index) {
              super.realizarMovimiento(fil, col, celda);
              return;
            } else {
              index++;
            }
          }
        }
      }
    }
  }
}
