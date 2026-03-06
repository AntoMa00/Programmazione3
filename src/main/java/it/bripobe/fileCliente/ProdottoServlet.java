package it.bripobe.fileCliente;

import it.bripobe.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/salva")
public class ProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    // 🔹 doPost chiama semplicemente doGet (opzione 1)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    // 🔹 Estraiamo il codice comune in un metodo privato
    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DBConnection.getConnection(getServletContext())) {

            List<Prodotto> prodotti = new ArrayList<>();
            String sql = "SELECT CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO FROM PRODOTTOACQUISTABILE";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        prodotti.add(new Prodotto(
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

            request.setAttribute("listaProdotti", prodotti);
            request.getRequestDispatcher("cliente.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}