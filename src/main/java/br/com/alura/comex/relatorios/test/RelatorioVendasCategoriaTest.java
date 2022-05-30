package br.com.alura.comex.relatorios.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.relatorios.RelatorioVendasCategoria;

class RelatorioVendasCategoriaTest {

	@Test
	void geracaoDeRelatorioParaUmaListaDePedidosVazia() {
		List<Pedido> pedidos = new ArrayList<>();
		
		RelatorioVendasCategoria relatorioVendasCategoria = new RelatorioVendasCategoria();
		
		
	}

}
