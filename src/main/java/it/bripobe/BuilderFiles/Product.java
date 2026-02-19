package it.bripobe.BuilderFiles;


public class Product {
    private String codice;
    private String nome;
    private String descrizione;
    private int quantita;
    private double costo;
    private String categoria;
    private int sconto;

    // setters
    public void setCodice(String codice) { this.codice = codice; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }
    public void setQuantita(int quantita) { this.quantita = quantita; }
    public void setCosto(double costo) { this.costo = costo; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setSconto(int sconto) { this.sconto = sconto; }

    @Override
    public String toString() {
        return "Product {" +
                "codice='" + codice + '\'' +
                ", nome='" + nome + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", quantita=" + quantita +
                ", costo=" + costo +
                ", categoria='" + categoria + '\'' +
                ", sconto=" + sconto +
                '}';
    }
}
