package view;

import java.util.Scanner;

import model.Pessoa;

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
		System.out.println("<5> Busca");
		System.out.println("<6> Busca em largura");
		System.out.println("<7> Busca em profundidade");
		System.out.println("<9> Sair");
	}
	
	public void SubMenu()
	{
		System.out.println("<SUB-MENU>");
		System.out.println("<1> Remover");
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
	
	public Pessoa InserirContato() throws Exception
	{
		Pessoa p = new Pessoa();
		String nome = "";
		String telefone = "";
		int ativo = 1;
		
		System.out.println("Informe o nome:");
		nome = entrada.nextLine();
		
		System.out.println("Informe o telefone:");
		telefone = entrada.nextLine();
		
		p.setNome(nome.toUpperCase());
		p.setTelefone(telefone.toUpperCase());
		p.setAtivo(ativo);
		
		return p;
	}
	
	public void ViewPessoa(Pessoa p)
	{
		System.out.println("Nome: " + p.getNome());
		System.out.println("Telefone: " + p.getTelefone());
		System.out.println("");
	}
	
	public void PosInsercao(int pAltura, int pNumeroItens)
	{
		System.out.println("Altura: " + pAltura);
		System.out.println("Itens: " + pNumeroItens);
		System.out.println("");
	}
	
	public String Pesquisa()
	{
		String pesquisa;
		
		System.out.println("Pesquisa: ");
		pesquisa = entrada.nextLine();
		
		return pesquisa;
	}
	
	public void Comparacoes(int comparacoes)
	{
		System.out.println("Comparações: " + comparacoes);
		System.out.println("");
	}
	
	public void NaoAchou()
	{
		System.out.println("Nenhum contato encontrado!");
		System.out.println("");
	}
	
	public void Exclusao()
	{
		System.out.println("Contato excluído com sucesso!");
		System.out.println("");
	}
	
}
