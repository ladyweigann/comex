package br.com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioProdutoMaisCaro {

	static public void relatorioProdutoMaisCaro(List<Pedido> pedidos) {
		
		Map<String, List<Pedido>> categorias = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria));

	}
	
}
