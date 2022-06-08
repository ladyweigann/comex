package br.com.alura.comex.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemDePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal precoUnitario;
    private int quantidade;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private BigDecimal desconto;
    private TipoDescontoPedido tipoDeDesconto;

    public ItemDePedido(int quantidade, Produto produto, Pedido pedido, BigDecimal desconto, TipoDescontoPedido tipoDeDesconto) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
        this.precoUnitario = produto.getPrecoUnitario();
        this.desconto = desconto;
        this.tipoDeDesconto = tipoDeDesconto;
    }

    public ItemDePedido(int quantidade, Produto produto, Pedido pedido) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
    }

    public ItemDePedido() {

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDescontoPedido getTipoDeDesconto() {
        return tipoDeDesconto;
    }

    public void setTipoDeDesconto(TipoDescontoPedido tipoDeDesconto) {
        this.tipoDeDesconto = tipoDeDesconto;
    }
}
