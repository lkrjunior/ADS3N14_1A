package estrutura;

import model.Contador;

public class ArvoreAVL 
{			 
    public int num;
    public int alturaDireita;
    public int alturaEsquerda;
    public ArvoreAVL direita;
    public ArvoreAVL esquerda;
 
    public ArvoreAVL inserir(ArvoreAVL aux, int num, Contador cont) 
    {
    	ArvoreAVL novo;
        if (aux == null || aux.num == 0) 
        {
            novo = new ArvoreAVL();
            novo.num = num;
            novo.alturaDireita = 0;
            novo.alturaEsquerda = 0;
            novo.esquerda = null;
            novo.direita = null;
            aux = novo;
        } 
        else if (num < aux.num) 
        {
        	cont.setComparacoes(cont.getComparacoes() + 1);
            aux.esquerda = inserir(aux.esquerda, num, cont);
            if (aux.esquerda.alturaDireita > aux.esquerda.alturaEsquerda) 
            {
                aux.alturaEsquerda = aux.esquerda.alturaDireita + 1;
            } 
            else 
            {
                aux.alturaEsquerda = aux.esquerda.alturaEsquerda + 1;
            }
            aux = balanceamento(aux, cont);
        } 
        else 
        {
        	cont.setComparacoes(cont.getComparacoes() + 1);
            aux.direita = inserir(aux.direita, num, cont);
            if (aux.direita.alturaDireita > aux.direita.alturaEsquerda) 
            {
                aux.alturaDireita = aux.direita.alturaDireita + 1;
            } 
            else 
            {
                aux.alturaDireita = aux.direita.alturaEsquerda + 1;
            }
            aux = balanceamento(aux, cont);
        }
        return aux;
    }
 
    public ArvoreAVL balanceamento(ArvoreAVL aux, Contador cont) 
    {
        int d, df;
        d = aux.alturaDireita - aux.alturaEsquerda;
        if (d == 2) 
        {
            df = aux.direita.alturaDireita - aux.direita.alturaEsquerda;
            if (df >= 0) 
            {
            	cont.setRotacoes(cont.getRotacoes() + 1);
                aux = rotacaoEsquerda(aux);
            } 
            else 
            {
            	cont.setRotacoes(cont.getRotacoes() + 2);
                aux.direita = rotacaoDireita(aux.direita);
                aux = rotacaoEsquerda(aux);
            }
        } 
        else if (d == -2) 
        {
            df = aux.esquerda.alturaDireita - aux.esquerda.alturaEsquerda;
            if (df <= 0) 
            {
            	cont.setRotacoes(cont.getRotacoes() + 1);
                aux = rotacaoDireita(aux);
            } 
            else 
            {
            	cont.setRotacoes(cont.getRotacoes() + 2);
                aux.esquerda = rotacaoEsquerda(aux.esquerda);
                aux = rotacaoDireita(aux);
            }
        }
        return aux;
    }
 
    public ArvoreAVL rotacaoEsquerda(ArvoreAVL aux) 
    {
    	ArvoreAVL aux1, aux2;
        aux1 = aux.direita;
        aux2 = aux1.esquerda;
        aux.direita = aux2;
        aux1.esquerda = aux;
        if (aux.direita == null) 
        {
            aux.alturaDireita = 0;
        } 
        else if (aux.direita.alturaEsquerda > aux.direita.alturaDireita) 
        {
            aux.alturaDireita = aux.direita.alturaEsquerda + 1;
        } 
        else 
        {
            aux.alturaDireita = aux.direita.alturaDireita + 1;
        }
 
        if (aux1.esquerda.alturaEsquerda > aux1.esquerda.alturaDireita) 
        {
            aux1.alturaEsquerda = aux1.esquerda.alturaEsquerda + 1;
        } 
        else 
        {
            aux1.alturaEsquerda = aux1.esquerda.alturaDireita + 1;
        }
        return aux1;
    }
 
    public ArvoreAVL rotacaoDireita(ArvoreAVL aux) 
    {
    	ArvoreAVL aux1, aux2;
        aux1 = aux.esquerda;
        aux2 = aux1.direita;
        aux.esquerda = aux2;
        aux1.direita = aux;
        if (aux.esquerda == null) 
        {
            aux.alturaEsquerda = 0;
        } 
        else if (aux.esquerda.alturaEsquerda > aux.esquerda.alturaDireita) 
        {
            aux.alturaEsquerda = aux.esquerda.alturaEsquerda + 1;
        } 
        else 
        {
            aux.alturaEsquerda = aux.esquerda.alturaDireita + 1;
        }
 
        if (aux1.direita.alturaEsquerda > aux1.direita.alturaDireita) 
        {
            aux1.alturaDireita = aux1.direita.alturaEsquerda + 1;
        } 
        else 
        {
            aux1.alturaDireita = aux1.direita.alturaDireita + 1;
        }
        return aux1;
    }
 
    public void infixa(ArvoreAVL aux) 
    {
        if (aux != null) 
        {
        	infixa(aux.esquerda);
            System.out.print(aux.num + "\n");
            infixa(aux.direita);
        }
    }
 
    public void prefixa(ArvoreAVL aux) 
    {
        if (aux != null) 
        {
            System.out.print(aux.num + "\n");
            prefixa(aux.esquerda);
            prefixa(aux.direita);
        }
    }
 
    public void posfixa(ArvoreAVL aux) 
    {
        if (aux != null) 
        {
        	posfixa(aux.esquerda);
        	posfixa(aux.direita);
            System.out.print(aux.num + "\n");
        }
    }
 
    public ArvoreAVL excluir(ArvoreAVL aux, int num) 
    {
    	ArvoreAVL p, p2;
        if (aux.num == num) 
        {
            if (aux.esquerda == aux.direita) 
            {
                return null;
            } 
            else if (aux.esquerda == null) 
            {
                return aux.direita;
            } 
            else if (aux.direita == null) 
            {
                return aux.esquerda;
            } 
            else 
            {
                p2 = aux.direita;
                p = aux.direita;
                while (p.esquerda != null) 
                {
                    p = p.esquerda;
                }
                p.esquerda = aux.esquerda;
                return p2;
            }
        } 
        else if (aux.num < num) 
        {
            aux.direita = excluir(aux.direita, num);
        } 
        else 
        {
            aux.esquerda = excluir(aux.esquerda, num);
        }
        return aux;
    }
 
    public ArvoreAVL atualizar(ArvoreAVL aux, Contador cont) 
    {
        if (aux != null) 
        {
            aux.esquerda = atualizar(aux.esquerda, cont);
            if (aux.esquerda == null) 
            {
                aux.alturaEsquerda = 0;
            } 
            else if (aux.esquerda.alturaEsquerda > aux.esquerda.alturaDireita) 
            {
                aux.alturaEsquerda = aux.esquerda.alturaEsquerda + 1;
            } 
            else 
            {
                aux.alturaEsquerda = aux.esquerda.alturaDireita + 1;
            }
            
            aux.direita = atualizar(aux.direita, cont);
            if (aux.direita == null) 
            {
                aux.alturaEsquerda = 0;
            } 
            else if (aux.direita.alturaEsquerda > aux.direita.alturaDireita) 
            {
                aux.alturaDireita = aux.direita.alturaEsquerda + 1;
            } 
            else 
            {
                aux.alturaDireita = aux.direita.alturaDireita + 1;
            }
            aux = balanceamento(aux, cont);
        }
        return aux;
    }
 
    public boolean consultar(ArvoreAVL aux, int num, boolean loc, Contador cont) 
    {
        if (aux != null && loc == false) 
        {
        	cont.setComparacoes(cont.getComparacoes() + 1);
            if (aux.num == num) 
            {
                loc = true;
            } 
            else if (num < aux.num) 
            {
                loc = consultar(aux.esquerda, num, loc, cont);
            } 
            else 
            {
                loc = consultar(aux.direita, num, loc, cont);
            }
        }
        return loc;
    }
}
