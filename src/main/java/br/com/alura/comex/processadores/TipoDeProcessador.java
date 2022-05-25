package br.com.alura.comex.processadores;

import java.util.List;

import br.com.alura.comex.Pedido;

public class TipoDeProcessador {

	public static List<Pedido> getTipoDeProcessador(int opt, String nomeArquivo, List<Pedido> pedidos)
			throws Exception {
		
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
