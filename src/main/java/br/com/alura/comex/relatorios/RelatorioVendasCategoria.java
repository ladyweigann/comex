package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alura.comex.FormatarSaidaDeValores;
import br.com.alura.comex.Pedido;

public class RelatorioVendasCategoria extends Relatorio{

	@Override
	public void imprimirRelatorio(List<Pedido> pedidos) {
		
		FormatarSaidaDeValores formatar = new FormatarSaidaDeValores();
		
		Map<String, List<Pedido>> categorias = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria, TreeMap::new, Collectors.toList()));
		
		categorias
			.forEach((categoria, pedido) -> {
			System.out.printf("\nCATEGORIA: " + categoria
					+ "\nQUANTIDADE VENDIDA: " + pedido.stream().mapToInt(Pedido::getQuantidade).sum()
					+ "\nMONTANTE: %s\n", formatar.formatarSaidaDeValores(pedido.stream().map(Pedido::getValorTotal)
					.reduce(BigDecimal.ZERO, BigDecimal::add)));
			
		});
		
	}

	
	
}
