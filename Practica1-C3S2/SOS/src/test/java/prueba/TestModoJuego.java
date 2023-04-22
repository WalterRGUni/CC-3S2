package prueba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.JuegoGeneral;
import produccion.JuegoSimple;
import produccion.SosGui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestModoJuego {
    // Criterio de aceptación 2.1
    @Test
    public void testCambioModoJuegoSimpleAGeneral() {
        SosGui juego = new SosGui(new JuegoSimple(8));
        juego.setTipoJuego(new JuegoGeneral(8));
        assertTrue(juego.getTipoJuego().getClass() == JuegoGeneral.class);
    }

    @Test
    public void testCambioModoJuegoGeneralASimple() {
        SosGui juego = new SosGui(new JuegoGeneral(8));
        juego.setTipoJuego(new JuegoSimple(8));
        assertTrue(juego.getTipoJuego().getClass() == JuegoSimple.class);
    }
}
