package it.bripobe.StrategyFiles;

public class PagamentoBancomat implements MetodoPagamento {

    @Override
    public String getPagina() {
        return "bancomat.jsp";
    }
}