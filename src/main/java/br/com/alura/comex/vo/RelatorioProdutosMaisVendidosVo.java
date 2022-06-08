package br.com.alura.comex.vo;

public class RelatorioProdutosMaisVendidosVo {
    private String nomeProduto;
    private Long quantidadeVendida;

    public RelatorioProdutosMaisVendidosVo(String nomeProduto, Long quantidadeVendida) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    @Override
    public String toString() {
        return "Produto: " + nomeProduto + " | Quantidade Vendida: " + quantidadeVendida;
    }
}
