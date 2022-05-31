package br.com.alura.comex.relatorios.test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisVendidos;
import br.com.alura.comex.relatorios.RelatorioProdutosMaisVendidos.ProdutosMaisVendidos;

class RelatorioProdutosMaisVendidosTest {

	List<Pedido> pedidos = new ArrayList<>();
	RelatorioProdutosMaisVendidos relatorio = new RelatorioProdutosMaisVendidos();

	private Consumer imprimirRelatorio() {
		Consumer consumer = Mockito.mock(Consumer.class);
		relatorio.imprimirRelatorio(pedidos, consumer);
		return consumer;
	}
	
	@Test
	void geracaoDeRelatorioParaUmaListaDePedidosVaziaDeveRetornarUmaExcecao() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> relatorio.imprimirRelatorio(pedidos, null));

	}

	@Test
	void geracaoDeRelatorioComUmUnicoPedido() {
		Pedido pedido = new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30));

		pedidos = List.of(pedido);

		Consumer consumer = imprimirRelatorio();
		
		List<ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();
		
		Assertions.assertEquals("Headphone", resultado.get(0).getProduto());
		Assertions.assertEquals(1, resultado.get(0).getQuantidadeVendida());
		
		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComDoisPedidos() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Mouse", "Larissa", new BigDecimal("79"), 3,
				LocalDate.of(2022, 5, 30)));

		Consumer consumer = imprimirRelatorio();
		
		List<ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();
		
		Assertions.assertEquals("Mouse", resultado.get(0).getProduto());
		Assertions.assertEquals(3, resultado.get(0).getQuantidadeVendida());
		
		
		Mockito.verify(consumer, Mockito.times(2)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComTresOuMaisPedidosDeveExibirApenasOsTresPrimeiros() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 2,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("LIVROS", "O Colecionador de Lagrimas", "Larissa", new BigDecimal("80"), 4,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CELULARES", "iPhone 12 Pro Max", "Larissa", new BigDecimal("5999"), 5,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CASA", "Conjunto de Cama King Size", "Larissa", new BigDecimal("459"), 3,
				LocalDate.of(2022, 5, 30)));

	
		Consumer consumer = imprimirRelatorio();
		
		List<ProdutosMaisVendidos> resultado = relatorio.getProdutosMaisVendidos();
		
		
		Assertions.assertEquals("iPhone 12 Pro Max", resultado.get(2).getProduto());
		Assertions.assertEquals("O Colecionador de Lagrimas", resultado.get(1).getProduto());
		Assertions.assertEquals("Conjunto de Cama King Size", resultado.get(0).getProduto());
		Assertions.assertEquals(3, resultado.get(0).getQuantidadeVendida());
		Assertions.assertEquals(4, resultado.get(1).getQuantidadeVendida());
		Assertions.assertEquals(5, resultado.get(2).getQuantidadeVendida());
		
		Mockito.verify(consumer, Mockito.times(3)).accept(Mockito.any());

	}


}
