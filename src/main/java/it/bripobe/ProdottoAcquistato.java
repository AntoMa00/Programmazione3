package it.bripobe;

import java.math.BigDecimal;
public class ProdottoAcquistato {

    public ProdottoAcquistato (String codice,String nome,String descrizione,int quantita,BigDecimal costo,String categoria){
        this.codice=codice;
        this.nome=nome;
        this.descrizione=descrizione;
        this.quantita=quantita;
        this.costo=costo;
        this.categoria=categoria;
    }

    private String codice;
    private String nome;
    private String descrizione;
    private int quantita;
    private  BigDecimal costo;
    private String categoria;
    public void setCodice(String codice) {
        this.codice=codice;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione=descrizione;
    }

    public void setQuantita(int quantita) {
        this.quantita=quantita;
    }

    public void setCosto(BigDecimal costo) {
        this.costo=costo;
    }

    public void setCategoria(String categoria) {
        this.categoria=categoria;
    }





    public String getCodice() {
        return codice;
    }


    public String getNome() {
        return nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public int getQuantita() {
        return quantita;
    }
    public BigDecimal getCosto() {
        return costo;
    }
    public String getCategoria() {
        return categoria;
    }



}
