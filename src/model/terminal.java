package model;

import java.util.List;
import java.util.Random;

public class terminal extends element{
	private String [] posiblesValores= {"A0", "A1", "D0", "D1", "D2", "D3"};
	private int size=6;
	
	public terminal() {
		setTipo("terminal");
		generarTerminal();
	}
	
	/**Elige uno de los posibles valores(A0, A1, D0, D1, D2 o D3)
	 *  y lo devuelve*/
	public void generarTerminal() {
		Random r=new Random();
		int pos=Math.abs(r.nextInt()%size);
		setValor(posiblesValores[pos]);
	}

	@Override
	public String toString(contador i, List<element> fenotipo) {
		i.addCount();
		return getValor();
	}
}
