package br.com.alura.comex.relatorios;

import java.util.List;

import br.com.alura.comex.Pedido;

public class TipoDeRelatorio {

	public static void imprimirRelatorioEscolhido(int opt, List<Pedido> pedidos) {
		Relatorio relatorio;
		switch (opt) {
		case 1: {
			RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);
			System.out.println(relatorioSintetico);
			break;
		}
		case 2: {
			RelatorioClientesFieis.relatorioClientesFieis(pedidos);
			break;
		}
		case 3: {
			relatorio = new RelatorioVendasCategoria();
			relatorio.imprimirRelatorio(pedidos, System.out::println);
			break;
		}
		case 4: {
			relatorio = new RelatorioProdutosMaisVendidos();
			relatorio.imprimirRelatorio(pedidos, System.out::println);
			break;
		}
		case 5: {
			relatorio = new RelatorioProdutoMaisCaroCategoria();
			relatorio.imprimirRelatorio(pedidos, System.out::println);
			break;
		}
		default:
			System.out.println("Opção inválida");
		}
	}

}
