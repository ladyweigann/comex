package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.relatorios.RelatorioClientesFieis;
import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.relatorios.RelatorioVendasCategoria;

public class TipoDeRelatorio {


	public static void getTipoDeRelatorio(int opt, List<Pedido> pedidos) {
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
		default:
			System.out.println("Opção inválida");
		}
	}

}
