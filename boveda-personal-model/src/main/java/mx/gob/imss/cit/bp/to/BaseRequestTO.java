
package mx.gob.imss.cit.bp.to;

import java.io.Serializable;

public class BaseRequestTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4851906735923675102L;
	
	protected TramiteTO tramite;

	public TramiteTO getTramite() {
		return tramite;
	}

	public void setTramite(TramiteTO tramite) {
		this.tramite = tramite;
	}
}
