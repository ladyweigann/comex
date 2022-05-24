package br.com.alura.comex;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.com.alura.comex.processadores.ProcessadorDeCsv;
import br.com.alura.comex.processadores.ProcessadorDeJson;
import br.com.alura.comex.processadores.ProcessadorDeXml;

public class TipoDeProcessador {

	public static List<Pedido> getTipoDeProcessador(int opt, String nomeArquivo, List<Pedido> pedidos)
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
