package br.com.alura.comex.vo;

public class RelatorioPedidosPorCliente {
    private String nomeCliente;
    private Long quantidadePedidos;

    public RelatorioPedidosPorCliente(String nomeCliente, Long quantidadePedidos) {
        this.nomeCliente = nomeCliente;
        this.quantidadePedidos = quantidadePedidos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    @Override
    public String toString() {
        return "Cliente: " + nomeCliente + ", Quantidade de Pedidos = " + quantidadePedidos;
    }
}
