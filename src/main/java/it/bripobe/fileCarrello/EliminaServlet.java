package it.bripobe.fileCarrello;

import it.bripobe.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/elimina")
public class EliminaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codice = request.getParameter("codice");;
        // Prendere la sessione attiva
        HttpSession session = request.getSession(false); // false = non crea nuova sessione se non esiste

        // Prendere l'username dalla sessione
        String username = null;
        if (session != null) {
            username = (String) session.getAttribute("username"); // deve essere esattamente il nome con cui lo hai salvato
        }

        try (Connection conn = DBConnection.getConnection(getServletContext()))
        {
            System.out.println("Codice: " + codice);
            System.out.println("Username: " + username);

            if (username != null && codice != null) {

                String sql = "DELETE FROM CARRELLO WHERE CODICE = ? AND USERNAME = ?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, codice);
                ps.setString(2, username);

                ps.executeUpdate();
            }else{
                System.out.println("WEWE");
            }

            response.sendRedirect("viewcarrello");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
