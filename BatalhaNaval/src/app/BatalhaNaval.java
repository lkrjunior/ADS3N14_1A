package app;

import java.util.Scanner;

import model.Jogador;
import model.Letras;
import view.BatalhaView;
import controller.Matriz;

public class BatalhaNaval 
{

	public static void main(String[] args) 
	{
		Scanner entrada = new Scanner(System.in);
		
		Matriz mat = new Matriz();
		BatalhaView v = new BatalhaView();
		v.Imprime(mat.batalha);
		
		mat.MontaNavios();
		//v.Imprime(mat.navios);
		Jogador jogador = new Jogador();
		
		while (jogador.getPontos() > 0 || mat.getDestruidos() > 0)
		{
			if (jogador.getPontos() == 0 || mat.getDestruidos() == 0)
				break;
			v.ImprimeTexto("");
			v.ImprimeTexto("Escolha uma coluna:");
			String coluna = entrada.next();
			
			v.ImprimeTexto("Escolha uma linha:");
			int linha = entrada.nextInt();
			
			if (linha <= 9 && linha >= 0 && Letras.BuscaNumero(coluna) <= 9 && Letras.BuscaNumero(coluna) >= 0)
			{
				if (mat.batalha[linha][Letras.BuscaNumero(coluna)] == ".")
				{
					jogador.setPontos(jogador.getPontos() - 1);
					
					if (mat.navios[linha][Letras.BuscaNumero(coluna)] == "O")
					{
						v.ImprimeTexto("Você acertou!");
						mat.batalha[linha][Letras.BuscaNumero(coluna)] = "O";
						
						if (mat.VerificaDestruido(linha, Letras.BuscaNumero(coluna)))
						{	
							v.ImprimeTexto("Você destruiu um navio!");
							jogador.setPontos(jogador.getPontos() + 5);
						}
						else
						{
							jogador.setPontos(jogador.getPontos() + 3);
						}
					}
					else
					{
						v.ImprimeTexto("Você errou!");
						mat.batalha[linha][Letras.BuscaNumero(coluna)] = "-";
					}
				}
				else
				{
					v.ImprimeTexto("Você já jogou neste ponto!");
				}
			}
			else
			{
				v.ImprimeTexto("Ponto inválido!");
			}
			v.Imprime(mat.batalha);
			v.ImprimePontos(jogador);
		}
		if (jogador.getPontos() == 0)
			v.ImprimeTexto("Você PERDEU!");	
		else
		{
			v.ImprimeTexto("Parabéns, você GANHOU!");
		}
		
		entrada.close();
	}

}
