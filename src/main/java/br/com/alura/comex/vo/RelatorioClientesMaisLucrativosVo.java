package br.com.alura.comex.vo;

import java.math.BigDecimal;

public class RelatorioClientesMaisLucrativosVo {

    private String nomeCliente;
    private BigDecimal montanteGasto;

    public RelatorioClientesMaisLucrativosVo(String nomeCliente, BigDecimal montanteGasto) {
        this.nomeCliente = nomeCliente;
        this.montanteGasto = montanteGasto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public BigDecimal getMontanteGasto() {
        return montanteGasto;
    }

    @Override
    public String toString() {
        return "Cliente: " + nomeCliente + " | Montante Gasto: R$ " + montanteGasto;
    }
}
