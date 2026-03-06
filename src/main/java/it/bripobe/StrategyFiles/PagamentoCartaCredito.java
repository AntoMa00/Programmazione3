package it.bripobe.StrategyFiles;

public class PagamentoCartaCredito implements MetodoPagamento {

    @Override
    public String getPagina() {
        return "cartadicredito.jsp";
    }
}