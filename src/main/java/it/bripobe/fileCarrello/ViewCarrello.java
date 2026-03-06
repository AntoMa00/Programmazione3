package it.bripobe.fileCarrello;

public class ViewCarrello {
    private String codice;
    private String nome;
    private String descrizione;
    private int quantita;
    private double prezzoBase;
    private String categoria;
    private int sconto;
    private double prezzoScontato;

    public ViewCarrello(String codice, String nome, String descrizione, int quantita, double prezzoBase,
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

    //costruttore da usare per il calcolo delle quantita dopo un acquisto
    public ViewCarrello(String codice, int quantita) {
        this.codice = codice;
        this.quantita = quantita;
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