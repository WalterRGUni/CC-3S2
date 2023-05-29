package Contract;

public class TaxCalculator {

    /**
     * Precondiciones: parámetro value >= 0
     * Postcondiciones: valor devuelto >= 0
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
