package it.bripobe;

import it.bripobe.DBConnection;
import it.bripobe.fileCarrello.ViewCarrello;
import it.bripobe.fileCliente.Prodotto;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/selezionaprodotto")
public class SelezionaProdotto extends HttpServlet {

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
            List<Prodotto> prodotto = new ArrayList<>();
            prodotto.add(selezionaProdotto(conn, codice));

            List<Prodotto> consigli = new ArrayList<>();
            consigli.addAll(selezionaProdottiConsigliati(conn, codice, categoria));

            request.setAttribute("listaProdotti", prodotto);
            request.setAttribute("listaProdottiConsigliati", consigli);
            request.getRequestDispatcher("visualizzaProdotto.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Prodotto selezionaProdotto(Connection conn, String codice) {
        String sql = "SELECT CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO FROM PRODOTTOACQUISTABILE WHERE CODICE = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codice);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Prodotto(
                            rs.getString("CODICE"),
                            rs.getString("NOME"),
                            rs.getString("DESCRIZIONE"),
                            rs.getInt("QUANTITA"),
                            rs.getDouble("PREZZO_BASE"),
                            rs.getString("CATEGORIA"),
                            rs.getInt("SCONTO"),
                            rs.getDouble("PREZZO_SCONTATO")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // se non trovi nulla
    }

    public static List<Prodotto> selezionaProdottiConsigliati(Connection conn, String codice, String categoria) {
        String sql = "SELECT CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO FROM PRODOTTOACQUISTABILE WHERE CODICE != ? AND CATEGORIA = ?";

        List<Prodotto> lista = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codice);
            ps.setString(2, categoria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) { // qui prendi tutti
                    lista.add(new Prodotto(
                            rs.getString("CODICE"),
                            rs.getString("NOME"),
                            rs.getString("DESCRIZIONE"),
                            rs.getInt("QUANTITA"),
                            rs.getDouble("PREZZO_BASE"),
                            rs.getString("CATEGORIA"),
                            rs.getInt("SCONTO"),
                            rs.getDouble("PREZZO_SCONTATO")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista; // anche se è vuota, non null
    }
}
