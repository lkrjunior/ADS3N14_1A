package model;

public class ListaBusca 
{
	private String nome;
	private int posicao;
	private int cmp;
	
	public ListaBusca()
	{
		nome = "";
		posicao = -1;
		cmp = 0;
	}
	
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public int getPosicao() 
	{
		return posicao;
	}
	public void setPosicao(int posicao)
	{
		this.posicao = posicao;
	}
	public int getCmp() 
	{
		return cmp;
	}
	public void setCmp(int cmp) 
	{
		this.cmp = cmp;
	}
	
}
