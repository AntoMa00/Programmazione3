package it.bripobe.fileCliente;

import it.bripobe.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/specifico")
public class ProdottoSpecificoServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");

        try (Connection conn = DBConnection.getConnection(getServletContext()))
        {

            List<Prodotto> prodotti = new ArrayList<>();
            String sql = "SELECT CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO FROM PRODOTTOACQUISTABILE WHERE CATEGORIA=?";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, categoria);  // <-- qui passi il parametro

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
