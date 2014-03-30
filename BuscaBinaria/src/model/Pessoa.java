package model;

public class Pessoa 
{
	
	private String nome;
	private String telefone;
	private int ativo;

	public Pessoa()
	{
		nome = "";
		telefone = "";
		ativo = 0;
	}
	
	public Pessoa(String pNome, String pTelefone, int pAtivo)
	{
		nome = pNome;
		telefone = pTelefone;
		ativo = pAtivo;
	}
	
	public String getNome() 
	{
		return nome;
	}
	
	public void setNome(String nome) 
	{
		this.nome = nome;
	}
	
	public String getTelefone() 
	{
		return telefone;
	}
	
	public void setTelefone(String telefone) 
	{
		this.telefone = telefone;
	}
	
	public int getAtivo() 
	{
		return ativo;
	}

	public void setAtivo(int ativo) 
	{
		this.ativo = ativo;
	}
	
}
