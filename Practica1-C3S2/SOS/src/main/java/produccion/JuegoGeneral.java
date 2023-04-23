package produccion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class JuegoGeneral extends JuegoSimple {

  private int numeroSosAzul;
  private int numeroSosRojo;

  public JuegoGeneral(int tamanio) {
    super(tamanio);
    this.numeroSosAzul = 0;
    this.numeroSosRojo = 0;
    this.lineasSos = new ArrayList<>();
  }

  @Override
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas
        && tablero[fila][columna] == Celda.VACIA) {
      tablero[fila][columna] = valorCelda;
      actualizarEstadoJuego(fila, columna);
      if (hizoSos(fila, columna)) {
        if (getTurno() == Turno.AZUL) {
          System.out.println("Sos azul: " + numeroSosAzul);
        } else {
          System.out.println("Sos rojo: " + numeroSosRojo);
        }
      } else if (getEstadoJuego() == EstadoJuego.JUGANDO) {
        turno = (turno == Turno.ROJO) ? Turno.AZUL : Turno.ROJO;
      }
    }
  }

  @Override
  public void actualizarEstadoJuego(int fila, int columna) {
    boolean juegoTermino = true;
    for (int fil = 0; fil < totalFilas; ++fil) {
      for (int col = 0; col < totalColumnas; ++col) {
        if (tablero[fil][col] == Celda.VACIA) {
          juegoTermino = false;
        }
      }
    }
    if(juegoTermino){
      if(numeroSosAzul > numeroSosRojo) {
        estadoJuegoActual = EstadoJuego.GANO_AZUL;
      } else if(numeroSosRojo > numeroSosAzul) {
        estadoJuegoActual = EstadoJuego.GANO_ROJO;
      } else {
        estadoJuegoActual = EstadoJuego.EMPATE;
      }
    }
  }

  @Override
  public boolean hizoSos(int fila, int col) {
    if (getCelda(fila, col) == Celda.S) {
      boolean b = false;
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col - 2, fila, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (col < getColumnasTotales() - 2 && getCelda(fila, col + 1) == Celda.O && getCelda(fila,
          col + 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col + 2, fila, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col, fila - 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila < getFilasTotales() - 2 && getCelda(fila + 1, col) == Celda.O && getCelda(fila + 2,
          col) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col, fila + 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila > 1 && col > 1 && getCelda(fila - 1, col - 1) == Celda.O
          && getCelda(fila - 2, col - 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col - 2, fila - 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila > 1 && col < getColumnasTotales() - 2 && getCelda(fila - 1, col + 1) == Celda.O
          && getCelda(fila - 2, col + 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col + 2, fila - 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila < getFilasTotales() - 2 && col > 1 && getCelda(fila + 1, col - 1) == Celda.O
          && getCelda(fila + 2, col - 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col - 2, fila + 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila < getFilasTotales() - 2 && col < getColumnasTotales() - 2 && getCelda(fila + 1,
          col + 1) == Celda.O && getCelda(fila + 2, col + 2) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila, col + 2, fila + 2, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if(b) return b;
    }
    if (getCelda(fila, col) == Celda.O) {
      boolean bo = false;
      if (col > 0 && col < getColumnasTotales() - 1 && getCelda(fila, col - 1) == Celda.S
          && getCelda(fila, col + 1) == Celda.S) {
        lineasSos.add(new LineaSos(col - 1, fila, col + 1, fila, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && getCelda(fila - 1, col) == Celda.S
          && getCelda(fila + 1, col) == Celda.S) {
        lineasSos.add(new LineaSos(col, fila -1, col, fila+1, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col - 1) == Celda.S
          && getCelda(fila + 1, col + 1) == Celda.S) {
        lineasSos.add(new LineaSos(col-1, fila -1, col+1, fila+1, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col + 1) == Celda.S
          && getCelda(fila + 1, col - 1) == Celda.S) {
        lineasSos.add(new LineaSos(col+1, fila -1, col-1, fila+1, getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if(turno == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if(bo) return bo;
    }
    return false;
  }
}
