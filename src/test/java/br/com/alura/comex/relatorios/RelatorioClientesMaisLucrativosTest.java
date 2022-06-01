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
import br.com.alura.comex.relatorios.RelatorioClientesMaisLucrativos.ClientesMaisLucrativos;

class RelatorioClientesMaisLucrativosTest {

	List<Pedido> pedidos = new ArrayList<>();
	
	RelatorioClientesMaisLucrativos relatorio = new RelatorioClientesMaisLucrativos();

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
		
		List<ClientesMaisLucrativos> resultado = relatorio.getClientesMaisLucrativos();
		
		Assertions.assertEquals("Larissa", resultado.get(0).getNome());
		Assertions.assertEquals(1, resultado.get(0).getNumeroDePedidos());
		Assertions.assertEquals(new BigDecimal("150.00"), resultado.get(0).getMontanteGasto());
		

		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}
	
	@Test
	void geracaoDeRelatorioComTresPedidosDeUmUnicoCliente() {
		
		pedidos.add(new Pedido("INFORMÁTICA", "Headphone", "Larissa", new BigDecimal("150"), 1, LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("CASA", "Conjunto de Cama King", "Larissa", new BigDecimal("499"), 1, LocalDate.of(2022, 5, 30)));
		pedidos.add(new Pedido("LIVROS", "O Vendedor de Sonhos", "Larissa", new BigDecimal("89"), 1, LocalDate.of(2022, 5, 30)));


		Consumer consumer = imprimirRelatorio();
		
		List<ClientesMaisLucrativos> resultado = relatorio.getClientesMaisLucrativos();
		
		Assertions.assertEquals("Larissa", resultado.get(0).getNome());
		Assertions.assertEquals(3, resultado.get(0).getNumeroDePedidos());
		Assertions.assertEquals(new BigDecimal("738.00"), resultado.get(0).getMontanteGasto());

		Mockito.verify(consumer, Mockito.times(1)).accept(Mockito.any());

	}



}
