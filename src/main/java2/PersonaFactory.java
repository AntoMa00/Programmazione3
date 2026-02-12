import java.sql.*;

public class PersonaFactory {

    public static Persona creaPersona(Connection conn, String username, String password) throws SQLException {

        String sql = "SELECT RUOLO FROM UTENTE WHERE USERNAME = ? AND PASSWORD = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String ruolo = rs.getString("RUOLO");

                    if ("USER".equalsIgnoreCase(ruolo)) {
                        return new Utente();
                    } else if ("ADMIN".equalsIgnoreCase(ruolo)) {
                        return new Amministratore();
                    }
                }
            }
        }

        return null; // login fallito
    }
}