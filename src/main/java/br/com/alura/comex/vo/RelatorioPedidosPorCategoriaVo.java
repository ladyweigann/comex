package br.com.alura.comex.vo;

public class RelatorioPedidosPorCategoriaVo {

    private String nomeCategoria;
    private Long quantidadePedidos;

    public RelatorioPedidosPorCategoriaVo(String nomeCategoria, Long quantidadePedidos) {
        this.nomeCategoria = nomeCategoria;
        this.quantidadePedidos = quantidadePedidos;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Long getQuantidadePedidos() {
        return quantidadePedidos;
    }

    @Override
    public String toString() {
        return "Categoria: " + nomeCategoria + " | Quantidade de Pedidos: " + quantidadePedidos;
    }
}
