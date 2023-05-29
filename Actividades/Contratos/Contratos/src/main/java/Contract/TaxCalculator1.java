package Contract;

public class TaxCalculator1 {
  // TaxCalculator con pre- y post-conditions implementado via asserts
  /**
   * Precondiciones: parámetro value >= 0
   * Postcondiciones: valor devuelto >= 0
   */
  public double calculateTax(double value) {

    assert value >= 0 : "Valor ingresado no puede ser negativo";

    double taxValue = 0;

    assert taxValue >= 0 : "Valor devuelto no puede ser negativo";

    return taxValue;
  }
}
