package br.com.alura.comex.relatorios;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioProdutosMaisVendidos {

	public static void relatorioProdutosMaisVendidos(List<Pedido> pedidos) {

		Map<String, Integer> produtos = new HashMap<>();
		
		pedidos.stream().collect(Collectors.groupingBy(Pedido::getProduto))
			.forEach((produto, quantidade) -> {
				produtos.put(produto, quantidade.stream().mapToInt(Pedido::getQuantidade).sum());

		});
		
		produtos.entrySet().stream()
			.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.limit(3)
					.forEach(produto -> {
						System.out.println("\nPRODUTO: " + produto.getKey() + "\nQUANTIDADE VENDIDA: " + produto.getValue());

		});
	}
}
