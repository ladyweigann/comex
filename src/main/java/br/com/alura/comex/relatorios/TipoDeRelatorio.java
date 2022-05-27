package br.com.alura.comex.relatorios;

import java.util.List;

import br.com.alura.comex.Pedido;

public class TipoDeRelatorio {


	public static void imprimirRelatorioEscolhido(int opt, List<Pedido> pedidos) {
		
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
			RelatorioVendasCategoria.relatorioVendasPorCategoria(pedidos);
			break;
		}
		case 4: {
			RelatorioProdutosMaisVendidos.relatorioProdutosMaisVendidos(pedidos);
			break;
		}
		case 5: {
			RelatorioProdutoMaisCaroCategoria.relatorioProdutoMaisCaroCategoria(pedidos);
			break;
		}
		default:
			System.out.println("Opção inválida");
		}
	}

}
