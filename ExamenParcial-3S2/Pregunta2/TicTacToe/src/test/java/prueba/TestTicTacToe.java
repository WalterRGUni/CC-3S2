package prueba;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produccion.TicTacToe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
