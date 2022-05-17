package br.com.alura.comex;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioClientes {


	static public void relatorioClientesFieis(List<Pedido> pedidos) {
		
		
		Map<String, Long> clientesOrdenados = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente, Collectors.counting()))
				.entrySet()
		        .stream()
		        .sorted(Map.Entry.comparingByKey())
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (key, value) -> key, LinkedHashMap::new));
		
		
		System.out.println("\n\n#### RELATÓRIO DE CLIENTES FIÉIS");
		clientesOrdenados.forEach((key, value) -> System.out.println("\nNOME: "+ key + "\nNº DE PEDIDOS: " + value));;
		

		
	}

	
}
