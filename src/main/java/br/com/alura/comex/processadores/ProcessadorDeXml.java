package br.com.alura.comex.processadores;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.alura.comex.Pedido;

public class ProcessadorDeXml implements Processador{

	@Override
	public List<Pedido> leitorArquivo(String nomeArquivo) throws IOException, URISyntaxException {
		
		URL recursoXml = ClassLoader.getSystemResource(nomeArquivo + ".xml");

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.registerModule(new JavaTimeModule());

		return xmlMapper.readValue(recursoXml, new TypeReference<List<Pedido>>() {
		});

	}
}
