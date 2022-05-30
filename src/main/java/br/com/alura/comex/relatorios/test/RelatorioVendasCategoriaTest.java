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
import br.com.alura.comex.relatorios.Relatorio;
import br.com.alura.comex.relatorios.RelatorioVendasCategoria;

class RelatorioVendasCategoriaTest {

	List<Pedido> pedidos = new ArrayList<>();
	Relatorio relatorioVendasCategoria = new RelatorioVendasCategoria();

	private Consumer imprimirRelatorio() {
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
	void geracaoDeRelatorioComUmUnicoPedidoDeveExibirUmaVez() {
		Pedido pedido = new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30));

		pedidos = List.of(pedido);

		Consumer consumer = imprimirRelatorio();
		
		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComMaisDeUmPedidoDaMesmaCategoriaDeveExibirUmaVez() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Mouse", "Larissa", new BigDecimal("79"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Teclado Mecanico", "Larissa", new BigDecimal("269"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("INFORMÁTICA", "Monitor Wide", "Larissa", new BigDecimal("1798"), 1,
				LocalDate.of(2022, 5, 30)));

		Consumer consumer = imprimirRelatorio();
		
		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComMaisDeUmPedidoDeCategoriasDiferentesDeveExibirAQuantidadeDeCategorias() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("LIVROS", "O Colecionador de Lagrimas", "Larissa", new BigDecimal("80"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CELULARES", "iPhone 12 Pro Max", "Larissa", new BigDecimal("5999"), 1,
				LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CASA", "Conjunto de Cama King Size", "Larissa", new BigDecimal("459"), 1,
				LocalDate.of(2022, 5, 30)));

		Consumer consumer = imprimirRelatorio();

		Mockito.verify(consumer, Mockito.times(4)).accept(Mockito.any());

	}

	

}
