package it.bripobe.StrategyFiles;

public class PontePagamento {

    private MetodoPagamento metodoPagamento;

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String checkout() {

        if (metodoPagamento == null) {
            throw new IllegalStateException("Metodo di pagamento non impostato");
        }

        return metodoPagamento.getPagina();
    }
}