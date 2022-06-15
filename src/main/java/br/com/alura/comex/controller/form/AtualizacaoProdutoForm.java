package br.com.alura.comex.controller.form;

import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.ProdutoRepository;

import java.math.BigDecimal;

public class AtualizacaoProdutoForm {

    private String nome;
    private String descricao;
    private BigDecimal precoUnitario;
    private int quantidadeEmEstoque;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
        Produto produto = produtoRepository.getOne(id);

        produto.setNome(this.nome);
        produto.setDescricao(this.descricao);
        produto.setPrecoUnitario(this.precoUnitario);
        produto.setQuantidadeEmEstoque(this.quantidadeEmEstoque);

        return produto;
    }
}
