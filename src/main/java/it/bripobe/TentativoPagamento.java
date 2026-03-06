package it.bripobe;

import it.bripobe.fileCarrello.ViewCarrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/tentativopagamento")
public class TentativoPagamento extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

            List<ViewCarrello> lista = new ArrayList<>();
            String sql = "SELECT CODICE FROM CARRELLO WHERE USERNAME=?";


            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);

                try(ResultSet rs = ps.executeQuery()){
                    while (rs.next()) {
                        String codice = rs.getString("CODICE");

                        // leggere la quantità relativa a questo codice
                        String paramName = "quantita_" + codice;
                        int quantita = Integer.parseInt(request.getParameter(paramName));

                        // Passa la quantità dal form al costruttore
                        lista.add(new ViewCarrello(codice, quantita));
                    }
                }

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
