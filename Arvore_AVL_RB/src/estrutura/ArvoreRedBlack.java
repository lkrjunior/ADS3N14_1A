package estrutura;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import model.Contador;

public class ArvoreRedBlack<K extends Comparable<K>, T> 
{
	private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Nodo raiz;     

    private class Nodo 
    {
        private K chave;           
        private T valor;         
        private Nodo esquerda, direita;  
        private boolean cor;     
        private int N;             

        public Nodo(K chave, T valor, boolean cor, int N) 
        {
            this.chave = chave;
            this.valor = valor;
            this.cor = cor;
            this.N = N;
        }
    }

    private boolean isRed(Nodo x) 
    {
        if (x == null) 
        	return false;
        return (x.cor == RED);
    }

    private int tamanho(Nodo x) 
    {
        if (x == null) 
        	return 0;
        return x.N;
    } 


    public int tamanho() 
    { 
        return tamanho(raiz); 
    }

    public boolean vazio() 
    {
        return raiz == null;
    }

    public T get(K chave) 
    { 
        return get(raiz, chave); 
    }

    private T get(Nodo x, K chave) 
    {
        while (x != null) 
        {
            int cmp = chave.compareTo(x.chave);
            
            if (cmp < 0) 
            	x = x.esquerda;
            else if (cmp > 0) 
            	x = x.direita;
            else
            	return x.valor;
        }
        return null;
    }

    public boolean contem(K chave) 
    {
        return (get(chave) != null);
    }

    private boolean contem(Nodo x, K chave) 
    {
        return (get(x, chave) != null);
    }

    public void inserir(K chave, T valor, Contador cont) 
    {
    	raiz = inserir(raiz, chave, valor, cont);
    	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo inserir(Nodo h, K chave, T valor, Contador cont) 
    { 
        if (h == null) 
        	return new Nodo(chave, valor, RED, 1);

        int cmp = chave.compareTo(h.chave);
        cont.setComparacoes(cont.getComparacoes() + 1);
        
        if (cmp < 0) 
        	h.esquerda  = inserir(h.esquerda,  chave, valor, cont); 
        else if (cmp > 0) 
        	h.direita = inserir(h.direita, chave, valor, cont); 
        else
        	h.valor   = valor;

        if (isRed(h.direita) && !isRed(h.esquerda))      
        {
        	cont.setRotacoes(cont.getRotacoes() + 1);
        	h = rotacaoEsquerda(h);
        }
        if (isRed(h.esquerda)  &&  isRed(h.esquerda.esquerda)) 
        {
        	cont.setRotacoes(cont.getRotacoes() + 1);
        	h = rotacaoDireita(h);
        }
        if (isRed(h.esquerda)  &&  isRed(h.direita))     
        	trocarCor(h);
        h.N = tamanho(h.esquerda) + tamanho(h.direita) + 1;

        return h;
    }

    public void deleteMin() 
    {
        if (vazio()) 
        	throw new NoSuchElementException("Underflow");

        if (!isRed(raiz.esquerda) && !isRed(raiz.direita))
        	raiz.cor = RED;

        raiz = deleteMin(raiz);
        if (!vazio()) 
        	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo deleteMin(Nodo h) 
    { 
        if (h.esquerda == null)
            return null;

        if (!isRed(h.esquerda) && !isRed(h.esquerda.esquerda))
            h = moverEsquerda(h);

        h.esquerda = deleteMin(h.esquerda);
        return balancear(h);
    }


    public void deleteMax() 
    {
        if (vazio()) 
        	throw new NoSuchElementException("Underflow");

        if (!isRed(raiz.esquerda) && !isRed(raiz.direita))
        	raiz.cor = RED;

        raiz = deleteMax(raiz);
        if (!vazio()) 
        	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo deleteMax(Nodo h) 
    { 
        if (isRed(h.esquerda))
            h = rotacaoDireita(h);

        if (h.direita == null)
            return null;

        if (!isRed(h.direita) && !isRed(h.direita.esquerda))
            h = moverDireita(h);

        h.direita = deleteMax(h.direita);

        return balancear(h);
    }

    public void delete(K chave, Contador cont) 
    { 
        if (!contem(chave)) 
        {
            System.err.println("Chave não existe: " + chave);
            return;
        }

        if (!isRed(raiz.esquerda) && !isRed(raiz.direita))
        	raiz.cor = RED;

        raiz = delete(raiz, chave, cont);
        if (!vazio()) 
        	raiz.cor = BLACK;
        assert checar();
    }

    private Nodo delete(Nodo h, K chave, Contador cont) 
    { 
        assert contem(h, chave);

        if (chave.compareTo(h.chave) < 0)  
        {
        	cont.setComparacoes(cont.getComparacoes() + 1);
            if (!isRed(h.esquerda) && !isRed(h.esquerda.esquerda))
                h = moverEsquerda(h);
            h.esquerda = delete(h.esquerda, chave, cont);
        }
        else 
        {
        	cont.setComparacoes(cont.getComparacoes() + 1);
            if (isRed(h.esquerda))
            {
            	cont.setRotacoes(cont.getRotacoes() + 1);
                h = rotacaoDireita(h);
            }
            if (chave.compareTo(h.chave) == 0 && (h.direita == null))
                return null;
            if (!isRed(h.direita) && !isRed(h.direita.esquerda))
                h = moverDireita(h);
            if (chave.compareTo(h.chave) == 0) 
            {
            	Nodo x = min(h.direita);
                h.chave = x.chave;
                h.valor = x.valor;
                h.direita = deleteMin(h.direita);
            }
            else 
            	h.direita = delete(h.direita, chave, cont);
        }
        return balancear(h);
    }

    private Nodo rotacaoDireita(Nodo h) 
    {
        assert (h != null) && isRed(h.esquerda);
        Nodo x = h.esquerda;
        h.esquerda = x.direita;
        x.direita = h;
        x.cor = x.direita.cor;
        x.direita.cor = RED;
        x.N = h.N;
        h.N = tamanho(h.esquerda) + tamanho(h.direita) + 1;
        return x;
    }

    private Nodo rotacaoEsquerda(Nodo h) 
    {
        assert (h != null) && isRed(h.direita);
        Nodo x = h.direita;
        h.direita = x.esquerda;
        x.esquerda = h;
        x.cor = x.esquerda.cor;
        x.esquerda.cor = RED;
        x.N = h.N;
        h.N = tamanho(h.esquerda) + tamanho(h.direita) + 1;
        return x;
    }

    private void trocarCor(Nodo h) 
    {
        assert (h != null) && (h.esquerda != null) && (h.direita != null);
        assert (!isRed(h) &&  isRed(h.esquerda) &&  isRed(h.direita))
            || ( isRed(h) && !isRed(h.esquerda) && !isRed(h.direita));
        h.cor = !h.cor;
        h.esquerda.cor = !h.esquerda.cor;
        h.direita.cor = !h.direita.cor;
    }

    private Nodo moverEsquerda(Nodo h) 
    {
        assert (h != null);
        assert isRed(h) && !isRed(h.esquerda) && !isRed(h.esquerda.esquerda);

        trocarCor(h);
        if (isRed(h.direita.esquerda)) 
        { 
            h.direita = rotacaoDireita(h.direita);
            h = rotacaoEsquerda(h);
        }
        return h;
    }

    private Nodo moverDireita(Nodo h) 
    {
        assert (h != null);
        assert isRed(h) && !isRed(h.direita) && !isRed(h.direita.esquerda);
        trocarCor(h);
        if (isRed(h.esquerda.esquerda)) 
        { 
            h = rotacaoDireita(h);
        }
        return h;
    }

    private Nodo balancear(Nodo h) 
    {
        assert (h != null);

        if (isRed(h.direita))                      
        	h = rotacaoEsquerda(h);
        if (isRed(h.esquerda) && isRed(h.esquerda.esquerda)) 
        	h = rotacaoDireita(h);
        if (isRed(h.esquerda) && isRed(h.direita))     
        	trocarCor(h);

        h.N = tamanho(h.esquerda) + tamanho(h.direita) + 1;
        return h;
    }

    public int altura() 
    { 
        return altura(raiz); 
    }
    
    private int altura(Nodo x) 
    {
        if (x == null) 
        	return -1;
        return 1 + Math.max(altura(x.esquerda), altura(x.direita));
    }

    public K min() 
    {
        if (vazio()) 
        	return null;
        return min(raiz).chave;
    } 

    private Nodo min(Nodo x) 
    { 
        assert x != null;
        if (x.esquerda == null) 
        	return x; 
        else                
        	return min(x.esquerda); 
    } 

    public K max() 
    {
        if (vazio()) 
        	return null;
        return max(raiz).chave;
    } 

    private Nodo max(Nodo x) 
    { 
        assert x != null;
        if (x.direita == null) 
        	return x; 
        else                 
        	return max(x.direita); 
    } 

    public K fundo(K chave) 
    {
    	Nodo x = fundo(raiz, chave);
        if (x == null) 
        	return null;
        else           
        	return x.chave;
    }    

    private Nodo fundo(Nodo x, K chave) 
    {
        if (x == null) 
        	return null;
        
        int cmp = chave.compareTo(x.chave);
        if (cmp == 0) 
        	return x;
        if (cmp < 0)  
        	return fundo(x.esquerda, chave);
        
        Nodo t = fundo(x.direita, chave);
        if (t != null) 
        	return t; 
        else           
        	return x;
    }

    public K teto(K key) 
    {  
    	Nodo x = teto(raiz, key);
        if (x == null) 
        	return null;
        else           
        	return x.chave;  
    }

    private Nodo teto(Nodo x, K chave) 
    {  
        if (x == null) 
        	return null;
        
        int cmp = chave.compareTo(x.chave);
        if (cmp == 0) 
        	return x;
        if (cmp > 0)  
        	return teto(x.direita, chave);
        
        Nodo t = teto(x.esquerda, chave);
        if (t != null) 
        	return t; 
        else           
        	return x;
    }

    public K selecionar(int k) 
    {
        if (k < 0 || k >= tamanho())  
        	return null;
        
        Nodo x = selecionar(raiz, k);
        return x.chave;
    }

    private Nodo selecionar(Nodo x, int k) 
    {
        assert x != null;
        assert k >= 0 && k < tamanho(x);
        
        int t = tamanho(x.esquerda); 
        if (t > k) 
        	return selecionar(x.esquerda,  k); 
        else if (t < k) 
        	return selecionar(x.direita, k-t-1); 
        else 
        	return x; 
    } 

    public int classificar(K chave) 
    {
        return classificar(chave, raiz);
    } 

    private int classificar(K chave, Nodo x) 
    {
        if (x == null) 
        	return 0; 
        
        int cmp = chave.compareTo(x.chave); 
        if (cmp < 0) 
        	return classificar(chave, x.esquerda); 
        else if (cmp > 0) 
        	return 1 + tamanho(x.esquerda) + classificar(chave, x.direita); 
        else              
        	return tamanho(x.esquerda); 
    } 

    public Iterable<K> chaves() 
    {
        return chaves(min(), max());
    }

    public Iterable<K> chaves(K inferior, K superior) 
    {
    	Queue<K> queue = new LinkedList<K>();
    	chaves(raiz, queue, inferior, superior);
        return queue;
    } 

    private void chaves(Nodo x, Queue<K> fila, K inferior, K superior) 
    { 
        if (x == null) 
        	return; 
        
        int cmplo = inferior.compareTo(x.chave); 
        int cmphi = superior.compareTo(x.chave); 
        if (cmplo < 0) 
        	chaves(x.esquerda, fila, inferior, superior); 
        if (cmplo <= 0 && cmphi >= 0) 
        	fila.offer(x.chave);
        if (cmphi > 0) 
        	chaves(x.direita, fila, inferior, superior); 
    } 

    public int tamanho(K inferior, K superior) 
    {
        if (inferior.compareTo(superior) > 0) 
        	return 0;
        if (contem(superior)) 
        	return classificar(superior) - classificar(inferior) + 1;
        else              
        	return classificar(superior) - classificar(inferior);
    }

    private boolean checar() 
    {
        if (!isBST())            
        	System.out.println("Arvore não simétrica");
        if (!tamanhoConsistente()) 
        	System.out.println("Arvore não consistente");
        if (!classificacaoConsistente()) 
        	System.out.println("Classificação não consistente");
        if (!is23())             
        	System.out.println("Arvore não é 2-3");
        if (!balanciada())       
        	System.out.println("Arvore não balanceada");
        return isBST() && tamanhoConsistente() && classificacaoConsistente() 
                       && is23() && balanciada();
    }

    private boolean isBST() 
    {
        return isBST(raiz, null, null);
    }

    private boolean isBST(Nodo x, K min, K max) 
    {
        if (x == null) 
        	return true;
        if (min != null && x.chave.compareTo(min) <= 0) 
        	return false;
        if (max != null && x.chave.compareTo(max) >= 0) 
        	return false;
        return isBST(x.esquerda, min, x.chave) && isBST(x.direita, x.chave, max);
    } 

    private boolean tamanhoConsistente() 
    {
        return tamanhoConsistente(raiz); 
    }

    private boolean tamanhoConsistente(Nodo x) 
    {
        if (x == null) 
        	return true;
        if (x.N != tamanho(x.esquerda) + tamanho(x.direita) + 1) 
        	return false;
        return tamanhoConsistente(x.esquerda) && tamanhoConsistente(x.direita);
    } 

    private boolean classificacaoConsistente() 
    {
        for (int i = 0; i < tamanho(); i++)
            if (i != classificar(selecionar(i))) 
            	return false;
        for (K key : chaves())
            if (key.compareTo(selecionar(classificar(key))) != 0) 
            	return false;
        return true;
    }

    private boolean is23() 
    { 
    	return is23(raiz); 
    }
    
    private boolean is23(Nodo x) 
    {
        if (x == null) 
        	return true;
        if (isRed(x.direita)) 
        	return false;
        if (x != raiz && isRed(x) && isRed(x.esquerda))
            return false;
        return is23(x.esquerda) && is23(x.direita);
    } 

    private boolean balanciada() 
    { 
        int black = 0;     
        Nodo x = raiz;
        while (x != null) 
        {
            if (!isRed(x)) black++;
            x = x.esquerda;
        }
        return balanciada(raiz, black);
    }

    private boolean balanciada(Nodo x, int black) 
    {
        if (x == null) 
        	return black == 0;
        if (!isRed(x)) 
        	black--;
        return balanciada(x.esquerda, black) && balanciada(x.direita, black);
    } 
}