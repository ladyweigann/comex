package br.com.alura.comex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioClientes {


	static public void relatorioClientesFieis(List<Pedido> pedidos) {
		
		Map<String, Long> clientes = pedidos.stream().map(Pedido::getCliente)
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		
		Map<String, Long> clientesOrdenados = clientes.entrySet()
		        .stream()
		        .sorted(Map.Entry.comparingByKey())
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		
		        System.out.println("\n\n#### RELATÓRIO DE CLIENTES FIÉIS");
		        clientesOrdenados.forEach((key, value) -> System.out.println("\nNOME: "+ key + "\nNº DE PEDIDOS: " + value));;
		
	
		
		
	}

	
}
