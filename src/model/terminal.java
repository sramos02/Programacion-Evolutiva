package model;

import java.util.Random;

public class terminal extends element{
	private String [] posiblesValores= {"A0", "A1", "D0", "D1", "D2", "D3"};
	private int size=6;
	private String valorPropio;
	
	/**Elige uno de los posibles valores(A0, A1, D0, D1, D2 o D3)
	 *  y lo devuelve*/
	public String generarTerminal() {
		Random r=new Random();
		int pos=r.nextInt()%size;
		valorPropio=posiblesValores[pos];
		return valorPropio;
	}
}
