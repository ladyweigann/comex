package br.com.alura.comex.processadores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.com.alura.comex.Pedido;

public interface Processador {

	List<Pedido> leitorArquivo(String nomeArquivo) throws IOException, URISyntaxException;
	
	
	
}
