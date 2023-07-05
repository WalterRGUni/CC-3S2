import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FacturaDaoTest extends SQLIntegrationTestBase {
  @Test
  public void pruebaTodo() {
    facturaDao.guardar("Luis", 1000);
    facturaDao.guardar("Carlos", 2000);
    List<Factura> facturas = facturaDao.todo();
    assertThat(facturas.get(0)).isEqualTo(new Factura("Luis", 1000));
    assertThat(facturas.get(1)).isEqualTo(new Factura("Carlos", 2000));
  }

  @Test
  public void pruebaTodosConAlMenos() {
    facturaDao.guardar("Luis", 49);
    facturaDao.guardar("Carlos", 50);
    facturaDao.guardar("Juan", 51);
    List<Factura> facturas = facturaDao.todosConAlMenos(50);
    assertThat(facturas).doesNotContain(new Factura("Luis", 49));
    assertThat(facturas).contains(new Factura("Carlos", 50));
    assertThat(facturas).contains(new Factura("Juan", 51));
  }
}
