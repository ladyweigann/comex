package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioSintetico {

	private int totalDeProdutosVendidos;
	private int totalDePedidosRealizados;
	private BigDecimal montanteDeVendas = BigDecimal.ZERO;
	private Pedido pedidoMaisBarato;
	private Pedido pedidoMaisCaro;
	private long categoriasProcessadas;

	// HashSet<String> categoriasProcessadas = new HashSet<>();

	public RelatorioSintetico(List<Pedido> pedidos) {
		
		if(pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode estar vazia");
		}
		
		this.montanteDeVendas = pedidos.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.totalDeProdutosVendidos = pedidos.stream().mapToInt(Pedido::getQuantidade).sum();
		this.totalDePedidosRealizados = pedidos.size();
		this.categoriasProcessadas = pedidos.stream().map(Pedido::getCategoria).distinct().count();
		this.pedidoMaisBarato = pedidos.stream().min(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista não pode estar vazia"));
		this.pedidoMaisCaro = pedidos.stream().max(Comparator.comparing(Pedido::getValorTotal)).orElseThrow(() -> new IllegalStateException("A lista não pode estar vazia"));

	}
	
	public void relatorioClientesFieis(List<Pedido> pedidos) {
		
		//pedidos.stream().sorted((c1, c2) -> c1.getCliente().compareTo(c2.getCliente())).forEach(System.out::println);
		Map<Object, Long> collect = pedidos.stream()
				.map(Pedido::getCliente)
				.collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));
		System.out.println(collect.keySet());
		System.out.println(collect.values());
		
		//System.out.println(collect.getKey());
		
		//List<Object> clientes = new ArrayList<Object>();
		
		//clientes.add(clientes)
		
		
	}

	public int getTotalDeProdutosVendidos() {
		return totalDeProdutosVendidos;
	}

	public int getTotalDePedidosRealizados() {
		return totalDePedidosRealizados;
	}

	public BigDecimal getMontanteDeVendas() {
		return montanteDeVendas;
	}

	public long getCategoriasProcessadas() {
		return categoriasProcessadas;
	}

	public Pedido getPedidoMaisBarato() {
		return pedidoMaisBarato;
	}
	public Pedido getPedidoMaisCaro() {
		return pedidoMaisCaro;
	}


}
