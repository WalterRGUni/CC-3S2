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
    public void whenSubtractionTwoNumbersThenReturnCorrectAnswer() {
        assertEquals(5, calculadora.subtraction(7,2));
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

    @Test
    public void whenSquareRootOfNegativeThenThrowException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
           calculadora.squareRoot(-4);
        });
        assertEquals("No se puede sacar raiz cuadrada a un número negativo", exception.getMessage());
    }

    @Test
    public void whenExponentiationThenReturnCorrectAnswer() {
        assertEquals(25, calculadora.exponentiation(5, 2));
    }

    @Test
    public void whenExponentiation0toThePower0ThenThrowException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.exponentiation(0, 0);
        });
        assertEquals("0 a la 0 no está definido", exception.getMessage());
    }
}
