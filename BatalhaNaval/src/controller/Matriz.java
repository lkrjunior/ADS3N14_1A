package controller;

import java.util.ArrayList;

import model.Navios;

public class Matriz 
{
	public final static int linhas = 10;
	public final static int colunas = 10;	
	public String[][] batalha = new String[linhas][colunas];
	public String[][] navios = new String[10][10];
	private int destruidos = 13;
	ArrayList<Navios> Navios = new ArrayList<Navios>();
	
	public int getDestruidos() 
	{
		return destruidos;
	}

	public Matriz()
	{
		for (int i = 0; i < batalha.length; i++)
			for (int j = 0; j < batalha[i].length; j++)
				batalha[i][j] = ".";
	}
	
	public void MontaNavios()
	{
		for (int i = 0; i < navios.length; i++)
			for (int j = 0; j < navios[i].length; j++)
				navios[i][j] = ".";
		
		Navios nav = new Navios(3, 2, 6);
		Navios.add(nav);
		
		Navios nav1 = new Navios(8, 6, 9);
		Navios.add(nav1);
		
		Navios nav2 = new Navios(5, 0, 3);
		Navios.add(nav2);
		
		Navios nav3 = new Navios(0, 5, 7);
		Navios.add(nav3);
		
		Navios nav4 = new Navios(8, 2, 4);
		Navios.add(nav4);
		
		Navios nav5 = new Navios(1, 6, 7);
		Navios.add(nav5);
		
		Navios nav6 = new Navios(4, 3, 4);
		Navios.add(nav6);
		
		Navios nav7 = new Navios(6, 7, 8);
		Navios.add(nav7);
		
		Navios nav8 = new Navios(2, 1, 1);
		Navios.add(nav8);
		
		Navios nav9 = new Navios(2, 8, 8);
		Navios.add(nav9);
		
		Navios nav10 = new Navios(9, 1, 1);
		Navios.add(nav10);
		
		Navios nav11 = new Navios(9, 9, 9);
		Navios.add(nav11);
		
		Navios nav12 = new Navios(5, 6, 6);
		Navios.add(nav12);
		
		for(Navios n : Navios) 
    	{  
			for (int i = n.getPosicaoIni(); i <= n.getPosicaoFim(); i++)
			{
				navios[n.getLinha()][i] = "O";
			}
    	}
		
	}
	
	public boolean VerificaDestruido(int linha, int coluna)
	{
		for(Navios n : Navios) 
    	{  
			if (linha == n.getLinha() && (coluna >= n.getPosicaoIni() && coluna <= n.getPosicaoFim()))
			{
				for (int i = n.getPosicaoIni(); i <= n.getPosicaoFim(); i++)
				{
					if (batalha[n.getLinha()][i] != "O")
						return false;
				}
				destruidos--;
				return true;
			}
    	}
		return false;
	}
}
