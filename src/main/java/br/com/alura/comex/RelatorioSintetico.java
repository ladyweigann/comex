package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

public class RelatorioSintetico {

	int totalDeProdutosVendidos;
	int totalDePedidosRealizados;
	BigDecimal montanteDeVendas = BigDecimal.ZERO;
	Pedido pedidoMaisBarato;
	Pedido pedidoMaisCaro;
	int totalDeCategorias;

	HashSet<String> categoriasProcessadas = new HashSet<>();

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

			if (!categoriasProcessadas.contains(pedidoAtual.getCategoria())) {
				totalDeCategorias++;
				categoriasProcessadas.add(pedidoAtual.getCategoria());
			}
		}
		
		//Refatorando cálculos
		
		montanteDeVendas = pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();
		totalDePedidosRealizados = pedidos.size();
		//Categorias processadas - quantidade de categorias distintas
		//Mais barato e mais caro
		

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
