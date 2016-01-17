package mx.gob.imss.cit.bp.services.exception;

import javax.ejb.ApplicationException;

import mx.gob.imss.cit.bp.exception.BovedaPersonalException;

@ApplicationException
public class BovedaPersonalServicesException extends BovedaPersonalException{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor default de la clase
	 */
	public BovedaPersonalServicesException(){
		super();
	}
	
	/**
	 * Constructor de la clase que incluye mensaje de error.
	 * 
	 * @param message
	 */
	public BovedaPersonalServicesException(String message) {
		super(message);
	}	
	
	/**
	 * Constructor de la clase que incluye mensaje de error y causa del error.
	 * 
	 * @param message
	 * @param cause
	 */
	public BovedaPersonalServicesException(String message, Throwable cause) {
		super(message,cause);
	}
	
	/**
	 * Constructor de la clase que incluye la causa del error.
	 * 
	 * @param cause
	 */
	public BovedaPersonalServicesException(Throwable cause){
		super(cause);
	}	
	
}
