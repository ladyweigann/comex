package br.com.alura.comex.relatorios;

import java.util.List;

import br.com.alura.comex.Pedido;

public abstract class Relatorio {
	
	public abstract void imprimirRelatorio(List<Pedido> pedidos);
}
