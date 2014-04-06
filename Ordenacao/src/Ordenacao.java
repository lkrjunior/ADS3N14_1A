
public class Ordenacao 
{
	private final int tamanho = 50;
	private final int randomico = 100;
	private int[] array = new int[tamanho];
	private int[] arrayOrdenado = new int[tamanho];
	private boolean imprimirOrdenado;
	private int comparacoes;
	
	public boolean isImprimirOrdenado() 
	{
		return imprimirOrdenado;
	}

	public void setImprimirOrdenado(boolean imprimirOrdenado) 
	{
		this.imprimirOrdenado = imprimirOrdenado;
	}
	
	public int[] getArray() 
	{
		return array;
	}

	public int[] getArrayOrdenado() 
	{
		return arrayOrdenado;
	}

	public Ordenacao() 
	{
		for (int i = 0; i < tamanho; i++) 
		{
			array[i] = (int) (Math.random() * randomico);
			arrayOrdenado[i] = array[i];
		}
		imprimirOrdenado = false;
		comparacoes = 0;
	}
	
	public void Imprimir()
	{
		for (int i = 0; i < tamanho; i++) 
		{
			if (imprimirOrdenado)
			{
				if (i == 0)
					System.out.println("Array ordenado:");
					
				System.out.print(arrayOrdenado[i]);
			}
			else 
			{
				if (i == 0)
					System.out.println("Array preenchido aleatoriamente:");
				System.out.print(array[i]);
			}
			
			if (i < (tamanho - 1))
				System.out.print(" - ");
		}
		
		System.out.println("");
		if (imprimirOrdenado)
			System.out.println("Comparações: " + comparacoes);
		System.out.println("");
	}
	
	//MergeSort
	public void mergeSort(int[] array) 
	{
        if (array.length > 1) 
        {
            int[] esquerda = metadeEsquerda(array);
            int[] direita = metadeDireita(array);
            
            mergeSort(esquerda);
            mergeSort(direita);
            
            merge(array, esquerda, direita);
        }
    }
    
    private int[] metadeEsquerda(int[] array) 
    {
        int meio = array.length / 2;
        int[] esquerda = new int[meio];
        
        for (int i = 0; i < meio; i++) 
        {
        	esquerda[i] = array[i];
        }
        
        return esquerda;
    }
    
    private int[] metadeDireita(int[] array) 
    {
        int meio1 = array.length / 2;
        int meio2 = array.length - meio1;
        int[] direita = new int[meio2];
        
        for (int i = 0; i < meio2; i++) 
        {
        	direita[i] = array[i + meio1];
        }
        
        return direita;
    }
    
    private void merge(int[] resultado, int[] esquerda, int[] direita) 
    {
        int i1 = 0;   
        int i2 = 0;   
        
        for (int i = 0; i < resultado.length; i++) 
        {
        	comparacoes++;
            if (i2 >= direita.length || (i1 < esquerda.length && esquerda[i1] <= direita[i2])) 
            {
            	//comparacoes++;
            	resultado[i] = esquerda[i1];   
                i1++;
            } 
            else 
            {
            	//comparacoes++;
            	resultado[i] = direita[i2]; 
                i2++;
            }
        }
    }
    
    //SelectionSort
    public void selectionSort(int[] array)
    {
        int menor, indiceMenor;

        for (int i = 0; i < array.length - 1; i++) 
        {
            menor = array[i];
            indiceMenor = i;

            for (int j = i + 1; j < array.length; j++)
            {
                if (array[j] < menor)
                {
                	comparacoes++;
                    menor = array[j];
                    indiceMenor = j;
                }
            }

            array[indiceMenor] = array[i];
            array[i] = menor;
        }
    }
    
}
