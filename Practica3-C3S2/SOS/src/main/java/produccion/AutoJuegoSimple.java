package produccion;

import java.util.Random;

public class AutoJuegoSimple extends JuegoSimple implements AutoJuego {

  private TipoJugador jugadorAzul; // Tipo de jugador(Computadora o Humano)
  private TipoJugador jugadorRojo; // Tipo de jugador(Computadora o Humano)
  private final int AUTO_TIEMPO = 700; // Tiempo de respuesta de las jugadas de la computadora

  public AutoJuegoSimple(int tamanio, TipoJugador jugadorAzul, TipoJugador jugadorRojo) {
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
   * Realiza un movimiento en un AutoJuegoSimple
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
      int ladoTablero = getGui().getLadoTablero();
      System.out.println("ladoTablero: " + ladoTablero);
      while (getEstadoJuego() == EstadoJuego.JUGANDO) {
        realizarAutoMovimiento();
        if (isPruebas()) {
          continue;
        }
        getGui().getPanelCentral().paintImmediately(0, 0, ladoTablero, ladoTablero);
        try {
          Thread.sleep(AUTO_TIEMPO);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    } else if (getTurno() == Turno.AZUL && jugadorAzul == TipoJugador.COMPUTADORA) {
      realizarAutoMovimiento();
    } else if (getTurno() == Turno.ROJO && jugadorRojo == TipoJugador.COMPUTADORA) {
      realizarAutoMovimiento();
    } else if (getTurno() == Turno.ROJO && jugadorRojo == TipoJugador.HUMANO) {
      super.realizarMovimiento(fila, columna, celda);
      if(isPruebas()){
        realizarAutoMovimiento();
      } else {
        int ladoTablero = getGui().getLadoTablero();
        getGui().getPanelCentral().paintImmediately(0, 0, ladoTablero, ladoTablero);
        try {
          Thread.sleep(AUTO_TIEMPO);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        realizarAutoMovimiento();
      }
    } else if (getTurno() == Turno.AZUL && jugadorAzul == TipoJugador.HUMANO) {
      super.realizarMovimiento(fila, columna, celda);
      if(isPruebas()){
        realizarAutoMovimiento();
      } else {
        int ladoTablero = getGui().getLadoTablero();
        getGui().getPanelCentral().paintImmediately(0, 0, ladoTablero, ladoTablero);
        try {
          Thread.sleep(AUTO_TIEMPO);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        realizarAutoMovimiento();
      }
    }
  }

  /**
   * Intenta realizar una jugada ganadora y si no puede realiza un movimiento aleatorio
   *
   * @return true (solo para cumplir con la interfaz AutoJuego)
   */
  @Override
  public boolean realizarAutoMovimiento() {
    if (!realizarJugadaGanadora()) {
      realizarMovimientoAleatorio();
    }
    return true;
  }

  /**
   * Verifica en todo el tablero si puede hacer SOS y en ese caso lo realiza
   *
   * @return true si realizó SOS o false en caso contrario
   */
  public boolean realizarJugadaGanadora() {
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
    Celda celda = random.nextInt(2) == 0 ? Celda.O : Celda.S;
    int numeroCeldasVacias = getNumeroCeldasVacias();
    if (numeroCeldasVacias > 0) {
      int movimientoObjetivo = random.nextInt(numeroCeldasVacias);
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
