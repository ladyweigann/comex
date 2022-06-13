package br.com.alura.comex.controller.dto;

import br.com.alura.comex.model.Produto;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

public class ProdutoDto {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEmEstoque;
    private Long categoriaId;
    private String nomeCategoria;

    public ProdutoDto(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.precoUnitario = produto.getPrecoUnitario();
        this.quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
        this.categoriaId = produto.getCategoria().getId();
        this.nomeCategoria = produto.getCategoria().getNome();
    }


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public static List<ProdutoDto> converter(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDto::new).toList();
    }
}
