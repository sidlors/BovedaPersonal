package mx.gob.imss.cit.bp.to;

import java.io.Serializable;

public class BaseObjectTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7364271033304924672L;
	
	protected String id;
    protected String customId;
    protected String parentId;
    protected String name;
    protected String isFolder;
    protected String path;
    protected boolean borrado;
	
    public String getId() {
		return id;
	}
    
	public void setId(String id) {
		this.id = id;
	}
	
	public String getCustomId() {
		return customId;
	}
	
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String isFolder() {
		return isFolder;
	}
	
	public void setFolder(String isFolder) {
		this.isFolder = isFolder;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isBorrado() {
		return borrado;
	}
	
	public void setBorrado(boolean borrado) {
		this.borrado = borrado;
	}
}
