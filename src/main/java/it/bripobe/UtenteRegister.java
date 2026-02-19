package it.bripobe;
//import jdk.vm.ci.meta.Local;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

@WebServlet("/register")
public class UtenteRegister extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupero dei dati inviati dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        LocalDate datanascita = LocalDate.parse(request.getParameter("datanascita"));
        String sesso = request.getParameter("sesso");
        String numerotelefono = request.getParameter("numerotelefono");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confermapassword = request.getParameter("confermapassword");

        System.out.println("AAA");

        // try-with-resources → chiude AUTOMATICAMENTE la connessione
        try (Connection conn = DBConnection.getConnection(getServletContext())) {

//            stampaResultSet(conn);

            inserisciUtente(conn, username, password, nome, cognome, datanascita, sesso, numerotelefono);
            // Redirect a login o pagina di successo
            response.sendRedirect("login.html");


//            stampaResultSet(conn);

//            it.bripobe.Utente.setTitolo("Led Zeppelin III");
//            aggiornaUtente(conn, it.bripobe.Utente);
//            stampaResultSet(conn);
//
//            eliminaUtente(conn, it.bripobe.Utente);
//            stampaResultSet(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            DriverManager.getConnection("jdbc:derby:;shutdown=true");
//        } catch (SQLException e) {
//            // Derby lancia sempre un'eccezione normale allo shutdown
//        }






//        // Imposta il tipo di contenuto della risposta
//        response.setContentType("text/html");


    }

    public static int inserisciUtente(Connection conn, String username, String password, String nome, String cognome, LocalDate datanascita, String sesso, String numerotelefono) throws SQLException {

        String sql =
                "INSERT INTO UTENTE (USERNAME, PASSWORD, NOME, COGNOME, DATANASCITA, SESSO, NTEL, RUOLO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // PreparedStatement legato alla CONNESSIONE
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, nome);
            ps.setString(4, cognome);
            ps.setDate(5, Date.valueOf(datanascita));
            ps.setString(6, String.valueOf(sesso));
            ps.setString(7, numerotelefono);
            ps.setString(8, "USER");

            return ps.executeUpdate(); // 🔥 QUI avviene l'inserimento
        }
    }




}