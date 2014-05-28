package controller;

import model.Contador;
import view.View;
import estrutura.*;

public class ArvoreControle 
{
	int opcao = 0;
	private View view = new View();
	private ArvoreAVL arvoreAVL = new ArvoreAVL();
	private ArvoreRedBlack<Integer, Integer> arvoreRB = new ArvoreRedBlack<Integer, Integer>();
	Contador cont = new Contador();
	
	public void ExibeMenu()
	{
		do
		{
			view.Menu();
			opcao = view.LerOpcao();
			switch (opcao) 
			{
				case 1:
					Inserir();
					break;
				case 2:
					arvoreAVL.prefixa(arvoreAVL);
					break;
				case 3:
					arvoreAVL.infixa(arvoreAVL);
					break;
				case 4:
					arvoreAVL.posfixa(arvoreAVL);
					break;
				case 5:
					Excluir();
					break;
			}
		} 
		while (opcao != 9);
		
		view.Dispose();
	}
	
	public void Inserir()
	{
		int num = view.LerNumero(true);
		
		cont = new Contador("Arvore AVL");
		arvoreAVL = arvoreAVL.inserir(arvoreAVL, num, cont);
		view.Informacoes(cont.getComparacoes(), cont.getRotacoes(), cont.getArvore());
		
		cont = new Contador("Arvore RedBlack");
		arvoreRB.inserir(num, num, cont);
		view.Informacoes(cont.getComparacoes(), cont.getRotacoes(), cont.getArvore());	
	}
	
	public void Excluir()
	{
		int num = view.LerNumero(false);
		cont = new Contador("Arvore AVL");
		
		if (arvoreAVL.consultar(arvoreAVL, num, false, cont)) 
        {
			arvoreAVL = arvoreAVL.excluir(arvoreAVL, num);
			arvoreAVL = arvoreAVL.atualizar(arvoreAVL, cont);
			view.Exclusao();
			view.Informacoes(cont.getComparacoes(), cont.getRotacoes(), cont.getArvore());
			
			cont = new Contador("Arvore RedBlack");
			arvoreRB.delete(num, cont);
			view.Informacoes(cont.getComparacoes(), cont.getRotacoes(), cont.getArvore());
			
        }
		else
		{
			view.NaoAchou();
		}
	}
	
}
