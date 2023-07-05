import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDao {
  private final String url = "jdbc:postgresql://localhost/bdfactura";
  private final String user = "postgres";
  private final String password = "1234";

  public Connection conectar() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, user, password);
      System.out.println("Conexión existosa");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public static void main(String[] args) {
    FacturaDao app = new FacturaDao();
    app.guardar("Cliente1", 123);
  }

  public void guardar(String cliente, int valor) {
    Connection conn = conectar();
    try {
      Statement stmt = conn.createStatement();
      String consultaInsert =
          String.format("INSERT INTO factura (nombre, valor) VALUES ('%s', %d)", cliente, valor);
      stmt.executeUpdate(consultaInsert);
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Factura> todo() {
    List<Factura> facturas = new ArrayList<>();
    Connection conn = conectar();
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM factura;");
      while (rs.next()) {
        String nombre = rs.getString("nombre");
        int valor = rs.getInt("valor");
        facturas.add(new Factura(nombre, valor));
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return facturas;
  }

  public List<Factura> todosConAlMenos(int minVal) {
    List<Factura> facturas = new ArrayList<>();
    Connection conn = conectar();
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs =
          stmt.executeQuery(String.format("SELECT * FROM factura WHERE valor >= %d;", minVal));
      while (rs.next()) {
        String nombre = rs.getString("nombre");
        int valor = rs.getInt("valor");
        facturas.add(new Factura(nombre, valor));
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return facturas;
  }

  public void eliminarDatos() {
    Connection conn = conectar();
    conn = conectar();
    try {
      Statement stmt = conn.createStatement();
      stmt.executeUpdate("DELETE FROM factura;");
      stmt.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
