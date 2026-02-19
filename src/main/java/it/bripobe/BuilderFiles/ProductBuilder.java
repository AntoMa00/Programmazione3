package it.bripobe.BuilderFiles;

public class ProductBuilder {
    private Product product;

    public void createProduct() {
        product = new Product();
    }

    public void setCodice(String codice) { product.setCodice(codice); }
    public void setNome(String nome) { product.setNome(nome); }
    public void setDescrizione(String descrizione) { product.setDescrizione(descrizione); }
    public void setQuantita(int quantita) { product.setQuantita(quantita); }
    public void setCosto(double costo) { product.setCosto(costo); }
    public void setCategoria(String categoria) { product.setCategoria(categoria); }
    public void setSconto(int sconto) { product.setSconto(sconto); }

    public Product build() { return product; }
}

