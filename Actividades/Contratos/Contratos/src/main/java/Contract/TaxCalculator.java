package Contract;

public class TaxCalculator {

    /**
     * Precondiciones: parámetro de entrada value >= 0
     * Postcondiciones: valor devuelto taxValue >= 0
     */
    public double calculateTax(double value) {
        if(value < 0) {
            throw new RuntimeException("El valor tiene que ser positivo");
        }

        double taxValue = 0;

        if(taxValue < 0) {
            throw new RuntimeException("El impuesto no puede ser negativo");
        }
        return taxValue;
    }
}
