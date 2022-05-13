package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RelatorioSintetico {

	private int totalDeProdutosVendidos;
	private int totalDePedidosRealizados;
	private BigDecimal montanteDeVendas = BigDecimal.ZERO;
	private Optional<Pedido> pedidoMaisBarato;
	private Optional<Pedido> pedidoMaisCaro;
	private long categoriasProcessadas;

	// HashSet<String> categoriasProcessadas = new HashSet<>();

	public RelatorioSintetico(List<Pedido> pedidos) {

		this.montanteDeVendas = pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();
		this.totalDePedidosRealizados = pedidos.size();
		this.categoriasProcessadas = pedidos.stream().map(Pedido::getCategoria).distinct().count();
		this.pedidoMaisBarato = pedidos.stream().min(Comparator.comparing(Pedido::getValorTotal));
		this.pedidoMaisCaro = pedidos.stream().max(Comparator.comparing(Pedido::getValorTotal));

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

	public Optional<Pedido> getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}

	public Optional<Pedido> getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}

}
