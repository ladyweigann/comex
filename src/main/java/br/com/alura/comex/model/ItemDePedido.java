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
//    @OneToOne
//    @JoinColumn(name = "produto_id")
//    private Produto produto;
//    @OneToOne
//    @JoinColumn(name = "pedido_id")
//    private Pedido pedido;
    private BigDecimal desconto;
    private TipoDeDesconto tipoDeDesconto;

//    public Pedido getPedido() {
//        return pedido;
//    }
//
//    public void setPedido(Pedido pedido) {
//        this.pedido = pedido;
//    }
//
//    public Produto getProduto() {
//        return produto;
//    }
//
//    public void setProduto(Produto produto) {
//        this.produto = produto;
//    }

}
