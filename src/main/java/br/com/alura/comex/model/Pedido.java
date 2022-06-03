package br.com.alura.comex.model;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data = LocalDate.now();
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private BigDecimal desconto;
    private TipoDeDesconto tipoDeDesconto;

//    @OneToOne
//    private List<ItemDePedido> itemDePedidos;

    public Pedido(Cliente cliente, BigDecimal desconto, TipoDeDesconto tipoDeDesconto) {
        this.cliente = cliente;
        this.desconto = desconto;
        this.tipoDeDesconto = tipoDeDesconto;
    }

    public Pedido() {

    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public TipoDeDesconto getTipoDeDesconto() {
        return tipoDeDesconto;
    }

    public void setTipoDeDesconto(TipoDeDesconto tipoDeDesconto) {
        this.tipoDeDesconto = tipoDeDesconto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

//    public List<ItemDePedido> getItemDePedidos() {
//        return itemDePedidos;
//    }
//
//    public void setItemDePedidos(List<ItemDePedido> itemDePedidos) {
//        this.itemDePedidos = itemDePedidos;
//    }
}
