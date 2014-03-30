package view;

import model.Jogador;

//import model.Letras;

public class BatalhaView 
{

	public void Imprime(String[][] mat)
	{
		for (int i = 0; i < mat.length; i++)
		{
			if (i == 0)
				System.out.println("  A B C D E F G H I J");
			else
				System.out.println("");
			for (int j = 0; j < mat[i].length; j++)
				if (j == 0)
					System.out.print(i + " " + mat[i][j] + " ");
				else 
					System.out.print(mat[i][j] + " ");
		}
		System.out.println("");
	}

	public void ImprimePontos(Jogador jogador)
	{
		System.out.println("Pontos: " + jogador.getPontos());
	}
	
	public void ImprimeTexto(String texto)
	{
		System.out.println(texto);
	}
	
}
