package it.bripobe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;

public class DBConnection {
   private static final String URL = "jdbc:derby:" + System.getProperty("user.home") + "/BriPoBeShop;create=true";
    //private static final String URL = "/BriPoBeShop;create=true";
    public static Connection getConnection() {
        try {
            // Carica il driver embedded di Derby
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//System.out.println("DDD");
            // Ritorna la connessione
            return DriverManager.getConnection(URL);

        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Errore nella connessioneeeee al DB", e);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver Derby non trovato!", e);
        }
    }
}
