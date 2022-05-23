package br.com.alura.comex;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception{
    	
    	List<Pedido> pedidosCSV = ProcessadorDeCsv.leitorCSV("pedidos");
    	List<Pedido> pedidosJSON = ProcessadorDeJson.leitorJson("pedidos");
    	List<Pedido> pedidosXml = ProcessadorDeXml.leitorXml("pedidos");
    	
        RelatorioSintetico relatorioSintetico = new RelatorioSintetico(pedidosXml);
        System.out.println(relatorioSintetico);
        
        RelatorioClientesFieis.relatorioClientesFieis(pedidosXml);
        RelatorioVendasCategoria.relatorioVendasPorCategoria(pedidosXml);
        
    }
    
    
}
