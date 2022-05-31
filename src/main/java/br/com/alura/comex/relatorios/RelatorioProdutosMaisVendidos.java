package br.com.alura.comex.relatorios;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioProdutosMaisVendidos extends Relatorio {


	private List<ProdutosMaisVendidos> produtosMaisVendidos;
	
	
	public List<ProdutosMaisVendidos> getProdutosMaisVendidos() {
		return produtosMaisVendidos;
	}
	
	@Override
	public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
		
		if (pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista está vazia");
		}
		
		
		produtosMaisVendidos = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getProduto))
					.entrySet().stream()
						.map(entry -> {
							return new ProdutosMaisVendidos(entry.getKey(), entry.getValue().stream().mapToInt(Pedido::getQuantidade).sum());
						}).toList();
			
			
		produtosMaisVendidos.stream()
			.sorted((p1, p2) -> Integer.compare(p2.getQuantidadeVendida(), p1.getQuantidadeVendida()))		
				.limit(3)
					.forEach(produto -> {
						impressoraDeRelatorio.accept("\nPRODUTO: " + produto.getProduto() + "\nQUANTIDADE VENDIDA: " + produto.getQuantidadeVendida());

		});
	}
	
public class ProdutosMaisVendidos {
		
		private String produto;
		private int quantidadeVendida;
		
		
		public ProdutosMaisVendidos(String produto, int quantidadeVendida) {
			this.produto = produto;
			this.quantidadeVendida = quantidadeVendida;
		}


		public String getProduto() {
			return produto;
		}

		public int getQuantidadeVendida() {
			return quantidadeVendida;
		}

	}
}
