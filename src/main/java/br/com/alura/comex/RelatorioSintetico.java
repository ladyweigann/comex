package br.com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class RelatorioSintetico {

	private int totalDeProdutosVendidos;
	private int totalDePedidosRealizados;
	private BigDecimal montanteDeVendas = BigDecimal.ZERO;
	private Pedido pedidoMaisBarato;
	private Pedido pedidoMaisCaro;
	private long categoriasProcessadas;

	public RelatorioSintetico(List<Pedido> pedidos) {
		
		
		if(pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode estar vazia");
		}
		
		this.montanteDeVendas = pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_DOWN);
		this.totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();
		this.totalDePedidosRealizados = pedidos.size();
		this.categoriasProcessadas = pedidos.stream().map(Pedido::getCategoria).distinct().count();
		this.pedidoMaisBarato = pedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista não pode estar vazia"));
		this.pedidoMaisCaro = pedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista não pode estar vazia"));

	}
	
	public int getTotalDeProdutosVendidos() {
		return totalDeProdutosVendidos;
	}

	public int getTotalDePedidosRealizados() {
		return totalDePedidosRealizados;
	}

	public BigDecimal getMontanteDeVendas() {
		return montanteDeVendas;
	}

	public long getCategoriasProcessadas() {
		return categoriasProcessadas;
	}

	public Pedido getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}
	public Pedido getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}

	
	@Override
    public String toString() {
        return "#### RELATÓRIO DE VALORES TOTAIS" +
                "\n- TOTAL DE PEDIDOS REALIZADOS: " + totalDePedidosRealizados +
                "\n- TOTAL DE PRODUTOS VENDIDOS: " + totalDeProdutosVendidos +
                "\n- TOTAL DE CATEGORIAS: " + categoriasProcessadas +
                "\n- MONTANTE DE VENDAS: " + formatarSaidaDeValores(montanteDeVendas) +
                "\n- PEDIDO MAIS BARATO: " + formatarSaidaDeValores(pedidoMaisBarato.getValorTotal()) + " (" + pedidoMaisBarato.getProduto() + ")" +
                "\n- PEDIDO MAIS CARO: " + formatarSaidaDeValores(pedidoMaisCaro.getValorTotal()) + " (" + pedidoMaisCaro.getProduto() + ")";
    }

	private String formatarSaidaDeValores(BigDecimal valor) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
	}

}
