package produccion;

import java.util.Random;

public class AutoJuegoGeneral extends JuegoGeneral {
    TipoJugador jugadorAzul;
    TipoJugador jugadorRojo;

    public AutoJuegoGeneral(int tamanio, TipoJugador jugadorAzul, TipoJugador jugadorRojo) {
        super(tamanio);
        this.jugadorAzul = jugadorAzul;
        this.jugadorRojo = jugadorRojo;
    }

    public TipoJugador getTipoJugador(String jugador) {
        if(jugador.equals("Azul")) {
            return jugadorAzul;
        } else if(jugador.equals("Rojo")){
            return jugadorRojo;
        } else {
            return null;
        }
    }

    @Override
    public void realizarMovimiento(int fila, int columna, Celda celda) {
        if (getEstadoJuego() != EstadoJuego.JUGANDO) {
            return;
        }
        if (jugadorAzul == TipoJugador.COMPUTADORA && jugadorRojo == TipoJugador.COMPUTADORA) {
            while (getEstadoJuego() == EstadoJuego.JUGANDO) {
                if (getTurno() == Turno.AZUL) {
                    realizarAutoMovimiento(getCeldaJugadorAzul());
                } else if (getTurno() == Turno.ROJO) {
                    realizarAutoMovimiento(getCeldaJugadorRojo());
                }
            }
        } else if (getTurno() == Turno.AZUL && jugadorAzul == TipoJugador.COMPUTADORA) {
            realizarAutoMovimiento(celda);
            while (hizoSos(fila, columna)) {
                realizarAutoMovimiento(celda);
            }
        } else if (getTurno() == Turno.ROJO && jugadorRojo == TipoJugador.COMPUTADORA) {
            realizarAutoMovimiento(celda);
            while (hizoSos(fila, columna)) {
                realizarAutoMovimiento(celda);
            }
        } else if (getTurno() == Turno.ROJO && jugadorAzul == TipoJugador.COMPUTADORA) {
            super.realizarMovimiento(fila, columna, getCeldaJugadorRojo());
            if(!hizoSos(fila, columna)) {
                while(realizarAutoMovimiento(getCeldaJugadorAzul())){
                }
            }
        } else if (getTurno() == Turno.AZUL && jugadorRojo == TipoJugador.COMPUTADORA) {
            super.realizarMovimiento(fila, columna, getCeldaJugadorAzul());
            if(!hizoSos(fila, columna)) {
                while(realizarAutoMovimiento(getCeldaJugadorRojo())){
                }
            }
        }
    }

    public boolean realizarAutoMovimiento(Celda celda) {
        if (!realizaJugadaSos()) {
            realizarMovimientoAleatorio(celda);
            return false;
        }
        return true;
    }


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

    public void realizarMovimientoAleatorio(Celda celda) {
        Random random = new Random();
        int numeroCeldas = getNumeroCeldasVacias();
        if(numeroCeldas > 0) {
            int movimientoObjetivo = random.nextInt(numeroCeldas);
            int index = 0;
            for (int fil = 0; fil < getTotalFilas(); ++fil) {
                for (int col = 0; col < getTotalColumnas(); ++col) {
                    if (getCelda(fil, col) == Celda.VACIA) {
                        if (movimientoObjetivo == index) {
                            super.realizarMovimiento(fil, col, celda);
                            return;
                        } else
                            index++;
                    }
                }
            }
        }
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
