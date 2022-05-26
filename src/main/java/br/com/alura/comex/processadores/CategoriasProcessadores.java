package br.com.alura.comex.processadores;

public enum CategoriasProcessadores {

	CSV (new ProcessadorDeCsv()),
	JSON (new ProcessadorDeJson()),
	XML (new ProcessadorDeXml());
	
	 private final Processador processador;
	 
	    CategoriasProcessadores(Processador processador) {
	        this.processador = processador;
	    }
	    public Processador getProcessador() {
	        return processador;
	    }
}
