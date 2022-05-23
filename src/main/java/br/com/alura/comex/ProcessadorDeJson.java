package br.com.alura.comex;

import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ProcessadorDeJson {

	static public List<Pedido> leitorJson(String nomeArquivo) throws Exception {
		URL recursoJson = ClassLoader.getSystemResource(nomeArquivo + ".json");

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		return objectMapper.readValue(recursoJson, new TypeReference<List<Pedido>>() {
		});

	}
}
