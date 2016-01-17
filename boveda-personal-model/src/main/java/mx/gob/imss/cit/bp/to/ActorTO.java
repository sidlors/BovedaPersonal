package mx.gob.imss.cit.bp.to;

import java.io.Serializable;

public class ActorTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3441184642515721515L;
	
	private String org;
    private String zona;
    private String tipoId;
    private String id;
    private String rol;
    private String isOwner;
    
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
	
	public String getTipoId() {
		return tipoId;
	}
	
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRol() {
		return rol;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String getIsOwner() {
		return isOwner;
	}
	
	public void setIsOwner(String isOwner) {
		this.isOwner = isOwner;
	}
}
