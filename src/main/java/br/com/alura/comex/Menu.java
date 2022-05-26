
package br.com.alura.comex;

import java.util.List;
import java.util.Scanner;

import br.com.alura.comex.processadores.TipoDeProcessador;
import br.com.alura.comex.relatorios.TipoDeRelatorio;

public class Menu {

	public static void exibirMenu()  throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(
				"Bem-vindo ao Comex\n\nDigite a opção correspondente ao tipo de arquivo que será lido: \n1 - CSV;\n2 - JSON;\n3 - XML");
		String opt = sc.next();
		
		System.out.println("Digite o nome do seu arquivo (sem extensão)");
		
		String nomeArquivo = sc.next();

		List<Pedido> pedidos = TipoDeProcessador.getTipoDeProcessador(opt, nomeArquivo);

		System.out.println(
				"Escolha o tipo de relatório que será impresso:\n1 - Relatório Sintético;\n2 - Relatório de Clientes Fiéis;\n3 - Relatório de Vendas por Categoria");
		int opt2 = sc.nextInt();

		TipoDeRelatorio.imprimirRelatorioEscolhido(opt2, pedidos);

		sc.close();
	}

	
}
