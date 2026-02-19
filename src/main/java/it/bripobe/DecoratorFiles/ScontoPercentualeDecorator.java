package it.bripobe.DecoratorFiles;

public class ScontoPercentualeDecorator extends PrezzoDecorator {
    private double percentualeSconto;

    public ScontoPercentualeDecorator(Prodotto prodotto, double percentualeSconto) {
        super(prodotto);
        this.percentualeSconto = percentualeSconto;
    }

    @Override
    public double getPrezzo() {
        double prezzoOriginale = super.getPrezzo();
        // Calcolo logica sconto
        return prezzoOriginale - (prezzoOriginale * (percentualeSconto / 100.0));
    }

}