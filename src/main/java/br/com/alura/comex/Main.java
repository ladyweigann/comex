package br.com.alura.comex;

import java.util.List;

import br.com.alura.comex.processadores.ProcessadorDeCsv;
import br.com.alura.comex.processadores.ProcessadorDeJson;
import br.com.alura.comex.processadores.ProcessadorDeXml;
import br.com.alura.comex.relatorios.RelatorioClientesFieis;
import br.com.alura.comex.relatorios.RelatorioSintetico;
import br.com.alura.comex.relatorios.RelatorioVendasCategoria;

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
