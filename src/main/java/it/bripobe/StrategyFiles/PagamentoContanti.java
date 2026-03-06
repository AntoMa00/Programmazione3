package it.bripobe.StrategyFiles;

public class PagamentoContanti implements MetodoPagamento {

    @Override
    public String getPagina() {
        return "contanti.jsp";
    }
}