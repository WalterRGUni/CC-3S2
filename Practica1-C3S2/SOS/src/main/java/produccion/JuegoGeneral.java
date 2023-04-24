package produccion;

import java.awt.Color;

public class JuegoGeneral extends JuegoSimple {

  private int numeroSosAzul;
  private int numeroSosRojo;

  public JuegoGeneral(int tamanio) {
    super(tamanio);
    this.numeroSosAzul = 0;
    this.numeroSosRojo = 0;
  }

  @Override
  public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
    if (fila >= 0 && fila < getTotalFilas() && columna >= 0 && columna < getTotalColumnas()
        && getCelda(fila, columna) == Celda.VACIA) {
      setCelda(fila, columna, valorCelda);
      if (!hizoSos(fila, columna) && getEstadoJuego() == EstadoJuego.JUGANDO) {
        setTurno((getTurno() == Turno.ROJO) ? Turno.AZUL : Turno.ROJO);
      }
      actualizarEstadoJuego(fila, columna);
    }
  }

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
      if (numeroSosAzul > numeroSosRojo) {
        setEstadoJuego(EstadoJuego.GANO_AZUL);
      } else if (numeroSosRojo > numeroSosAzul) {
        setEstadoJuego(EstadoJuego.GANO_ROJO);
      } else {
        setEstadoJuego(EstadoJuego.EMPATE);
      }
    }
  }

  @Override
  public boolean hizoSos(int fila, int col) {
    if (getCelda(fila, col) == Celda.S) {
      boolean b = false;
      if (col > 1 && getCelda(fila, col - 1) == Celda.O && getCelda(fila, col - 2) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col - 2, fila,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (col < getColumnasTotales() - 2 && getCelda(fila, col + 1) == Celda.O && getCelda(fila,
          col + 2) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col + 2, fila,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila > 1 && getCelda(fila - 1, col) == Celda.O && getCelda(fila - 2, col) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col, fila - 2,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila < getFilasTotales() - 2 && getCelda(fila + 1, col) == Celda.O && getCelda(fila + 2,
          col) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col, fila + 2,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila > 1 && col > 1 && getCelda(fila - 1, col - 1) == Celda.O
          && getCelda(fila - 2, col - 2) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col - 2, fila - 2,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila > 1 && col < getColumnasTotales() - 2 && getCelda(fila - 1, col + 1) == Celda.O
          && getCelda(fila - 2, col + 2) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col + 2, fila - 2,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila < getFilasTotales() - 2 && col > 1 && getCelda(fila + 1, col - 1) == Celda.O
          && getCelda(fila + 2, col - 2) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col - 2, fila + 2,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
      if (fila < getFilasTotales() - 2 && col < getColumnasTotales() - 2 && getCelda(fila + 1,
          col + 1) == Celda.O && getCelda(fila + 2, col + 2) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila, col + 2, fila + 2,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        b = true;
      }
        if (b) {
            return b;
        }
    }
    if (getCelda(fila, col) == Celda.O) {
      boolean bo = false;
      if (col > 0 && col < getColumnasTotales() - 1 && getCelda(fila, col - 1) == Celda.S
          && getCelda(fila, col + 1) == Celda.S) {
        getLineasSos().add(new LineaSos(col - 1, fila, col + 1, fila,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && getCelda(fila - 1, col) == Celda.S
          && getCelda(fila + 1, col) == Celda.S) {
        getLineasSos().add(new LineaSos(col, fila - 1, col, fila + 1,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col - 1) == Celda.S
          && getCelda(fila + 1, col + 1) == Celda.S) {
        getLineasSos().add(new LineaSos(col - 1, fila - 1, col + 1, fila + 1,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
      if (fila > 0 && fila < getFilasTotales() - 1 && col > 0 && col < getColumnasTotales() - 1
          && getCelda(fila - 1, col + 1) == Celda.S
          && getCelda(fila + 1, col - 1) == Celda.S) {
        getLineasSos().add(new LineaSos(col + 1, fila - 1, col - 1, fila + 1,
            getTurno() == Turno.ROJO ? Color.RED : Color.BLUE));
        if (getTurno() == Turno.AZUL) {
          numeroSosAzul++;
        } else {
          numeroSosRojo++;
        }
        bo = true;
      }
        if (bo) {
            return bo;
        }
    }
    return false;
  }
}
