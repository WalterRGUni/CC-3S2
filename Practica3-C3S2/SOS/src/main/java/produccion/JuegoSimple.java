package produccion;

import static produccion.Guardado.guardarJuego;
import static produccion.Guardado.guardarJugada;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/** Representa un juego simple SOS. */
public class JuegoSimple {

  private int totalFilas; // número de filas del tablero
  private int totalColumnas; // número de columnas del tablero
  private final List<LineaSos> lineasSos = new ArrayList<>();
  private Celda[][] tablero;
  private Turno turno = Turno.AZUL;
  private EstadoJuego estadoJuegoActual = EstadoJuego.JUGANDO;
  private boolean juegoDebeGuardarse = false;
  private StringBuilder juegoGuardado = new StringBuilder(); // Guarda las jugadas
  private SosGui gui; // GUI asociado a este juego
  private boolean pruebas = false; // indica si se está ejecutando pruebas
  private final int TAMANIO_MINIMO_TABLERO = 2; // número mínimo de filas o columnas de un tablero
  private final int TAMANIO_MAXIMO_TABLERO = 20; // número máximo de filas o columnas de un tablero

  public JuegoSimple(int tamanioTablero) {
    setTablero(tamanioTablero);
  }

  /**
   * indica si se está ejecutando pruebas
   *
   * @return true si se está ejecutando pruebas o false en caso contrario
   */
  public boolean isPruebas() {
    return pruebas;
  }

  public void setPruebas(boolean pruebas) {
    this.pruebas = pruebas;
  }

  public SosGui getGui() {
    return gui;
  }

  public void setGui(SosGui gui) {
    this.gui = gui;
  }

  public StringBuilder getJuegoGuardado() {
    return juegoGuardado;
  }

  public void appendJuegoGuardado(String texto) {
    juegoGuardado.append(texto);
  }

  public void setJuegoDebeGuardarse(boolean juegoDebeGuardarse) {
    this.juegoDebeGuardarse = juegoDebeGuardarse;
  }

  public boolean juegoDebeGuardarse() {
    return juegoDebeGuardarse;
  }

  public int getTotalFilas() {
    return totalFilas;
  }

  public int getTotalColumnas() {
    return totalColumnas;
  }

  public List<LineaSos> getLineasSos() {
    return lineasSos;
  }

  /**
   * Inicializa el tablero con celdas vacías
   *
   * @param tamanio número de filas o columnas del tablero
   */
  public void setTablero(int tamanio) {
    if (tamanio > TAMANIO_MINIMO_TABLERO && tamanio <= TAMANIO_MAXIMO_TABLERO) {
      tablero = new Celda[tamanio][tamanio];
      totalFilas = tamanio;
      totalColumnas = tamanio;
      for (int fila = 0; fila < totalFilas; ++fila) {
        for (int col = 0; col < totalColumnas; ++col) {
          tablero[fila][col] = Celda.VACIA;
        }
      }
    }
  }

  public int getTamanioTablero() {
    return totalFilas;
  }

  public Turno getTurno() {
    return turno;
  }

  public int getFilasTotales() {
    return totalFilas;
  }

  public int getColumnasTotales() {
    return totalColumnas;
  }

  public Celda getCelda(int fila, int columna) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas) {
      return tablero[fila][columna];
    } else {
      return null;
    }
  }

  public void setCelda(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas) {
      tablero[fila][columna] = valorCelda;
    }
  }

  /**
   * Realiza un movimiento del juego
   *
   * @param fila fila de celda en la que se realiza el movimiento
   * @param columna columna de celda en la que se realiza el movimiento
   * @param valorCelda valor 'S' u 'O' que se pondrá en la celda
   */
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (isCeldaValida(fila, columna)) {
      setCelda(fila, columna, valorCelda);
      if (juegoDebeGuardarse) {
        guardarJugada(fila, columna, valorCelda, turno, juegoGuardado);
      }
      actualizarEstadoJuego(fila, columna);
      if (getEstadoJuego() == EstadoJuego.JUGANDO) {
        cambiarTurno();
      } else if (juegoDebeGuardarse) {
        guardarJuego(juegoGuardado);
      }
    }
  }

  public boolean isCeldaValida(int fila, int columna) {
    return fila >= 0
        && fila < totalFilas
        && columna >= 0
        && columna < totalColumnas
        && getCelda(fila, columna) == Celda.VACIA;
  }

  /** Cambia de turno de rojo a azul y viceversa */
  public void cambiarTurno() {
    turno = (getTurno() == Turno.ROJO) ? Turno.AZUL : Turno.ROJO;
  }

  /**
   * Actualiza el estado de juego según haya ganado el azul, rojo o haya un empate
   *
   * @param fila fila del movimiento actual
   * @param columna columna del movimiento actual
   */
  public void actualizarEstadoJuego(int fila, int columna) {
    if (hizoSos(fila, columna)) {
      estadoJuegoActual = (turno == Turno.ROJO) ? EstadoJuego.GANO_ROJO : EstadoJuego.GANO_AZUL;
    } else if (getNumeroCeldasVacias() == 0) {
      estadoJuegoActual = EstadoJuego.EMPATE;
    }
  }

  /**
   * Verifica si el movimiento actual forma SOS en el tablero
   *
   * @param fila fila del movimiento actual
   * @param col columna del movimiento actual
   * @return true si el movimiento actual forma SOS en el tablero o false en caso contrario
   */
  public boolean hizoSos(int fila, int col) {
    return hizoSosConS(fila, col) || hizoSosConO(fila, col);
  }

  /**
   * Verifica si el movimiento actual colocó una S en el tablero y se formó SOS
   *
   * @param fila fila del movimiento actual
   * @param col columna del movimiento actual
   * @return true si el movimiento actual forma SOS en el tablero o false en caso contrario
   */
  public boolean hizoSosConS(int fila, int col) {
    boolean realizoSos = false;
    if (getCelda(fila, col) == Celda.S) {
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col - 2, fila});
        realizoSos = true;
      }
      if (col < getColumnasTotales() - 2
          && getCelda(fila, col + 1) == Celda.O
          && getCelda(fila, col + 2) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col + 2, fila});
        realizoSos = true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col, fila - 2});
        realizoSos = true;
      }
      if (fila < getFilasTotales() - 2
          && getCelda(fila + 1, col) == Celda.O
          && getCelda(fila + 2, col) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col, fila + 2});
        realizoSos = true;
      }
      if (fila > 1
          && col > 1
          && getCelda(fila - 1, col - 1) == Celda.O
          && getCelda(fila - 2, col - 2) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col - 2, fila - 2});
        realizoSos = true;
      }
      if (fila > 1
          && col < getColumnasTotales() - 2
          && getCelda(fila - 1, col + 1) == Celda.O
          && getCelda(fila - 2, col + 2) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col + 2, fila - 2});
        realizoSos = true;
      }
      if (fila < getFilasTotales() - 2
          && col > 1
          && getCelda(fila + 1, col - 1) == Celda.O
          && getCelda(fila + 2, col - 2) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col - 2, fila + 2});
        realizoSos = true;
      }
      if (fila < getFilasTotales() - 2
          && col < getColumnasTotales() - 2
          && getCelda(fila + 1, col + 1) == Celda.O
          && getCelda(fila + 2, col + 2) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila, col + 2, fila + 2});
        realizoSos = true;
      }
    }
    return realizoSos;
  }

  /**
   * Verifica si el movimiento actual colocó una O en el tablero y se formó SOS
   *
   * @param fila fila del movimiento actual
   * @param col columna del movimiento actual
   * @return true si el movimiento actual forma SOS en el tablero o false en caso contrario
   */
  public boolean hizoSosConO(int fila, int col) {
    if (getCelda(fila, col) == Celda.O) {
      if (col > 0
          && col < getColumnasTotales() - 1
          && getCelda(fila, col - 1) == Celda.S
          && getCelda(fila, col + 1) == Celda.S) {
        aniadirLineaSos(new int[] {col - 1, fila, col + 1, fila});
        return true;
      }
      if (fila > 0
          && fila < getFilasTotales() - 1
          && getCelda(fila - 1, col) == Celda.S
          && getCelda(fila + 1, col) == Celda.S) {
        aniadirLineaSos(new int[] {col, fila - 1, col, fila + 1});
        return true;
      }
      if (fila > 0
          && fila < getFilasTotales() - 1
          && col > 0
          && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col - 1) == Celda.S
          && getCelda(fila + 1, col + 1) == Celda.S) {
        aniadirLineaSos(new int[] {col - 1, fila - 1, col + 1, fila + 1});
        return true;
      }
      if (fila > 0
          && fila < getFilasTotales() - 1
          && col > 0
          && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col + 1) == Celda.S
          && getCelda(fila + 1, col - 1) == Celda.S) {
        aniadirLineaSos(new int[] {col + 1, fila - 1, col - 1, fila + 1});
        return true;
      }
    }
    return false;
  }

  /**
   * Añade una linea SOS a la lista lineasSos
   *
   * @param coord arreglo que contiene las filas y columnas de las celdas en la que están los puntos
   *     extremos de la línea SOS en el orden: fila celda1, columna celda1, fila celda2, columna
   *     celda2.
   */
  public void aniadirLineaSos(int[] coord) {
    Color color = getTurno() == Turno.ROJO ? Color.RED : Color.BLUE;
    lineasSos.add(new LineaSos(coord, color));
  }

  public EstadoJuego getEstadoJuego() {
    return estadoJuegoActual;
  }

  public void setEstadoJuego(EstadoJuego estadoJuegoActual) {
    this.estadoJuegoActual = estadoJuegoActual;
  }

  public int getNumeroCeldasVacias() {
    int numeroCeldasVacias = 0;
    for (int fil = 0; fil < getTotalFilas(); ++fil) {
      for (int col = 0; col < getTotalColumnas(); ++col) {
        if (getCelda(fil, col) == Celda.VACIA) {
          numeroCeldasVacias++;
        }
      }
    }
    return numeroCeldasVacias;
  }
}
