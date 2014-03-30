package estruturas;

import java.util.ArrayList;

import model.ListaBusca;

public class BuscaBinaria 
{
	private int comparacoes;
	private int posicaoEncontrada;
	private int posicaoMaisProxima;
	private String nomeMaisProximo;
	
	public int getPosicaoMaisProxima() 
	{
		return posicaoMaisProxima;
	}

	public String getNomeMaisProximo() 
	{
		return nomeMaisProximo;
	}

	public int getComparacoes() 
	{
		return comparacoes;
	}

	public int getPosicaoEncontrada() 
	{
		return posicaoEncontrada;
	}

	public BuscaBinaria()
	{
		comparacoes = 0;
		posicaoEncontrada = -1;
		posicaoMaisProxima = -1;
		nomeMaisProximo = "";
	}
	
	public void Pesquisar(Nodo<String> nodoBusca, ListaOrdenada<String> lista, String busca)
	{
		
		nodoBusca = lista.getHead();
		
		int posicoes = 0;
		while (nodoBusca != null) 
		{
			posicoes++;
			nodoBusca = nodoBusca.getNext();
		}
		
		String[] array = new String[posicoes];
		
		nodoBusca = lista.getHead();
		
		for (int i = 0; i < posicoes; i++ )
		{
			String arraySplit[] = new String[3];  
			arraySplit = nodoBusca.getChaveArq().split("\\|");
			
			array[i] = arraySplit[0];
			nodoBusca = nodoBusca.getNext();
		}
		
		int resultado = -1;
		int inicio = 0;
		int fim = array.length - 1;
		int meio;
		ArrayList<ListaBusca> listaBusca = new ArrayList<ListaBusca>();
		
        while(inicio <= fim)
        {
        	
        	ListaBusca lb = new ListaBusca();
        	
        	comparacoes++;
        	meio = ( inicio + fim ) / 2;

        	
            lb.setNome(array[meio]);
            lb.setPosicao(meio);
            lb.setCmp(array[meio].compareTo(busca));
            listaBusca.add(lb);
        	
        	if( array[meio].compareTo(busca) < 0 )
        		inicio = meio + 1;
            else if( array[meio].compareTo(busca) > 0 )
            	fim = meio - 1;
            else
            {
                resultado = meio;
                break;
            }
        }
        
        posicaoEncontrada = resultado;
        
        int pos = Integer.MAX_VALUE;
        
        if (resultado == -1)
        {
        	
        	for(ListaBusca l : listaBusca) 
        	{  
        	     if (l.getCmp() < pos && l.getCmp() > 0)
        	     {
        	    	 pos = l.getCmp();
        	    	 posicaoMaisProxima = l.getPosicao();
        	    	 nomeMaisProximo = l.getNome();
        	     }
        	} 
        	 
        }
		
	}
}
