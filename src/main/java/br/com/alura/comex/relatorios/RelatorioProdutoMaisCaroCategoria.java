package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.alura.comex.FormatarSaidaDeValores;
import br.com.alura.comex.Pedido;

public class RelatorioProdutoMaisCaroCategoria extends Relatorio{

	private List<ProdutoMaisCaroCategoria> produtoMaisCaroCategoria;
	
	
	public List<ProdutoMaisCaroCategoria> getVendasPorCategoria() {
		return produtoMaisCaroCategoria;
	}
	
	@Override
	public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
		if (pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista está vazia");
		}
		FormatarSaidaDeValores formatar = new FormatarSaidaDeValores();

		produtoMaisCaroCategoria = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria, TreeMap::new, Collectors.toList()))
				.entrySet().stream().map(entry -> {
					return new ProdutoMaisCaroCategoria(entry.getKey(), entry.getValue().stream().max(Comparator.comparing(Pedido::getPreco))
							.stream().map(Pedido::getProduto).findFirst().get(), entry.getValue().stream().max(Comparator.comparing(Pedido::getPreco))
							.stream().map(Pedido::getPreco).findFirst().get());
				}).toList();
		
		produtoMaisCaroCategoria.stream()
		.forEach(produto -> {
			impressoraDeRelatorio.accept("\n\nCATEGORIA: " + produto.getCategoria() 
					+ "\nPRODUTO: " + produto.getProduto()
					+ "\nPREÇO: " + formatar.formatarSaidaDeValores(produto.getPreco()));
		});
	}
	
public class ProdutoMaisCaroCategoria {
		
		private String categoria;
		private String produto;
		private BigDecimal preco;
		
		
		public ProdutoMaisCaroCategoria(String categoria, String produto, BigDecimal preco) {
			super();
			this.categoria = categoria;
			this.produto = produto;
			this.preco = preco;
		}
		
		
		public String getCategoria() {
			return categoria;
		}
		
		public String getProduto() {
			return produto;
		}
		
		public BigDecimal getPreco() {
			return preco;
		}
}	
		
}
