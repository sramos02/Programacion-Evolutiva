package model;

import java.util.List;
import java.util.Random;

public class terminal extends element{
	private final String [] posiblesValores= {"A0", "A1", "D0", "D1", "D2", "D3"};
	private final String [] posiblesValores2= {"A0", "A1","A2", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8"};
	private int size;
	private tupla valor;
	
	public terminal(int numVars) {
		setTipo("terminal");
		size=numVars;
		generarTerminal();
	}
	
	public terminal(element old) {
		super(old);
		size= ((terminal) old).getSize();
		valor= new tupla(((terminal) old).getTupla());
	}

	private tupla getTupla() {
		return valor;
	}

	private int getSize() {
		return size;
	}

	
	/**Elige uno de los posibles valores(A0, A1, D0, D1, D2 o D3)
	 *  y lo devuelve*/
	public void generarTerminal() {
		Random r=new Random();
		int pos=Math.abs(r.nextInt()%size);
		String nombre = size == 6 ?  posiblesValores[pos] : posiblesValores2[pos];
		valor=new tupla(pos, nombre);
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

	public String nuevoTerminal(int numVariables) {
		Random r=new Random();
		int pos = Math.abs(r.nextInt()%size);
		return (size == 6)? posiblesValores[pos] : posiblesValores2[pos];
	}
}
