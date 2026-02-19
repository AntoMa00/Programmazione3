package it.bripobe.FactoryMethodFiles;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/login")
public class LoginService extends HttpServlet{


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recupero dei dati inviati dal form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection(getServletContext())) {
            Persona persona = PersonaFactory.creaPersona(conn, username, password);

            if (persona != null) {
                HttpSession session = request.getSession(); // crea nuova sessione se non esiste
                session.setAttribute("username", username); // salva username nella sessione
                // Polimorfismo: l’oggetto decide cosa deve aprire
                persona.apriDocumento(response);
            } else {
                System.out.println("Username o password errati."); // gestiscilo con il CSS
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }




    }




//    public static Persona autentica(ServletContext context) {
//
//        try (Connection conn = DBConnection.getConnection(context)) {
//
//
//            // Chiama la factory per creare l'oggetto corretto
//            return PersonaFactory.creaPersona(conn, username, password);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("Errore durante la lettura dei dati.");
//        }
//
//        return null;
//    }
}