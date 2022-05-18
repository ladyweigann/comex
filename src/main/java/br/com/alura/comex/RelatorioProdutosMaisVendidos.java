package br.com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioProdutosMaisVendidos {

static public void relatorioProdutosMaisVendidos(List<Pedido> pedidos) {
		
	Map<String, List<Pedido>> produtos = pedidos.stream()
			.collect(Collectors.groupingBy(Pedido::getProduto));
	
	produtos.forEach((produto, pedido) -> {
		System.out.println("\nPRODUTO: " + produto + "\nQUANTIDADE: " + pedido.stream().mapToInt(Pedido::getQuantidade).sum());
	});
	
	}
	
}
