
package mx.gob.imss.cit.bp.to;

import java.io.Serializable;
import java.util.List;

public class TramiteTO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8522501276130665359L;
	
	protected String org;
    protected String zona;
    protected String tramite;
    protected String fecha;
    protected String folio;
    protected List<String> actores;
	
    public String getOrg() {
		return org;
	}
	
    public void setOrg(String org) {
		this.org = org;
	}
	
    public String getZona() {
		return zona;
	}
	
    public void setZona(String zona) {
		this.zona = zona;
	}
	
    public String getTramite() {
		return tramite;
	}
	
    public void setTramite(String tramite) {
		this.tramite = tramite;
	}
	
    public String getFecha() {
		return fecha;
	}
	
    public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
    public String getFolio() {
		return folio;
	}
	
    public void setFolio(String folio) {
		this.folio = folio;
	}
	
    public List<String> getActores() {
		return actores;
	}
	
    public void setActores(List<String> actores) {
		this.actores = actores;
    }
}
