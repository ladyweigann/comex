package br.com.alura.comex.processadores;

import java.util.List;

import br.com.alura.comex.Pedido;

public class TipoDeProcessador {

	public static List<Pedido> getTipoDeProcessador(String opt, String nomeArquivo)
			throws Exception {
		
		 CategoriasProcessadores categoria = CategoriasProcessadores.valueOf(opt.toUpperCase());
	     Processador processador = categoria.getProcessador();
	     return processador.leitorArquivo(nomeArquivo);
	
		
	}
}
