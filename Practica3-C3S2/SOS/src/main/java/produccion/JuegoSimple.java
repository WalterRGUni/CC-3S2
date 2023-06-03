package produccion;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Representa un juego simple SOS.
 */
public class JuegoSimple {

  private int totalFilas;
  private int totalColumnas;
  private final List<LineaSos> lineasSos = new ArrayList<>();
  private Celda[][] tablero;
  private Turno turno = Turno.AZUL;
  private EstadoJuego estadoJuegoActual = EstadoJuego.JUGANDO;
  ;
  protected boolean juegoDebeGuardarse = false;
  private final StringBuilder juegoGuardado = new StringBuilder();
  private JPanel panelCentral;
  private boolean pruebas =false;

  public JuegoSimple(int tamanio) {
    setTablero(tamanio);
  }

  public void guardarJuego() {
    try (PrintWriter out = new PrintWriter("juegoGuardado.txt")) {
      out.print(juegoGuardado);
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public boolean isPruebas() {
    return pruebas;
  }

  public void setPruebas(boolean pruebas) {
    this.pruebas = pruebas;
  }

  public JPanel getPanelCentral() {
    return panelCentral;
  }

  public void setPanelCentral(JPanel panelCentral) {
    this.panelCentral = panelCentral;
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

  public int getTotalFilas() {
    return totalFilas;
  }

  public int getTotalColumnas() {
    return totalColumnas;
  }

  public List<LineaSos> getLineasSos() {
    return lineasSos;
  }

  public void setTablero(int tamanio) {
    if (tamanio > 2 && tamanio <= 20) {
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

  public void setTurno(Turno turno) {
    this.turno = turno;
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
   * Llena la celda en la fila y columna seleccionada con el valor dado por valor Celda
   *
   * @param fila       fila de celda en la que se realiza el movimiento
   * @param columna    columna de celda en la que se realiza el movimiento
   * @param valorCelda valor 'S' u 'O' que se pondrá en la celda
   */
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas
        && getCelda(fila, columna) == Celda.VACIA) {
      setCelda(fila, columna, valorCelda);
      if (juegoDebeGuardarse) {
        guardarJugada(fila, columna, valorCelda);
      }
      actualizarEstadoJuego(fila, columna);
      if (getEstadoJuego() == EstadoJuego.JUGANDO) {
        setTurno((getTurno() == Turno.ROJO) ? Turno.AZUL : Turno.ROJO);
      } else if (juegoDebeGuardarse) {
        guardarJuego();
      }
    }
  }

  public void guardarJugada(int fila, int columna, Celda valorCelda) {
    juegoGuardado.append(String.format("%s: (%d, %d) -> %s%n", turno, fila, columna, valorCelda));
  }

  /**
   * Actualiza el estado de juego según haya ganado el azul, rojo o haya un empate
   *
   * @param fila    fila del movimiento actual
   * @param columna columna del movimiento actual
   */
  public void actualizarEstadoJuego(int fila, int columna) {
    if (hizoSos(fila, columna)) {
      //ganador = getTurno();
      estadoJuegoActual = (turno == Turno.ROJO) ? EstadoJuego.GANO_ROJO : EstadoJuego.GANO_AZUL;
    } else if (esEmpate()) {
      estadoJuegoActual = EstadoJuego.EMPATE;
    }
  }

  /**
   * Este método se llama después de verificar que no hubo SOS Verifica que no hay más celdas vacías
   * y por lo tanto es empate
   *
   * @return true cuando hay empate
   */
  public boolean esEmpate() {
    for (int fila = 0; fila < totalFilas; ++fila) {
      for (int col = 0; col < totalColumnas; ++col) {
        if (tablero[fila][col] == Celda.VACIA) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Verifica si el movimiento actual forma uno o más SOS, los añade a la lista "lineasSos"
   *
   * @param fila fila del movimiento actual
   * @param col  columna del movimiento actual
   * @return true si se ha formado uno o mas SOS, caso contrario retorna false
   */
  public boolean hizoSos(int fila, int col) {
    if (getCelda(fila, col) == Celda.S) {
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col - 2, fila);
        return true;
      }
      if (col < getColumnasTotales() - 2 && getCelda(fila, col + 1) == Celda.O
          && getCelda(fila, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col + 2, fila);
        return true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        aniadirLineaSos(col, fila, col, fila - 2);
        return true;
      }
      if (fila < getFilasTotales() - 2 && getCelda(fila + 1, col) == Celda.O
          && getCelda(fila + 2, col) == Celda.S) {
        aniadirLineaSos(col, fila, col, fila + 2);
        return true;
      }
      if (fila > 1 && col > 1 && getCelda(fila - 1, col - 1) == Celda.O
          && getCelda(fila - 2, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col - 2, fila - 2);
        return true;
      }
      if (fila > 1 && col < getColumnasTotales() - 2 && getCelda(fila - 1, col + 1) == Celda.O
          && getCelda(fila - 2, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col + 2, fila - 2);
        return true;
      }
      if (fila < getFilasTotales() - 2 && col > 1 && getCelda(fila + 1, col - 1) == Celda.O
          && getCelda(fila + 2, col - 2) == Celda.S) {
        aniadirLineaSos(col, fila, col - 2, fila + 2);
        return true;
      }
      if (fila < getFilasTotales() - 2 && col < getColumnasTotales() - 2
          && getCelda(fila + 1, col + 1) == Celda.O && getCelda(fila + 2, col + 2) == Celda.S) {
        aniadirLineaSos(col, fila, col + 2, fila + 2);
        return true;
      }
    }
    if (getCelda(fila, col) == Celda.O) {
      if (col > 0 && col < getColumnasTotales() - 1 && getCelda(fila, col - 1) == Celda.S
          && getCelda(fila, col + 1) == Celda.S) {
        aniadirLineaSos(col - 1, fila, col + 1, fila);
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && getCelda(fila - 1, col) == Celda.S
          && getCelda(fila + 1, col) == Celda.S) {
        aniadirLineaSos(col, fila - 1, col, fila + 1);
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col - 1) == Celda.S && getCelda(fila + 1, col + 1) == Celda.S) {
        aniadirLineaSos(col - 1, fila - 1, col + 1, fila + 1);
        return true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col + 1) == Celda.S && getCelda(fila + 1, col - 1) == Celda.S) {
        aniadirLineaSos(col + 1, fila - 1, col - 1, fila + 1);
        return true;
      }
    }
    return false;
  }

  public void aniadirLineaSos(int col1, int fil1, int col2, int fil2) {
    lineasSos.add(
        new LineaSos(col1, fil1, col2, fil2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
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