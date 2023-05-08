package produccion;

import produccion.JuegoSimple.Celda;

public interface AutoJuego {
    TipoJugador getTipoJugador(String jugador);
    boolean realizarAutoMovimiento(Celda celda);
    boolean realizaJugadaSos();
    void realizarMovimientoAleatorio(Celda celda);
    int getNumeroCeldasVacias();
}
