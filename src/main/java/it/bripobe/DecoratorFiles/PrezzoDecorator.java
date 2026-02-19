package it.bripobe.DecoratorFiles;

/*
    Classe astratta così nel "main" non puoi fare istanze compà. La classe serve solo a
    definire cosa hanno in comune tutti i decoratori futuri
*/
public abstract class PrezzoDecorator implements Prodotto {
    protected Prodotto prodottoDecorato; // Riferimento all'oggetto incapsulato

    public PrezzoDecorator(Prodotto prodotto) {
        this.prodottoDecorato = prodotto;
    }

    @Override
    public double getPrezzo() {
        return prodottoDecorato.getPrezzo();
    }
}
