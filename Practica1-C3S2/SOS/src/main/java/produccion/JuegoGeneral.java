package produccion;

public class JuegoGeneral extends JuegoSimple{
    private int numeroSosAzul;
    private int numeroSosRojo;
    public JuegoGeneral(int tamanio){
        super(tamanio);
        this.numeroSosAzul = 0;
        this.numeroSosRojo = 0;
    }

    @Override
    public void realizarMovimiento(int fila, int columna, Celda valorCelda) {
        if (fila >= 0 && fila < totalFilas && columna >= 0 && columna < totalColumnas && tablero[fila][columna] == Celda.VACIA) {
            tablero[fila][columna] = valorCelda;
            actualizarEstadoJuego(fila, columna);
            if (hizoSos(fila, columna)) {
                if(getTurno() == Turno.AZUL) {
                    numeroSosAzul++;
                    System.out.println("Sos azul: " + numeroSosAzul);
                } else {
                    numeroSosRojo++;
                    System.out.println("Sos rojo: " + numeroSosRojo);
                }
            } else if(getEstadoJuego() == EstadoJuego.JUGANDO){
                turno = (turno == Turno.ROJO) ? Turno.AZUL : Turno.ROJO;
            }
        }
    }

    @Override
    public boolean esEmpate() {
        for (int fila = 0; fila < totalFilas; ++fila) {
            for (int col = 0; col < totalColumnas; ++col) {
                if (tablero[fila][col] == Celda.VACIA) {
                    return false;
                }
            }
        }
        if(numeroSosAzul == numeroSosRojo){
            return true;
        }
        return false;
    }

    @Override
    public void actualizarEstadoJuego(int fila, int columna) {
        if (esEmpate()) {
            estadoJuegoActual = EstadoJuego.EMPATE;
        }
    }

}
