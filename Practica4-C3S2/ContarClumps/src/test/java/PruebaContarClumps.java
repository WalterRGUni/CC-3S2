import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebaContarClumps {
    @Test
    public void arregloLongitudCeroONulo() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = new int[0];
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(0, clumps);
    }
    @Test
    public void arregloLongitudUno() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = {5};
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(0, clumps);
    }
    @Test
    public void arregloLongitudDosConElementosIguales() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = {5, 5};
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(1, clumps);
    }
    @Test
    public void arregloLongitudDosConElementosDiferentes() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = {5, 7};
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(0, clumps);
    }
    @Test
    public void arregloLongitudTresPrimerosDosElementosIgualesTerceroDiferente() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = {5, 5, 7};
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(1, clumps);
    }
    @Test
    public void arregloLongitudTresUltimosDosElementosIgualesPrimeroDiferente() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = {5, 7, 7};
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(1, clumps);
    }

    @Test
    public void arregloConClumpAlFinal() {
        ContarClumps contarClumps = new ContarClumps();
        int[] nums = {1, 2, 2, 3, 4, 4, 7, 9, 9};
        int clumps = contarClumps.contarClumps(nums);
        assertEquals(3, clumps);
    }
}
