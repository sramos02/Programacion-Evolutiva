package init;

import model.arbol;
public class RampedHalf extends initMethod{
	private int num_grupos;
	private int prof_actual;
	private int tam_grupo;
	private int actual_completos;
	private int actual_crecientes;
	
	public RampedHalf() {
		super("ramped");
		num_grupos = (getProfundidad()-1);
		setProf_actual(1);
		setActual_completos(0);
		setActual_crecientes(0);
	}

	public RampedHalf(RampedHalf old) {
		super(old);
		num_grupos = old.getNum_grupos();
		setProf_actual(old.getProf_actual());
		setTam_grupo(old.getTam_grupo());
		setActual_crecientes(getActual_crecientes());
		setActual_completos(getActual_completos());	
	}

	@Override
	public arbol crearArbol(int profundidad, int numVars) {
		arbol nodo = null;
		if(actual_completos < tam_grupo || actual_crecientes == tam_grupo) {
			//Crear completo
			Completa ini_completa = new Completa();
			ini_completa.setUseIfs(useIfs());
			nodo = ini_completa.crearArbol(prof_actual, numVars);
			actual_completos++;
		}else {
			//Crear creciente
			Creciente ini_creCreciente = new Creciente();
			ini_creCreciente.setUseIfs(useIfs());
			nodo = ini_creCreciente.crearArbol(prof_actual, numVars);
			actual_crecientes++;
		}
		if(actual_completos == tam_grupo && actual_crecientes == tam_grupo) {
			if(prof_actual < num_grupos) {
				prof_actual++;
				actual_completos = 0;
				actual_crecientes = 0;
			}
		}
		return nodo;
	}

	public int getProf_actual() {
		return prof_actual;
	}

	public void setProf_actual(int prof_actual) {
		this.prof_actual = prof_actual;
	}

	public int getTam_grupo() {
		return tam_grupo;
	}

	public void setTam_grupo(int tam_grupo) {
		this.tam_grupo = tam_grupo;
	}

	public int getActual_crecientes() {
		return actual_crecientes;
	}

	public void setActual_crecientes(int actual_crecientes) {
		this.actual_crecientes = actual_crecientes;
	}

	public int getActual_completos() {
		return actual_completos;
	}

	public void setActual_completos(int actual_completos) {
		this.actual_completos = actual_completos;
	}
	
	private int getNum_grupos() {
		return num_grupos;
	}

	@Override
	public void setPropiedades(int size_pob) {
		setTam_grupo((size_pob / num_grupos)/2);
	}
}
