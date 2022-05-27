package br.com.alura.comex.relatorios;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import br.com.alura.comex.FormatarSaidaDeValores;
import br.com.alura.comex.Pedido;

public class RelatorioProdutoMaisCaroCategoria {

	public static void relatorioProdutoMaisCaroCategoria(List<Pedido> pedidos) {
		FormatarSaidaDeValores formatar = new FormatarSaidaDeValores();

		Map<String, List<Pedido>> categorias = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria, TreeMap::new, Collectors.toList()));
		
		categorias.forEach((categoria, pedido) -> {
			System.out.printf("\n\nCATEGORIA: " + categoria 
					+ "\nPRODUTO: " + pedido.stream().max(Comparator.comparing(Pedido::getPreco))
							.stream().map(Pedido::getProduto).findFirst().get()
					+ "\nPREÇO: %s", formatar.formatarSaidaDeValores(pedido.stream().max(Comparator.comparing(Pedido::getPreco))
							.stream().map(Pedido::getPreco).findFirst().get()));
		});
	}

}
