package br.com.alura.comex;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private String categoria;
    private String produto;
    private String cliente;

    private BigDecimal preco;
    private int quantidade;

    private LocalDate data;
    
    public Pedido(String categoria, String produto, String cliente, BigDecimal preco, int quantidade, LocalDate data) {
        this.categoria = categoria;
        this.produto = produto;
        this.cliente = cliente;
        this.preco = preco;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProduto() {
        return produto;
    }

    public String getCliente() {
        return cliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    //implementar método getValorTotal
    public BigDecimal getValorTotal() {
    	return this.preco.multiply(new BigDecimal(this.quantidade));
    }
    
    //implementar método isMaisBaratoQue
    public boolean isMaisBaratoQue(Pedido outroPedido) {
    	if(outroPedido == null || this.getValorTotal().compareTo(outroPedido.getValorTotal()) < 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    //implementar método isMaisCaroQue
    public boolean isMaisCaroQue(Pedido outroPedido) {
    	if(outroPedido == null || this.getValorTotal().compareTo(outroPedido.getValorTotal()) > 0) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
    @Override
    public String toString() {
        return "Pedido{" +
                "categoria='" + categoria + '\'' +
                ", produto='" + produto + '\'' +
                ", cliente='" + cliente + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
