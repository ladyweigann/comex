package br.com.alura.comex;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException{
    	
    	List<Pedido> pedidos = ProcessadorDeCsv.leitorCSV("pedidos");
    	
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidos);
        System.out.println(relatorioSintetico);
        
        RelatorioClientesFieis.relatorioClientesFieis(pedidos);
        RelatorioVendasCategoria.relatorioVendasPorCategoria(pedidos);
        
    }
    
    
}
