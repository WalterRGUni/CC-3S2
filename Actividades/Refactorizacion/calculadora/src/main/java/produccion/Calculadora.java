package produccion;

public class Calculadora {
    private boolean status;

    public Calculadora() {
        this.status = true;
    }

    public boolean getStatus() {
        return status;
    }

    public int addition(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int division(int a, int b) {
        if(b == 0) {
            throw new IllegalArgumentException("No se puede dividir por cero");
        } else {
            return a / b;
        }
    }

    public double squareRoot(int a) {
        if(a < 0) {
            throw new IllegalArgumentException("No se puede sacar raiz cuadrada a un número negativo");
        } else {
            return Math.sqrt(a);
        }
    }

    public int exponentiation(int a, int n) {
        if(a == 0 && n == 0) {
            throw new IllegalArgumentException("0 a la 0 no está definido");
        } else {
            return (int)Math.pow(a, n);
        }
    }
}
