import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:derby:BriPoBeShop;create=true";

    public static Connection getConnection() {
        try {
            // carica il driver embedded (non obbligatorio con le versioni recenti)
            //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // ritorna la connessione
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore connessione al database");
        }
    }
}
