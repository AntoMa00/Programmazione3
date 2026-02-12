package it.bripobe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginService {

    public static Persona autentica() {

        try (Connection conn = DBConnection.getConnection();
             BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("Inserisci il tuo username: ");
            String username = buffer.readLine();

            System.out.print("Inserisci password: ");
            String password = buffer.readLine();

            // Chiama la factory per creare l'oggetto corretto
            return PersonaFactory.creaPersona(conn, username, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Errore durante la lettura dei dati.");
        }

        return null;
    }
}