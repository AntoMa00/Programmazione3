package it.bripobe;

import java.sql.SQLException;
import java.sql.DriverManager;

public class Login {

    public static void main(String[] args) {

        Persona persona = LoginService.autentica();

        if (persona != null) {
            // Polimorfismo: l’oggetto decide cosa deve aprire
            persona.apriDocumento();
        } else {
            System.out.println("Username o password errati."); // gestiscilo con il CSS
        }

        // Shutdown Derby embedded (opzionale)
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // Derby lancia sempre eccezione normale a shutdown
        }
    }
}