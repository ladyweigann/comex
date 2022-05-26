package br.com.alura.comex.processadores;

import java.util.List;

import br.com.alura.comex.Pedido;

public class TipoDeProcessador {

	public static List<Pedido> getTipoDeProcessador(String opt, String nomeArquivo, List<Pedido> pedidos)
			throws Exception {
		opt.toUpperCase();
		switch (opt) {
		case "CSV": {
			pedidos = ProcessadorDeCsv.leitorCSV(nomeArquivo);
			break;
		}
		case "JSON": {
			pedidos = ProcessadorDeJson.leitorJson(nomeArquivo);
			break;
		}
		case "XML": {
			pedidos = ProcessadorDeXml.leitorXml(nomeArquivo);
			break;
		}
		default:
			throw new IllegalArgumentException("Opção inválida");
		}
		return pedidos;
	}
}
