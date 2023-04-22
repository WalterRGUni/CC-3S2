package prueba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.JuegoGeneral;
import produccion.JuegoSimple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJuegoGeneral {
    private JuegoGeneral juego;

    @BeforeEach
    public void setUp() {
        juego = new JuegoGeneral(3);
    }

    // Criterio de aceptación 6.1
    @Test
    public void testMovimientoQueNoFormeSos() {
        juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S);
        assertEquals(JuegoSimple.Celda.S, juego.getCelda(0, 0));
        assertTrue(juego.getTurno() == JuegoSimple.Turno.ROJO);
    }

    // Criterio de aceptación 6.2
    @Test
    public void testMovimientoFormaSos() {
        juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S);
        juego.realizarMovimiento(0, 1, JuegoSimple.Celda.O);
        juego.realizarMovimiento(0, 2, JuegoSimple.Celda.S);
        assertEquals(JuegoSimple.Celda.S, juego.getCelda(0, 2));
        assertTrue(juego.getTurno() == JuegoSimple.Turno.AZUL);
    }

    // Criterio de aceptación 6.3
    @Test
    public void testMovimientoNoValido() {
        juego.realizarMovimiento(0, 0, JuegoSimple.Celda.S);
        juego.realizarMovimiento(0, 0, JuegoSimple.Celda.O);
        assertEquals(JuegoSimple.Celda.S, juego.getCelda(0, 0));
        assertTrue(juego.getTurno() == JuegoSimple.Turno.ROJO);
    }
}
