package it.bripobe.FactoryMethodFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import java.io.File;

public class DBConnection {

    public static Connection getConnection(ServletContext context) {
        try {
            // 1. IL TUO PERCORSO ESATTO (Ho corretto gli slash per Java)
            // Usa "/" oppure "\\" altrimenti Java dà errore di sintassi.
            String cartellaMina = "C:/Users/mikel/IdeaProjects/Programmazione3/src/main/webapp/mina";

            // 2. NOME DEL DATABASE
            // Derby creerà una cartella chiamata "ecommerce_db" DENTRO la cartella "mina".
            // Non possiamo usare "mina" direttamente come DB perché contiene già altri file.
            String dbName = "ecommerce_db";

            // Percorso finale: .../mina/ecommerce_db
            String connectionUrl = "jdbc:derby:" + cartellaMina + "/" + dbName + ";create=true";

            // 3. CARICAMENTO DRIVER
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            System.out.println("--- TENTATIVO CONNESSIONE ---");
            System.out.println("DB URL: " + connectionUrl);

            // 4. CONNESSIONE
            return DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore SQL (Controlla se il DB è bloccato da IntelliJ): " + e.getMessage(), e);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver Derby non trovato!", e);
        }
    }
}