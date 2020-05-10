package init;
import model.arbol;

public abstract class initMethod {
	private int maxProfundidad=3;
	private boolean useIfs;
	private String tipo;
	
	public initMethod(String tipo) {
		this.tipo=tipo;
	}
	public initMethod(initMethod old) {
		useIfs = old.useIfs();
		tipo = new String(old.getTipo());
	}
	public abstract arbol crearArbol(int profundidad, int numVars);
	
	public arbol crearArbol(int numVars) {
		return crearArbol(maxProfundidad-1, numVars);
	}
	public void setUseIfs(boolean useIfs) {
		this.useIfs=useIfs;
	}
	
	public boolean useIfs() {
		return useIfs;
	}
	public String getTipo() {
		return tipo;
	}
}
