package br.com.alura.comex.relatorios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alura.comex.Pedido;
import br.com.alura.comex.relatorios.RelatorioProdutoMaisCaroCategoria;
import br.com.alura.comex.relatorios.RelatorioProdutoMaisCaroCategoria.ProdutoMaisCaroCategoria;

class RelatorioProdutoMaisCaroCategoriaTest {

	List<Pedido> pedidos = new ArrayList<>();
	RelatorioProdutoMaisCaroCategoria relatorio = new RelatorioProdutoMaisCaroCategoria();

	private Consumer imprimirRelatorio() {
		Consumer consumer = Mockito.mock(Consumer.class);
		relatorio.imprimirRelatorio(pedidos, consumer);
		return consumer;
	}

	@Test
	void geracaoDeRelatorioParaUmaListaDePedidosVaziaDeveRetornarUmaExcecao() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> relatorio.imprimirRelatorio(pedidos, null));

	}

	@Test
	void geracaoDeRelatorioComUmUnicoPedido() {
		Pedido pedido = new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30));

		pedidos = List.of(pedido);

		Consumer consumer = imprimirRelatorio();
		
		List<ProdutoMaisCaroCategoria> resultado = relatorio.getVendasPorCategoria();
		
		Assertions.assertEquals("INFORMÁTICA", resultado.get(0).getCategoria());
		Assertions.assertEquals("Headphone", resultado.get(0).getProduto());
		Assertions.assertEquals(new BigDecimal("150"), resultado.get(0).getPreco());
		

		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}

	@Test
	void geracaoDeRelatorioComMaisDeUmPedidoDeUmaMesmaCategoria() {

		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1, LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Mouse", "Larissa", new BigDecimal("79"), 1, LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Monitor LED", "Larissa", new BigDecimal("1959"), 1, LocalDate.of(2022, 5, 30)));
		
		Consumer consumer = imprimirRelatorio();

		List<ProdutoMaisCaroCategoria> resultado = relatorio.getVendasPorCategoria();
		
		Assertions.assertEquals("INFORMÁTICA", resultado.get(0).getCategoria());
		Assertions.assertEquals("Monitor LED", resultado.get(0).getProduto());
		Assertions.assertEquals(new BigDecimal("1959"), resultado.get(0).getPreco());
		
		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}

	@Test
	void geracaoDeRelatorioComMaisDeUmPedidoDeVariasCategorias() {

		pedidos.add(
				new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1, LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("LIVROS", "O Colecionador de Lagrimas", "Larissa", new BigDecimal("80"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CELULARES", "iPhone 12 Pro Max", "Larissa", new BigDecimal("5999"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CASA", "Conjunto de Cama King Size", "Larissa", new BigDecimal("459"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CASA", "Toalhas de Banho", "Carla", new BigDecimal("99"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("LIVROS", "Senhora", "Larissa", new BigDecimal("50"), 1,
				LocalDate.of(2022, 5, 30)));
		
		
		Consumer consumer = imprimirRelatorio();

		List<ProdutoMaisCaroCategoria> resultado = relatorio.getVendasPorCategoria();
		
		Assertions.assertEquals("CASA", resultado.get(0).getCategoria());
		Assertions.assertEquals("Conjunto de Cama King Size", resultado.get(0).getProduto());
		Assertions.assertEquals(new BigDecimal("459"), resultado.get(0).getPreco());
		
		Assertions.assertEquals("CELULARES", resultado.get(1).getCategoria());
		Assertions.assertEquals("iPhone 12 Pro Max", resultado.get(1).getProduto());
		Assertions.assertEquals(new BigDecimal("5999"), resultado.get(1).getPreco());
		
		Assertions.assertEquals("INFORMÁTICA", resultado.get(2).getCategoria());
		Assertions.assertEquals("Headphone", resultado.get(2).getProduto());
		Assertions.assertEquals(new BigDecimal("150"), resultado.get(2).getPreco());
		
		Assertions.assertEquals("LIVROS", resultado.get(3).getCategoria());
		Assertions.assertEquals("O Colecionador de Lagrimas", resultado.get(3).getProduto());
		Assertions.assertEquals(new BigDecimal("80"), resultado.get(3).getPreco());
		
		Mockito.verify(consumer, Mockito.times(4)).accept(Mockito.any());

	}

}
