package model;

import java.util.List;
import java.util.Random;

public class terminal extends element{
	private String [] posiblesValores= {"A0", "A1", "D0", "D1", "D2", "D3"};
	private int size=6;
	private tupla valor;
	public terminal() {
		setTipo("terminal");
		generarTerminal();
	}
	
	/**Elige uno de los posibles valores(A0, A1, D0, D1, D2 o D3)
	 *  y lo devuelve*/
	public void generarTerminal() {
		Random r=new Random();
		int pos=Math.abs(r.nextInt()%size);
		valor=new tupla(pos, posiblesValores[pos]);
		setValor(valor.getValor());
	}

	@Override
	public String toString(contador i, List<element> fenotipo) {
		i.addCount();
		return getValor();
	}

	@Override
	protected int evaluarExpresion(contador contador, int[] sol, List<element> fenotipo) {
		contador.addCount();
		return sol[valor.getPosicion()];
	}
}
