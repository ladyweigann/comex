package br.com.alura.comex;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RelatorioClientesFieis {


	static public void relatorioClientesFieis(List<Pedido> pedidos) {
		
		
		Map<String, Long> clientesOrdenados = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()));
		
		
		System.out.println("\n\n#### RELATÓRIO DE CLIENTES FIÉIS");
		clientesOrdenados.forEach((cliente, pedido) -> System.out.println("\nNOME: "+ cliente + "\nNº DE PEDIDOS: " + pedido));
		

		
	}

	
}
