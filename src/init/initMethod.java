package init;
import model.arbol;

public abstract class initMethod {
	private int maxProfundidad=3;
	private boolean useIfs;
	
	public abstract arbol crearArbol(int profundidad);
	public arbol crearArbol() {
		return crearArbol(maxProfundidad-1);
	}
	public void setUseIfs(boolean useIfs) {
		this.useIfs=useIfs;
	}
	
	public boolean useIfs() {
		return useIfs;
	}
}
