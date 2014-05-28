package view;

import java.util.Scanner;

public class View 
{
	Scanner entrada = new Scanner(System.in);
	
	public void Dispose()
	{
		entrada.close();
	}
	
	public void Menu()
	{
		System.out.println("<MENU>");
		System.out.println("<1> Inserir");
		System.out.println("<2> Prefixa");
		System.out.println("<3> Infixa");
		System.out.println("<4> Posfixa");
		System.out.println("<5> Remover");
		System.out.println("<9> Sair");
	}
		
	public int LerOpcao()
	{	
		int opcao = 0;
		
		try
		{
			opcao = entrada.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Informe um número inteiro");
			opcao = 0;
		}	
		entrada.nextLine();
		
		return opcao;
	}
	
	public int LerNumero(boolean incluir)
	{	
		int numero = 0;
		
		if (incluir)
			System.out.println("Informe um número para inserir na arvore: ");
		else
			System.out.println("Informe um número para remover da arvore: ");
		try
		{
			numero = entrada.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Informe um número inteiro");
			numero = 0;
		}	
		entrada.nextLine();
		
		return numero;
	}
		
	public void Informacoes(int comparacoes, int rotacoes, String arvore)
	{
		System.out.println("Arvore: " + arvore);
		System.out.println("Comparações: " + comparacoes);
		System.out.println("Rotações: " + rotacoes);
		System.out.println("");
	}
	
	public void NaoAchou()
	{
		System.out.println("Número não encontrado!");
		System.out.println("");
	}
	
	public void Exclusao()
	{
		System.out.println("Número excluído com sucesso!");
		System.out.println("");
	}
	
}
