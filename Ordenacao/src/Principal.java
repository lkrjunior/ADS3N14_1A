
public class Principal 
{

	public static void main(String[] args) 
	{
		//Merge Sort
		Ordenacao mergeSort = new Ordenacao();
		
		mergeSort.setImprimirOrdenado(false);
		mergeSort.Imprimir();
		
		mergeSort.mergeSort(mergeSort.getArrayOrdenado());
		mergeSort.setImprimirOrdenado(true);
		System.out.println("Merge Sort");
		mergeSort.Imprimir();
		System.out.println("");
		
		//Bubble Sort
		Ordenacao bubbleSort = new Ordenacao();
		
		bubbleSort.setImprimirOrdenado(false);
		bubbleSort.Imprimir();
		
		bubbleSort.bubbleSort(bubbleSort.getArrayOrdenado());
		bubbleSort.setImprimirOrdenado(true);
		System.out.println("Bubble Sort");
		bubbleSort.Imprimir();
		System.out.println("");
		
		//Selection Sort
		Ordenacao selectionSort = new Ordenacao();
		
		selectionSort.setImprimirOrdenado(false);
		selectionSort.Imprimir();
		
		selectionSort.selectionSort(selectionSort.getArrayOrdenado());
		selectionSort.setImprimirOrdenado(true);
		System.out.println("Selection Sort");
		selectionSort.Imprimir();
	}

}
