package mx.gob.imss.cit.bp.services.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BovedaPersonalServicesExceptionBuilder {

	private static final int ERROR_DESCONOCIDO = 0;
	/**
	 * Objeto para recuperar el archivo properties de los mensajes de error
	 */
	private static final ResourceBundle MSG_ERROR = ResourceBundle.getBundle("service-exception-config");
	/**
	 * Prefijo de la cadena de errores
	 */
	private static final String PREFIJO_ERROR = "Error.";
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BovedaPersonalServicesExceptionBuilder.class);

/**
 * Constructor por default
 */
private BovedaPersonalServicesExceptionBuilder(){
}


/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado y una cadena StringBuilder de detalles
 * 
 * @param BovedaPersonalServicesCodeExceptionEnum code
 * @param StringBuilder cadenaErrores
 * @return BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( BovedaPersonalServicesCodeExceptionEnum code, String parametrosErrores)
{
 Object[] params = new Object[1];
 params[0] = parametrosErrores;
 return build( code.getId(), null, params, null);
}



/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado
 * 
 * @param BovedaPersonalServicesCodeExceptionEnum code
 * @return BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( BovedaPersonalServicesCodeExceptionEnum code )
{
  return build( code.getId(), null, null, null );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado
 * 
 * @param code C&oacute;digo de Error
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( int code )
{
  return build( code, null, null, null );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado, anexa los par&aacute;metros
 * 
 * @param code C&oacute;digo de Error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( int code, Object[] args )
{
  return build( code, null, args, null );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado, anexa los par&aacute;metros
 * 
 * @param code C&oacute;digo de Error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( BovedaPersonalServicesCodeExceptionEnum code, Object[] args )
{
  return build( code.getId(), null, args, null );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado, agrega la causa de error
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( int code, Throwable cause )
{
  return build( code, cause, null, null );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado, agrega la causa de error
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( BovedaPersonalServicesCodeExceptionEnum code, Throwable cause )
{
  return build( code.getId(), cause, null, null );
}


public static BovedaPersonalServicesException build(int code, Throwable cause ,String descriptionError){
	
	LOG.info("BovedaPersonalServicesExceptionBuilder - build - code: " + code + " - descriptionError: " + descriptionError);
	
	return build(code, cause, null, descriptionError);
}


/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado, agrega la causa de error, agrega los
 * par&aacute;metros de
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( BovedaPersonalServicesCodeExceptionEnum code, Throwable cause, Object[] args )
{
  return build( code.getId(), cause, args , null );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con el c&oacute;digo de error indicado, agrega la causa de error, agrega los
 * par&aacute;metros de
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( int code, Throwable cause, Object[] args, String descriptionError  )
{
	BovedaPersonalServicesException bovedaPersonalServicesException = null;
  if( cause != null ){
	  bovedaPersonalServicesException = new BovedaPersonalServicesException( cause.getMessage(), cause );
  } else {
	  bovedaPersonalServicesException = new BovedaPersonalServicesException();
  }

  bovedaPersonalServicesException.setCode( code );
  
  if(descriptionError == null){
	  String description = MessageFormat.format( MSG_ERROR.getString(PREFIJO_ERROR + code) ,  args);
	  bovedaPersonalServicesException.setDescription( description );
  }else{
	  bovedaPersonalServicesException.setDescription(descriptionError);
  }
  
  LOG.info("BovedaPersonalServicesExceptionBuilder - error descripcion: " +bovedaPersonalServicesException.getDescription() + " - causa:" + cause );
  
  return bovedaPersonalServicesException;
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con un c&oacute;digo de error 0 (desconocido)
 * 
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build()
{
  return build( ERROR_DESCONOCIDO );
}

/**
 * Genera una excepci&oacute;n BovedaPersonalServicesException con un c&oacute;digo de error 0 (desconocido) y agrega la causa de error
 * 
 * @param cause Causa de error
 * @return Excepci&oacute;n BovedaPersonalServicesException
 */
public static BovedaPersonalServicesException build( Throwable cause )
{
  return build( ERROR_DESCONOCIDO, cause );
}		
	
	
}
