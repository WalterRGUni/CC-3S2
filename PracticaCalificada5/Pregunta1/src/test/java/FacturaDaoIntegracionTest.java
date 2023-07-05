import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FacturaDaoIntegracionTest {
  FacturaDao dao;
  Connection conn;

  @BeforeEach
  public void openConnectionAndCleanup() {
    dao = new FacturaDao();
    conn = dao.conectar();
    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate("DELETE FROM factura;");
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  public void closeConnection() {
    try {
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void pruebaTodo() {
    dao.guardar("Luis", 1000);
    dao.guardar("Carlos", 2000);
    List<Factura> facturas = dao.todo();
    assertThat(facturas.get(0)).isEqualTo(new Factura("Luis", 1000));
    assertThat(facturas.get(1)).isEqualTo(new Factura("Carlos", 2000));
  }

  @Test
  public void pruebaTodosConAlMenos() {
    dao.guardar("Luis", 49);
    dao.guardar("Carlos", 50);
    dao.guardar("Juan", 51);
    List<Factura> facturas = dao.todosConAlMenos(50);
    assertThat(facturas).doesNotContain(new Factura("Luis", 49));
    assertThat(facturas).contains(new Factura("Carlos", 50));
    assertThat(facturas).contains(new Factura("Juan", 51));
  }

}
