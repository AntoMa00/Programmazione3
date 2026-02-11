package it.bripobe;

import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            // Prendere i dati dal form
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Qui puoi collegarti al database e verificare le credenziali
            // Ad esempio:
            /*
            String sql = "SELECT * FROM UTENTE WHERE USERNAME = ? AND PASSWORD = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        response.sendRedirect("index.html"); // pagina di benvenuto
                    } else {
                        response.sendRedirect("preferiti.html"); // ritorna al login

                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Errore nel database");
            }*/
            response.sendRedirect("preferiti.html");

    }
}




