package model;

public class Navios 
{
	
	int posicaoIni;
	int posicaoFim;
	int linha;
	
	public int getPosicaoIni() 
	{
		return posicaoIni;
	}

	public int getPosicaoFim() 
	{
		return posicaoFim;
	}

	public int getLinha() 
	{
		return linha;
	}

	public Navios(int pLinha, int pPosicaoIni, int pPosicaoFim) 
	{
		linha = pLinha;
		posicaoIni = pPosicaoIni;
		posicaoFim = pPosicaoFim;
	}

}
