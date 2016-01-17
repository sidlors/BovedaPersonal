
package mx.gob.imss.cit.bp.to;

import java.io.Serializable;


public class BaseResponseTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8398548278627312608L;
	
	protected Integer excepcionCodigo;
    protected String excepcionMensaje;
    protected String excepcionCausa;
    protected boolean exitoso;
	
	public Integer getExcepcionCodigo() {
		return excepcionCodigo;
	}
	
	public void setExcepcionCodigo(Integer excepcionCodigo) {
		this.excepcionCodigo = excepcionCodigo;
	}
	
	public String getExcepcionMensaje() {
		return excepcionMensaje;
	}
	
	public void setExcepcionMensaje(String excepcionMensaje) {
		this.excepcionMensaje = excepcionMensaje;
	}
	
	public String getExcepcionCausa() {
		return excepcionCausa;
	}
	
	public void setExcepcionCausa(String excepcionCausa) {
		this.excepcionCausa = excepcionCausa;
	}
	
	public boolean isExitoso() {
		return exitoso;
	}
	
	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}
}
