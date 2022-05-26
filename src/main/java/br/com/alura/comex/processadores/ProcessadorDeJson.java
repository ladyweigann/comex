package br.com.alura.comex.processadores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.alura.comex.Pedido;

public class ProcessadorDeJson implements Processador{

	@Override
	public List<Pedido> leitorArquivo(String nomeArquivo) throws IOException, URISyntaxException {
		
		URL recursoJson = ClassLoader.getSystemResource(nomeArquivo + ".json");

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(recursoJson, new TypeReference<List<Pedido>>() {
		});

	}
}
