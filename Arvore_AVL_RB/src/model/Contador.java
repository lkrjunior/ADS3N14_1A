package model;

public class Contador 
{
	private int comparacoes, rotacoes;
	private String arvore;
	
	public Contador()
	{
		comparacoes = 0;
		rotacoes = 0;
		arvore = "";
	}
	
	public Contador(String pArvore)
	{
		comparacoes = 0;
		rotacoes = 0;
		arvore = pArvore;
	}
	
	public int getComparacoes() 
	{
		return comparacoes;
	}

	public void setComparacoes(int comparacoes) 
	{
		this.comparacoes = comparacoes;
	}

	public int getRotacoes()
	{
		return rotacoes;
	}

	public void setRotacoes(int rotacoes) 
	{
		this.rotacoes = rotacoes;
	}	
	
	public String getArvore() 
	{
		return arvore;
	}

	public void setArvore(String arvore) 
	{
		this.arvore = arvore;
	}
}
