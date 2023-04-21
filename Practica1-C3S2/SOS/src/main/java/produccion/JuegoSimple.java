package produccion;

public class JuegoSimple {
  protected static int totalFilas = 8;
  protected static int totalColumnas = 8;
  public enum Celda {
    VACIA, S, O
  }
  protected Celda[][] tablero;
  public enum Turno {
    ROJO, AZUL
  }
  protected Turno turno;
  public enum EstadoJuego {
    JUGANDO, EMPATE, GANO_S, GANO_O
  }
  protected EstadoJuego estadoJuegoActual;
  public JuegoSimple(int tamanio) {
    setTamanioTablero(tamanio);
    tablero = new Celda[totalFilas][totalColumnas];
    iniciarJuego();
  }
  public boolean setTamanioTablero(int tamanio){
    if(tamanio > 2 && tamanio <= 20){
      totalFilas = tamanio;
      totalColumnas = tamanio;
      return true;
    }
    return false;
  }
  public int getTamanioTablero(){
    return totalFilas;
  }
  private void iniciarJuego() {
    for (int fila = 0; fila < totalFilas; ++fila) {
      for (int col = 0; col < totalColumnas; ++col) {
        tablero[fila][col] = Celda.VACIA;
      }
    }
    estadoJuegoActual = EstadoJuego.JUGANDO;
    turno = Turno.ROJO;
  }
  public void resetearJuego() {
    iniciarJuego();
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
  public Turno getTurno() {
    return turno;
  }

}
