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
import br.com.alura.comex.relatorios.RelatorioVendasCategoria;
import br.com.alura.comex.relatorios.RelatorioVendasCategoria.VendasCategoria;

class RelatorioVendasCategoriaTest {

	List<Pedido> pedidos = new ArrayList<>();
	RelatorioVendasCategoria relatorioVendasCategoria = new RelatorioVendasCategoria();
	
	private Consumer iniciarTeste() {
		Consumer consumer = Mockito.mock(Consumer.class);
		relatorioVendasCategoria.imprimirRelatorio(pedidos, consumer);
		return consumer;
	}
	
	@Test
	void geracaoDeRelatorioParaUmaListaDePedidosVaziaDeveRetornarUmaExcecao() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> relatorioVendasCategoria.imprimirRelatorio(pedidos, null));

	}

	@Test
	void geracaoDeRelatorioComUmUnicoPedidoz() {
		Pedido pedido = new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30));

		pedidos = List.of(pedido);
		
		Consumer consumer = iniciarTeste();
		
		List<VendasCategoria> resultado = relatorioVendasCategoria.getVendasPorCategoria();
		Assertions.assertEquals("INFORMÁTICA", resultado.get(0).getCategoria());
		Assertions.assertEquals(1, resultado.get(0).getQuantidadeVendida());
		Assertions.assertEquals(new BigDecimal("150.00"), resultado.get(0).getMontante());
		
		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComMaisDeUmPedidoDaMesmaCategoria() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Mouse", "Larissa", new BigDecimal("79"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Teclado Mecanico", "Larissa", new BigDecimal("269"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Monitor Wide", "Larissa", new BigDecimal("1798"), 1,
				LocalDate.of(2022, 5, 30)));

		Consumer consumer = iniciarTeste();
		
		List<VendasCategoria> resultado = relatorioVendasCategoria.getVendasPorCategoria();
		Assertions.assertEquals("INFORMÁTICA", resultado.get(0).getCategoria());
		Assertions.assertEquals(4, resultado.get(0).getQuantidadeVendida());
		Assertions.assertEquals(new BigDecimal("2296.00"), resultado.get(0).getMontante());
		
		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComMaisDeUmPedidoDeCategoriasDiferentes() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("LIVROS", "O Colecionador de Lagrimas", "Larissa", new BigDecimal("80"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CELULARES", "iPhone 12 Pro Max", "Larissa", new BigDecimal("5999"), 2,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CASA", "Conjunto de Cama King Size", "Larissa", new BigDecimal("459"), 1,
				LocalDate.of(2022, 5, 30)));

		Consumer consumer = iniciarTeste();

		List<VendasCategoria> resultado = relatorioVendasCategoria.getVendasPorCategoria();
		
		//Ordem Alfabética na saída do relatório
		Assertions.assertEquals("CASA", resultado.get(0).getCategoria());
		Assertions.assertEquals("CELULARES", resultado.get(1).getCategoria());
		Assertions.assertEquals(2, resultado.get(1).getQuantidadeVendida());
		Assertions.assertEquals(new BigDecimal("11998.00"), resultado.get(1).getMontante());
		
		Mockito.verify(consumer, Mockito.times(4)).accept(Mockito.any());

	}

	

}
