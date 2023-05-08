package prueba;

import org.junit.jupiter.api.Test;
import produccion.AutoJuegoSimple;
import produccion.JuegoSimple;
import produccion.JuegoSimple.EstadoJuego;
import produccion.TipoJugador;

import static org.junit.jupiter.api.Assertions.*;

public class TestAutoJuegoSimple {

    private AutoJuegoSimple juego;

    // Criterio de aceptación 9.1
    @Test
    public void testMovimientoComputadoraAutoJuegoSimple() {
        juego = new AutoJuegoSimple(3, TipoJugador.HUMANO, TipoJugador.COMPUTADORA);
        juego.setEstadoJuego(JuegoSimple.EstadoJuego.JUGANDO);
        juego.realizarMovimiento(1, 1, JuegoSimple.Celda.S); // movimiento Humano

        assertEquals(7, juego.getNumeroCeldasVacias()); // Celdas vacías = 9 - 1(humano) - 1(computador) = 7
    }

    // Criterio de aceptación 10.1
    @Test
    public void testAutoJuegoSimpleCompletoHumanoAzulComputadoraRojo() {
        juego = new AutoJuegoSimple(3, TipoJugador.HUMANO, TipoJugador.COMPUTADORA);
        juego.setEstadoJuego(JuegoSimple.EstadoJuego.JUGANDO);

        while(juego.getEstadoJuego() == JuegoSimple.EstadoJuego.JUGANDO) {
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(juego.getCelda(i, j) == JuegoSimple.Celda.VACIA) {
                        juego.realizarMovimiento(i, j, JuegoSimple.Celda.S);
                    }
                }
            }
        }
        assertTrue(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL ||
                juego.getEstadoJuego() == EstadoJuego.GANO_ROJO ||
                juego.getEstadoJuego() == EstadoJuego.EMPATE);
    }

    // Criterio de aceptación 12.1
    @Test
    public void testAutoJuegoSimpleCompletoComputadoraAzulComputadoraRoja() {
        juego = new AutoJuegoSimple(3, TipoJugador.COMPUTADORA, TipoJugador.COMPUTADORA);
        juego.setEstadoJuego(JuegoSimple.EstadoJuego.JUGANDO);
        juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S); // Sirve para iniciar el juego, la computadora
        // ignora la fila y columna y realiza un movimiento aleatorio
        assertTrue(juego.getEstadoJuego() == EstadoJuego.GANO_AZUL ||
                juego.getEstadoJuego() == EstadoJuego.GANO_ROJO ||
                juego.getEstadoJuego() == EstadoJuego.EMPATE);
    }

}
