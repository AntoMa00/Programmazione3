package it.bripobe.BuilderFiles;


import it.bripobe.BuilderFiles.Product;
import it.bripobe.BuilderFiles.ProductBuilder;
import it.bripobe.FactoryMethodFiles.DBConnection;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/newproduct")
public class NewProduct extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Leggere i dati dal form HTML
        String codice = request.getParameter("codice");
        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        double costo = Double.parseDouble(request.getParameter("costo"));
        String categoria = request.getParameter("categoria");
        int sconto = Integer.parseInt(request.getParameter("sconto"));

        // Prendere la sessione attiva
        HttpSession session = request.getSession(false); // false = non crea nuova sessione se non esiste

        // Prendere l'username dalla sessione
        String username = null;
        if (session != null) {
            username = (String) session.getAttribute("username"); // deve essere esattamente il nome con cui lo hai salvato
        }

        // 2️⃣ Creare il prodotto con il Builder
        ProductBuilder builder = new ProductBuilder();
        builder.createProduct();
        builder.setCodice(codice);
        builder.setNome(nome);
        builder.setDescrizione(descrizione);
        builder.setQuantita(quantita);
        builder.setCosto(costo);
        builder.setCategoria(categoria);
        builder.setSconto(sconto);

        Product prodotto = builder.build();

        try (Connection conn = DBConnection.getConnection(getServletContext())) {
            inserisciNuovoProdotto(conn, codice, nome, descrizione, quantita, costo, categoria, sconto, username);
        }catch (SQLException e) {
            e.printStackTrace();
        }

        // 4️⃣ Risposta al client
//        response.setContentType("text/html");
//        response.getWriter().println("Prodotto creato: " + prodotto);
    }

    public static int inserisciNuovoProdotto(Connection conn, String codice, String nome, String descrizione, int quantita, double costo, String categoria, int sconto, String username) throws SQLException {

        String sql =
                "INSERT INTO PRODOTTOACQUISTABILE (CODICE, NOME, DESCRIZIONE, QUANTITA, PREZZO_BASE, CATEGORIA, SCONTO, PREZZO_SCONTATO, USERNAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // PreparedStatement legato alla CONNESSIONE
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codice);           // CODICE
            ps.setString(2, nome);             // NOME
            ps.setString(3, descrizione);      // DESCRIZIONE
            ps.setInt(4, quantita);            // QUANTITA
            ps.setDouble(5, costo);            // PREZZO_BASE
            ps.setString(6, categoria);        // CATEGORIA
            ps.setInt(7, sconto);              // SCONTO
            ps.setDouble(8, costo - (costo * sconto / 100.0)); // PREZZO_SCONTATO
            ps.setString(9, username);           // USERNAME


            return ps.executeUpdate(); // 🔥 QUI avviene l'inserimento
        }
    }
}
