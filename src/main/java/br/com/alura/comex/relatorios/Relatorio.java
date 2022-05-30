package br.com.alura.comex.relatorios;

import java.util.List;
import java.util.function.Consumer;

import br.com.alura.comex.Pedido;

public abstract class Relatorio {

	public void imprimirRelatorio(List<Pedido> pedidos, Consumer<String> impressoraDeRelatorio) {
		if (pedidos == null || pedidos.isEmpty()) {
			throw new IllegalArgumentException("A lista está vazia");
		}
	}
}
