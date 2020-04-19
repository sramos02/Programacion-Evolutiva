package model;

import java.util.Random;

public class funcion extends element{
	private String [] posiblesValores= {"AND", "OR", "NOT", "IF"};
	private int size=4;
	private String valorPropio;
	
	/**Elige uno de los posibles valores(AND, OR, NOT o If) y lo devuelve*/
	public String generarFuncion() {
		Random r=new Random();
		int pos=r.nextInt()%size;
		valorPropio=posiblesValores[pos];
		return valorPropio;
	}
}
