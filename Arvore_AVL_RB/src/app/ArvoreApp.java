package app;

import controller.ArvoreControle;

public class ArvoreApp 
{

	public static void main(String[] args) 
	{
		
		/*
		ArvoreRedBlack<String, Integer> st = new ArvoreRedBlack<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) 
		{
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
        StdOut.println();
		*/
		
		ArvoreControle menu = new ArvoreControle();
		menu.ExibeMenu();
	}

}
