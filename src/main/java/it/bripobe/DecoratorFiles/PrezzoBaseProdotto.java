package it.bripobe.DecoratorFiles;

import it.bripobe.FactoryMethodFiles.Amministratore;
import it.bripobe.FactoryMethodFiles.DBConnection;
import it.bripobe.FactoryMethodFiles.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/sconto")
public class PrezzoBaseProdotto extends HttpServlet implements Prodotto {

    private String codice;
    private double oldprezzo;

    public PrezzoBaseProdotto(String codice, double oldprezzo) {
        this.codice = codice;
        this.oldprezzo = oldprezzo;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupero dei dati inviati dal form
        String codice_html = request.getParameter("codice");
        int newsconto = Integer.parseInt(request.getParameter("newsconto"));

        try (Connection conn = DBConnection.getConnection(getServletContext())) {

            //int oldsconto = selezionaSconto(conn, codice_html);
            double oldprezzo = selezionaPrezzo(conn, codice_html);

            Prodotto telefono = new PrezzoBaseProdotto(codice_html, oldprezzo);

            Prodotto telefonoScontato = new ScontoPercentualeDecorator(telefono, newsconto);

            // Prezzo finale scontato
            double prezzoScontato = telefonoScontato.getPrezzo();

            // Aggiorno DB
            aggiornaPrezzoESconto(conn, codice_html, prezzoScontato, newsconto);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*public static int selezionaSconto(Connection conn, String codice) throws SQLException {

        String sql = "SELECT SCONTO FROM UTENTE WHERE CODICE=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codice);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("SCONTO"); // catturi lo sconto
                }
            }
        }

        // caso limite: codice non trovato
        return -1;
    }*/

    public static double selezionaPrezzo(Connection conn, String codice) throws SQLException {

        String sql = "SELECT PREZZO_BASE FROM UTENTE WHERE CODICE=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codice);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("PREZZO_BASE"); // catturi il prezzo
                }
            }
        }

        // caso limite: codice non trovato
        return -1.0;
    }

    public static void aggiornaPrezzoESconto(Connection conn, String codice, double nuovoPrezzo, int nuovoSconto) throws SQLException {

        String sql = "UPDATE UTENTE SET PREZZO_SCONTATO=?, SCONTO=? WHERE CODICE=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, nuovoPrezzo);
            ps.setInt(2, nuovoSconto);
            ps.setString(3, codice);
            ps.executeUpdate();
        }
    }

    @Override
    public double getPrezzo() {
        return oldprezzo;
    }
}


