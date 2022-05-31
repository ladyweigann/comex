package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.alura.comex.FormatarSaidaDeValores;
import br.com.alura.comex.Pedido;

public class RelatorioVendasCategoria extends Relatorio {

	private List<VendasCategoria> vendasPorCategoria;
	
	
	public List<VendasCategoria> getVendasPorCategoria() {
		return vendasPorCategoria;
	}
	
	@Override
	public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
		if (pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista está vazia");
		}
		
		FormatarSaidaDeValores formatar = new FormatarSaidaDeValores();

		vendasPorCategoria = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria, TreeMap::new, Collectors.toList()))
				.entrySet().stream().map(entry -> {
					return new VendasCategoria(entry.getKey(), 
							entry.getValue().stream().mapToInt(Pedido::getQuantidade).sum(), 
							entry.getValue().stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
				}).toList();
				
			
		vendasPorCategoria.stream().forEach(vendas -> {
			impressoraDeRelatorio.accept("\n\nCATEGORIA: " + vendas.getCategoria() + "\nQUANTIDADE VENDIDA: "
					+ vendas.getQuantidadeVendida() + "\nMONTANTE: "
						+ formatar.formatarSaidaDeValores(vendas.getMontante()));

		});

	}
	public class VendasCategoria {
		
		private String categoria;
		private int quantidadeVendida;
		private BigDecimal montante;
		
		
		public VendasCategoria(String categoria, int quantidadeVendida, BigDecimal montante) {
			this.categoria = categoria;
			this.quantidadeVendida = quantidadeVendida;
			this.montante = montante;
		}
		
		
		public String getCategoria() {
			return categoria;
		}
		
		public int getQuantidadeVendida() {
			return quantidadeVendida;
		}
		
		public BigDecimal getMontante() {
			return montante;
		}
		
	}
}


