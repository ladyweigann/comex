package br.com.alura.comex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioVendasCategoria {

	static public void relatorioVendasPorCategoria(List<Pedido> pedidos) {
		
		Map<String, List<Pedido>> categorias = pedidos.stream()
				.collect(Collectors.groupingBy(Pedido::getCategoria));
		
		categorias
		.forEach((categoria, pedido) -> {
			System.out.printf("\nCATEGORIA: " + categoria 
					+ "\nQUANTIDADE VENDIDA: " + pedido.stream().mapToInt(Pedido::getQuantidade).sum()
					+ "\nMONTANTE: %s\n", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(pedido.stream().map(Pedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_DOWN)));
		});
		
	}
	
}
