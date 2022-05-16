package br.com.alura.comex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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
		
		List<String> clientes = pedidos.stream().map(Pedido::getCliente).sorted(Comparator.naturalOrder()).toList();
		
		List<String> teste = new ArrayList<>();
		
		for (int i = 0; i < clientes.size(); i++) {
			teste.add(clientes.get(i));
		}
		
		boolean controle = true;
		int contador = 1;
		String nome;
		
		System.out.println("\n\n#### RELATÓRIO DE CLIENTES FIÉIS");
		
		while(controle) {
			
			controle = false;
			
			for (int i = 0; i < (teste.size()-1); i++) {
				nome = teste.get(i);
				
				if(teste.get(i).equals(teste.get(i+1))) {
					teste.remove(i+1);
					contador++;
					controle = true;
					break;
				}else {
					System.out.println("\nNOME: " + nome + "\nNº DE PEDIDOS: " + contador);
					controle = true;
				}
				
				teste.remove(i);
				contador = 1;
				
			}
			
		}
		System.out.println("\nNOME: " + teste.get(0) + "\nNº DE PEDIDOS: " + contador);
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
