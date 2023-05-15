package prueba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.TicTacToe;

import static org.junit.jupiter.api.Assertions.*;

public class TestTicTacToe {
    TicTacToe ticTacToe;

    @BeforeEach
    public void setUp() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void givenTablero3x3WhenJuegasFueraTEjeXThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.jugar(5, 2);
        });
    }

    @Test
    public void givenTablero3x3WhenJuegasFueraEjeYThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.jugar(2, 5);
        });
    }

    @Test
    public void whenJugadaLugarOcupadoThenRunTimeException() {
        ticTacToe.jugar(1,1);
        assertThrows(RuntimeException.class, () -> {
            ticTacToe.jugar(1, 1);
        });
    }

    @Test
    public void whenIniciaJuegoThenXJuegaPrimero() {
        assertEquals("X", ticTacToe.proximoJugador());
    }

    @Test
    public void whenXJuegaThenProximoTurnoEsO() {
        ticTacToe.jugar(1,1); // juega X
        assertEquals("O", ticTacToe.proximoJugador());
    }

    @Test
    public void whenNoHay3EnLineaThenNoHayGanador() {
        ticTacToe.jugar(1, 1);
        assertEquals("No hay ganador", ticTacToe.getGanador());
    }

    @Test
    public void whenLineaHorizontalLlenaHayGanador() {
        ticTacToe.jugar(1, 1);
        ticTacToe.jugar(2, 1);
        ticTacToe.jugar(1, 2);
        ticTacToe.jugar(2, 2);
        ticTacToe.jugar(1, 3);
        assertEquals("X", ticTacToe.getGanador());
    }

    @Test
    public void whenLineaVerticalLlenaHayGanador() {
        ticTacToe.jugar(3, 3);
        ticTacToe.jugar(1, 2);
        ticTacToe.jugar(1, 1);
        ticTacToe.jugar(2, 2);
        ticTacToe.jugar(1, 3);
        ticTacToe.jugar(3, 2);
        assertEquals("O", ticTacToe.getGanador());
    }

    @Test
    public void whenLineaDiagonalSuperioIzquierdaInferiorDerechaLlenaHayGanador() {
        ticTacToe.jugar(1, 1);
        ticTacToe.jugar(1, 2);
        ticTacToe.jugar(2, 2);
        ticTacToe.jugar(2, 3);
        ticTacToe.jugar(3, 3);
        assertEquals("X", ticTacToe.getGanador());
    }

    @Test
    public void whenLineaDiagonalInferiorIzquierdaSuperiorDerechaLlenaHayGanador() {
        ticTacToe.jugar(1, 3);
        ticTacToe.jugar(1, 2);
        ticTacToe.jugar(2, 2);
        ticTacToe.jugar(2, 3);
        ticTacToe.jugar(3, 1);
        assertEquals("X", ticTacToe.getGanador());
    }

    @Test
    public void whenNoQuedanCasillasYNoHayGanadorThenEmpate() {
        ticTacToe.jugar(1, 1);
        ticTacToe.jugar(2, 2);
        ticTacToe.jugar(1, 3);
        ticTacToe.jugar(1, 2);
        ticTacToe.jugar(3, 2);
        ticTacToe.jugar(2, 3);
        ticTacToe.jugar(2, 1);
        ticTacToe.jugar(3, 1);
        ticTacToe.jugar(3, 3);

        assertTrue(ticTacToe.esEmpate());
    }
}
