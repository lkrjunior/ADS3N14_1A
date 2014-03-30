package model;

public class Jogador 
{
	int pontos;
	
	public int getPontos() 
	{
		return pontos;
	}

	public void setPontos(int pontos) 
	{
		this.pontos = pontos;
	}

	public Jogador() 
	{
		pontos = 15;
	}
	
	public boolean Perdeu()
	{
		if (pontos <= 0)
			return true;
		return false;
	}

}
