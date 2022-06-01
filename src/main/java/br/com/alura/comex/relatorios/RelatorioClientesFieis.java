package br.com.alura.comex.relatorios;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.alura.comex.Pedido;

public class RelatorioClientesFieis extends Relatorio{

	private List<ClientesFieis> clientesFieis;
	
	
	public List<ClientesFieis> getClientesFieis() {
		return clientesFieis;
	}
	
	@Override
	public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
		if (pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista está vazia");
		}
		
		clientesFieis = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.counting()))
				.entrySet().stream().map(entry -> {
					return new ClientesFieis(entry.getKey(), entry.getValue());
				}).toList();
		

		clientesFieis.stream().forEach(cliente -> impressoraDeRelatorio.accept("\nNOME: "+ cliente.getNome() + "\nNº DE PEDIDOS: " + cliente.getNumeroDePedidos()));
		

		
	}
	
	public class ClientesFieis {
		
		private String nome;
		private Long numeroDePedidos;
		
		
		public ClientesFieis(String nome, Long numeroDePedidos) {
			this.nome = nome;
			this.numeroDePedidos = numeroDePedidos;
		}
		
		public String getNome() {
			return nome;
		}
		
		public Long getNumeroDePedidos() {
			return numeroDePedidos;
		}
		
	}

	
}
