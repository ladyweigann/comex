package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioSintetico {

	int totalDeProdutosVendidos;
	int totalDePedidosRealizados;
	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	Pedido pedidoMaisBarato;
	Pedido pedidoMaisCaro;
	int totalDeCategorias;

	CategoriasProcessadas categoriasProcessadas = new CategoriasProcessadas();

	public RelatorioSintetico(List<Pedido> pedidos) {

		for (int i = 0; i < pedidos.size(); i++) {
			Pedido pedidoAtual = pedidos.get(i);

			if (pedidoAtual == null) {
				break;
			}

			if (pedidoAtual.isMaisBaratoQue(this.pedidoMaisBarato)) {
				this.pedidoMaisBarato = pedidoAtual;
			}

			if (pedidoAtual.isMaisCaroQue(this.pedidoMaisCaro)) {
				this.pedidoMaisCaro = pedidoAtual;
			}

			montanteDeVendas = montanteDeVendas.add(pedidoAtual.getValorTotal());
			totalDeProdutosVendidos += pedidoAtual.getQuantidade();
			totalDePedidosRealizados++;

			if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
				totalDeCategorias++;
				categoriasProcessadas.add(pedidoAtual.getCategoria());
			}
		}

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

	public Pedido getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}

	public Pedido getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}

	public int getTotalDeCategorias() {
		return totalDeCategorias;
	}

}
