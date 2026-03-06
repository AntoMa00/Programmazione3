package it.bripobe.fileCarrello;

import it.bripobe.DBConnection;

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


@WebServlet("/viewcarrello")
public class ViewCarrelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DBConnection.getConnection(getServletContext()))
        {

            // Prendere la sessione attiva
            HttpSession session = request.getSession(false); // false = non crea nuova sessione se non esiste

            // Prendere l'username dalla sessione
            String username = null;
            if (session != null) {
                username = (String) session.getAttribute("username"); // deve essere esattamente il nome con cui lo hai salvato
            }

            List<ViewCarrello> carrello = new ArrayList<>();
            String sql = "SELECT CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO FROM CARRELLO WHERE USERNAME=?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);

                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        carrello.add(new ViewCarrello(
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

            }

            double totale = calcolaTotalePrezzo(conn, username);

            request.setAttribute("totaleCarrello", totale);
            request.setAttribute("listaCarrello", carrello);
            request.getRequestDispatcher("carrello.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double calcolaTotalePrezzo(Connection conn, String username) {
        String sql = "SELECT SUM(PREZZO_SCONTATO) AS TOTALE FROM CARRELLO WHERE USERNAME=?";
        double somma = 0.0;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // il valore SUM può essere null se non ci sono righe, quindi gestiamo il casting
                    somma = rs.getDouble("TOTALE");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return somma;
    }
}
