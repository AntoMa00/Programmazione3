package it.bripobe;

import java.sql.*;
import java.time.LocalDate;

public class CRUDTest {

    public static void main(String[] args) {
        // try-with-resources → chiude AUTOMATICAMENTE la connessione
        try (Connection conn = DBConnection.getConnection()) {

//            stampaResultSet(conn);

            Utente utente = new Utente("EEEE", "po123", "Gerardo", "Colasurdo", LocalDate.of(2004, 7, 13), 'M', "3331598176", "geemail");
            inserisciUtente(conn, utente);
            stampaResultSet(conn);

//            Utente.setTitolo("Led Zeppelin III");
//            aggiornaUtente(conn, Utente);
//            stampaResultSet(conn);
//
//            eliminaUtente(conn, Utente);
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
    public static int inserisciUtente(Connection conn, Utente utente) throws SQLException {

        String sql =
                "INSERT INTO UTENTE (USERNAME, PASSWORD, NOME, COGNOME, DATANASCITA, SESSO, NTEL, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // PreparedStatement legato alla CONNESSIONE
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, utente.getUsername());
            ps.setString(2, utente.getPassword());
            ps.setString(3, utente.getNome());
            ps.setString(4, utente.getCognome());
            ps.setDate(5, Date.valueOf(utente.getDataNascita()));
            ps.setString(6, String.valueOf(utente.getSesso()));
            ps.setString(7, utente.getNTel());
            ps.setString(8, utente.getEmail());

            return ps.executeUpdate(); // 🔥 QUI avviene l'inserimento
        }
    }

    // =========================
    // UPDATE
    // =========================
    /* public static int aggiornaUtente(Connection conn, Utente Utente) throws SQLException {

        String sql =
                "UPDATE Utente SET Titolo = ?, Artista = ?, Anno = ? WHERE UtenteId = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Utente.getTitolo());
            ps.setString(2, Utente.getArtista());
            ps.setInt(3, Utente.getAnno());
            ps.setInt(4, Utente.getUtenteId());

            return ps.executeUpdate(); // 🔥 UPDATE reale
        }
    } */

    // =========================
    // DELETE
    // =========================
    /* public static int eliminaUtente(Connection conn, Utente Utente) throws SQLException {

        String sql = "DELETE FROM Utente WHERE UtenteId = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Utente.getUtenteId());

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