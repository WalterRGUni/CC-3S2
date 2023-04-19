package prueba;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import produccion.Calculadora;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private static Calculadora calculadora;

    @BeforeAll
    public static void init() {
        calculadora = new Calculadora();
    }

    @Test
    public void whenCalculatorInitializedThenReturnTrue() {
        assertTrue(calculadora.getStatus());
    }

    @Test
    public void whenAdditionTwoNumberThenReturnCorrectAnswer() {
        assertEquals(5, calculadora.addition(2, 3));
    }

    @Test
    public void whenSubstractionTwoNumbersThenReturnCorrectAnswer() {
        assertEquals(5, calculadora.substraction(7,2));
    }

    @Test
    public void whenDivisionThenReturnCorrectAnswer() {
        assertEquals(2,calculadora.division(8, 4));
    }

    @Test
    public void whenDivisionByZeroThenThrowException() {
        Throwable exception = assertThrows(IllegalArgumentException.class,() -> {
           calculadora.division(5, 0);
        });
        assertEquals("No se puede dividir por cero", exception.getMessage());
    }

    @Test
    public void whenSquareRootThenReturnCorrectAnswer() {
        assertEquals(3.0, calculadora.squareRoot(9));
    }
}
