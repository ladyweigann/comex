package br.com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Pedido {

    private String categoria;
    private String produto;
    private String cliente;
    private BigDecimal preco;
    private int quantidade;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate data;
    
    public Pedido() {
    	
    }
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

    public BigDecimal getValorTotal() {
    	return this.preco.multiply(new BigDecimal(this.quantidade)).setScale(2, RoundingMode.HALF_DOWN);
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
