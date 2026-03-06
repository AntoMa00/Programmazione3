package it.bripobe.fileCarrello;

import it.bripobe.DBConnection;
import it.bripobe.fileCliente.Prodotto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codice = request.getParameter("codice");
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        double prezzoBase = Double.parseDouble(request.getParameter("prezzoBase"));
        String categoria = request.getParameter("categoria");
        int sconto = Integer.parseInt(request.getParameter("sconto"));
        double prezzoScontato = Double.parseDouble(request.getParameter("prezzoScontato"));

        // Prendere la sessione attiva
        HttpSession session = request.getSession(false); // false = non crea nuova sessione se non esiste

        // Prendere l'username dalla sessione
        String username = null;
        if (session != null) {
            username = (String) session.getAttribute("username"); // deve essere esattamente il nome con cui lo hai salvato
        }

        try (Connection conn = DBConnection.getConnection(getServletContext())) {
            inserisciProdotto(conn, codice, nome, descrizione, quantita, prezzoBase, categoria, sconto, prezzoScontato, username);



        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int inserisciProdotto(Connection conn, String codice, String nome, String descrizione, int quantita, double prezzoBase, String categoria, int sconto, double prezzoScontato, String username){
        String sql =
                "INSERT INTO CARRELLO (CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO, USERNAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // PreparedStatement legato alla CONNESSIONE
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codice);           // CODICE
            ps.setString(2, nome);             // NOME
            ps.setString(3, descrizione);      // DESCRIZIONE
            ps.setInt(4, quantita);            // QUANTITA
            ps.setDouble(5, prezzoBase);            // PREZZO_BASE
            ps.setString(6, categoria);        // CATEGORIA
            ps.setInt(7, sconto);              // SCONTO
            ps.setDouble(8, prezzoBase - (prezzoBase * sconto / 100.0)); // PREZZO_SCONTATO
            ps.setString(9, username);           // USERNAME


            return ps.executeUpdate(); // 🔥 QUI avviene l'inserimento
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
