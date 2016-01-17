
package mx.gob.imss.cit.bp.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MetadataTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2862722993393582546L;
	
	protected String version;
    protected Date fechaCreacion;
    protected Date fechaModificacion;
	protected List<String> actores;
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	public List<String> getActores() {
		return actores;
	}
	
	public void setActores(List<String> actores) {
		this.actores = actores;
	}
}
