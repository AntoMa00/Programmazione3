package it.bripobe;

import java.sql.*;
import java.time.LocalDate;

public class CRUDTest {

    public static void main(String[] args) {
        // try-with-resources → chiude AUTOMATICAMENTE la connessione
        try (Connection conn = DBConnection.getConnection()) {

//            stampaResultSet(conn);

            UtenteRegister utenteRegister = new UtenteRegister("EEEE", "po123", "Gerardo", "Colasurdo", LocalDate.of(2004, 7, 13), 'M', "3331598176", "geemail");
            inserisciUtente(conn, utenteRegister);
            stampaResultSet(conn);

//            it.bripobe.Utente.setTitolo("Led Zeppelin III");
//            aggiornaUtente(conn, it.bripobe.Utente);
//            stampaResultSet(conn);
//
//            eliminaUtente(conn, it.bripobe.Utente);
//            stampaResultSet(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // Derby lancia sempre un'eccezione normale allo shutdown
        }
    }

    // =========================
    // INSERT
    // =========================
    public static int inserisciUtente(Connection conn, UtenteRegister utenteRegister) throws SQLException {

        String sql =
                "INSERT INTO UTENTE (USERNAME, PASSWORD, NOME, COGNOME, DATANASCITA, SESSO, NTEL, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // PreparedStatement legato alla CONNESSIONE
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, utenteRegister.getUsername());
            ps.setString(2, utenteRegister.getPassword());
            ps.setString(3, utenteRegister.getNome());
            ps.setString(4, utenteRegister.getCognome());
            ps.setDate(5, Date.valueOf(utenteRegister.getDataNascita()));
            ps.setString(6, String.valueOf(utenteRegister.getSesso()));
            ps.setString(7, utenteRegister.getNTel());
            ps.setString(8, utenteRegister.getEmail());

            return ps.executeUpdate(); // 🔥 QUI avviene l'inserimento
        }
    }

    // =========================
    // UPDATE
    // =========================
    /* public static int aggiornaUtente(Connection conn, it.bripobe.Utente it.bripobe.Utente) throws SQLException {

        String sql =
                "UPDATE it.bripobe.Utente SET Titolo = ?, Artista = ?, Anno = ? WHERE UtenteId = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, it.bripobe.Utente.getTitolo());
            ps.setString(2, it.bripobe.Utente.getArtista());
            ps.setInt(3, it.bripobe.Utente.getAnno());
            ps.setInt(4, it.bripobe.Utente.getUtenteId());

            return ps.executeUpdate(); // 🔥 UPDATE reale
        }
    } */

    // =========================
    // DELETE
    // =========================
    /* public static int eliminaUtente(Connection conn, it.bripobe.Utente it.bripobe.Utente) throws SQLException {

        String sql = "DELETE FROM it.bripobe.Utente WHERE UtenteId = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, it.bripobe.Utente.getUtenteId());

            return ps.executeUpdate(); // 🔥 DELETE reale
        }
    } */

    // =========================
    // SELECT
    // =========================
    public static void stampaResultSet(Connection conn) throws SQLException {

        String sql = "SELECT * FROM UTENTE";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.printf(
                        "%s | %s | %s | %s | %s | %s | %s | %s%n",
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("NOME"),
                        rs.getString("COGNOME"),
                        rs.getDate("DATANASCITA"),
                        rs.getString("SESSO"),
                        rs.getString("NTEL"),
                        rs.getString("EMAIL")
                );
            }
        }
    }
}