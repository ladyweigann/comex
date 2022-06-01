package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import br.com.alura.comex.FormatarSaidaDeValores;
import br.com.alura.comex.Pedido;

public class RelatorioClientesMaisLucrativos extends Relatorio{

private List<ClientesMaisLucrativos> clientesMaisLucrativos;
	
	
	public List<ClientesMaisLucrativos> getClientesMaisLucrativos() {
		return clientesMaisLucrativos;
	}
	
	@Override
	public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
		if (pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista está vazia");
		}
		FormatarSaidaDeValores formatar = new FormatarSaidaDeValores();
		
		clientesMaisLucrativos = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCliente, TreeMap::new, Collectors.toList()))
				.entrySet().stream().map(entry -> {
					return new ClientesMaisLucrativos(entry.getKey(), 
							entry.getValue().stream().count(), 
							entry.getValue().stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
				}).toList();
		

		clientesMaisLucrativos.stream()
			.sorted((c1, c2) -> c2.getMontanteGasto().compareTo(c1.getMontanteGasto()))
				.limit(2)
					.forEach(cliente -> impressoraDeRelatorio.accept("\nNOME: "+ cliente.getNome() 
						+ "\nNº DE PEDIDOS: " + cliente.getNumeroDePedidos() 
						+ "\nMONTANTE GASTO: " + formatar.formatarSaidaDeValores(cliente.getMontanteGasto())));
		
	}
	
	
	public class ClientesMaisLucrativos {
		
		private String nome;
		private Long numeroDePedidos;
		private BigDecimal montanteGasto;
		
		public ClientesMaisLucrativos(String nome, Long numeroDePedidos, BigDecimal montanteGasto) {
			this.nome = nome;
			this.numeroDePedidos = numeroDePedidos;
			this.montanteGasto = montanteGasto;
		}

		public String getNome() {
			return nome;
		}

		public Long getNumeroDePedidos() {
			return numeroDePedidos;
		}

		public BigDecimal getMontanteGasto() {
			return montanteGasto;
		}

		
	}
}
