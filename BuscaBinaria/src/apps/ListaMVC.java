package apps;

import java.io.IOException;
import java.util.Scanner;

import views.ConsoleView;
import estruturas.BuscaBinaria;
import estruturas.ListaOrdenada;
import estruturas.Nodo;

import ArquivosTxt.*;

public class ListaMVC 
{

	public static void main(String[] args) 
	{
		//Controller controller = new Controller();
		//controller.iniciaLista();
		//controller.imprimeLista();
		
		//Constante
		final String nomeArquivo = "contatos.txt";
		
		//Lista
		ListaOrdenada<String> lista = new ListaOrdenada<String>();
		ConsoleView view = new ConsoleView();
		
		//Variavies locais
		Scanner entrada = new Scanner(System.in);
		String nome;
		String telefone;
		String inserirArquivo;
		int opcao = 0;
		int ativoCad;
		
		//Lê o arquivo e insere na lista;
		Arq arq = new Arq();
		try 
		{
			arq = Arquivo.LerArquivo(nomeArquivo);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		for (int i = 0; i < arq.numeroLinhas; i++)
		{
			String linha;
			try
			{
				linha = arq.texto[i].toString();
			}
			catch (NullPointerException n)
			{
				linha = "";
			}
					
			if (!linha.isEmpty())
			{
				String array[] = new String[3];  
				array = linha.split("\\|");
				int ativo = Integer.parseInt(array[2]);
				
				if (ativo == 1)
				{
					Nodo<String> novo = new Nodo<String>();
					novo.setChave(array[0] + " - " + array[1]);
					novo.setChaveArq(linha);
					lista.insert(novo);
				}	
			}
		}
		
		do 
		{
			System.out.println("Digite sua opção:");
			System.out.println("1 - Incluir contato");
			System.out.println("2 - Navegar nos contatos");
			System.out.println("3 - Buscar contato");
			System.out.println("4 - Exibir contatos");
			System.out.println("5 - Busca Binária");
			System.out.println("9 - Sair");
			try
			{
				opcao = entrada.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("Informe um número inteiro");
				opcao = 0;
			}	
			
			switch (opcao) 
			{
				case 1:
					System.out.println("Informe o nome:");
					nome = entrada.next();
					
					System.out.println("Informe o telefone:");
					telefone = entrada.next();
					
					ativoCad = 1;
					
					inserirArquivo = nome.toUpperCase() + "|" + telefone.toUpperCase() + "|" + ativoCad;
					
					try 
					{
						Arquivo.EscreverArquivo(nomeArquivo, inserirArquivo);
					} 
					catch (IOException e) 
					{
					
						e.printStackTrace();
					}
					
					Nodo<String> novo = new Nodo<String>();
					novo.setChave(nome.toUpperCase() + " - " + telefone.toUpperCase());
					novo.setChaveArq(inserirArquivo);
					lista.insert(novo);
					
					break;
					
				case 2:
					Nodo<String> avancar = lista.getHead();
					Nodo<String> voltar = lista.getHead();
					if (avancar != null)
						view.imprimeString(avancar.getChave());
					int opcao2 = 0;
					
					do
					{
						System.out.println("1 - Avançar");
						System.out.println("2 - Voltar");
						System.out.println("3 - Excluir");
						System.out.println("9 - Sair");
						
						try
						{
							opcao2 = entrada.nextInt();
						}
						catch(Exception e)
						{
							System.out.println("Informe um número inteiro");
							opcao = 0;
						}	
						
						switch (opcao2) 
						{
							case 1:
								if (avancar != null)
								{
									if (avancar == lista.getTail())
									{
										System.out.println("Fim da lista");
										view.imprimeString(avancar.getChave());
									}
									else
									{
										voltar = avancar;
										avancar = avancar.getNext();
										avancar.setAnterior(voltar);
										view.imprimeString(avancar.getChave());
									}
								}
								
								break;
							case 2:
								if (avancar != null)
								{
									if (avancar == lista.getHead())
									{
										System.out.println("Você já está no ínicio da lista");
										view.imprimeString(avancar.getChave());
									}
									else
									{
										avancar = avancar.getAnterior();
										view.imprimeString(avancar.getChave());
									}
								}
								break;
								
							case 3:
								//Exclui do arquivo
								String excluir;
								excluir = avancar.getChaveArq();
								excluir = excluir.substring(0, excluir.length() -1 );
								excluir = excluir + "0";
								try 
								{
									Arquivo.AlterarArquivo(nomeArquivo, avancar.getChaveArq(), excluir);
								} 
								catch (IOException e) 
								{
									e.printStackTrace();
								}
								//Exclui da lista
								avancar = lista.remove(avancar);
								System.out.println("Contato removido!");
								view.imprimeString(avancar.getChave());
								opcao2 = 9;
								break;
						}
						
					}
					while (opcao2 !=9);
					
					break;
				
				case 3:
					System.out.println("Informe o nome para busca:");
					String busca = entrada.next();
					Nodo<String> buscaNodo = new Nodo<String>();
					buscaNodo.setChave(busca.toUpperCase());
					Nodo<String> resultado = new Nodo<String>();
					resultado = lista.Busca(buscaNodo);
					view.imprimeString(resultado.getChave());
					break;
				
				case 4:
					Nodo<String> nodo = lista.getHead();
					while (nodo != null) 
					{
						view.imprimeString(nodo.getChave());
						nodo = nodo.getNext();
					}
					
					break;
					
				case 5:
					System.out.println("Informe o nome para busca binária:");
					String buscaBin = entrada.next();
					Nodo<String> nodoBusca = null;
					
					BuscaBinaria bb = new BuscaBinaria();
					
					bb.Pesquisar(nodoBusca, lista, buscaBin.toUpperCase());
					
					if (bb.getPosicaoEncontrada() == -1)
						System.out.println("Palavra não encontrada na lista!\nPosição do Vetor mais próxima: " + bb.getPosicaoMaisProxima() + "\nNome: " + bb.getNomeMaisProximo());
					else
						System.out.println("Posição do Vetor: " + bb.getPosicaoEncontrada() + "\nComparações: " + bb.getComparacoes());;
					
					break;
			}
			
		}
		while (opcao != 9);
		entrada.close();
	}
}
