package it.bripobe.fileCliente;

public class Prodotto {
    private String codice;
    private String nome;
    private String descrizione;
    private int quantita;
    private double prezzoBase;
    private String categoria;
    private int sconto;
    private double prezzoScontato;

    public Prodotto(String codice, String nome, String descrizione, int quantita, double prezzoBase,
                    String categoria, int sconto, double prezzoScontato) {
        this.codice = codice;
        this.nome = nome;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.prezzoBase = prezzoBase;
        this.categoria = categoria;
        this.sconto = sconto;
        this.prezzoScontato = prezzoScontato;
    }

    // Getters
    public String getCodice() { return codice; }
    public String getNome() { return nome; }
    public String getDescrizione() { return descrizione; }
    public int getQuantita() { return quantita; }
    public double getPrezzoBase() { return prezzoBase; }
    public String getCategoria() { return categoria; }
    public int getSconto() { return sconto; }
    public double getPrezzoScontato() { return prezzoScontato; }
}
