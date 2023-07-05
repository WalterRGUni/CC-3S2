import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SQLIntegrationTestBase {
    protected FacturaDao facturaDao;
    Connection conn;
    @BeforeEach
    public void openConnectionAndCleanup() {
        facturaDao = new FacturaDao();
        conn = facturaDao.conectar();
        facturaDao.eliminarDatos();
    }

    @AfterEach
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
