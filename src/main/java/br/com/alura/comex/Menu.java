
package br.com.alura.comex;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.alura.comex.processadores.ProcessadorDeCsv;
import br.com.alura.comex.processadores.ProcessadorDeJson;
import br.com.alura.comex.processadores.ProcessadorDeXml;
import br.com.alura.comex.relatorios.RelatorioClientesFieis;
import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.relatorios.RelatorioVendasCategoria;

public class Menu {

	public static void exibirMenu()  throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"Bem-vindo ao Comex\n\nDigite a opção correspondente ao tipo de arquivo que será lido: \n1 - CSV;\n2 - JSON;\n3 - XML");
		int opt = sc.nextInt();
		System.out.println("Digite o nome do seu arquivo (sem extensão)");
		String nomeArquivo = sc.next();
		List<Pedido> pedidos = new ArrayList<>();

		pedidos = getTipoDeProcessador(opt, nomeArquivo, pedidos);

		System.out.println(
				"Escolha o tipo de relatório que será impresso:\n1 - Relatório Sintético;\n2 - Relatório de Clientes Fiéis;\n3 - Relatório de Vendas por Categoria");
		opt = sc.nextInt();

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

		 sc.close();
	}

	private static List<Pedido> getTipoDeProcessador(int opt, String nomeArquivo, List<Pedido> pedidos)
			throws IOException, URISyntaxException, Exception {
		switch (opt) {
		case 1: {
			pedidos = ProcessadorDeCsv.leitorCSV(nomeArquivo);
			break;
		}
		case 2: {
			pedidos = ProcessadorDeJson.leitorJson(nomeArquivo);
			break;
		}
		case 3: {
			pedidos = ProcessadorDeXml.leitorXml(nomeArquivo);
			break;
		}
		default:
			System.out.println("Opção inválida");
		}
		return pedidos;
	}
}
