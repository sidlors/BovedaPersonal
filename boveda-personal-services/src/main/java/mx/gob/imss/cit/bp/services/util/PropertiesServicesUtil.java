package mx.gob.imss.cit.bp.services.util;

import java.util.ResourceBundle;

import mx.gob.imss.cit.bp.services.constants.BodegaPersonalServicesConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para leer el archivo properties definido
 * @author Admin
 *
 */

public final class PropertiesServicesUtil {
	
	/**
	 * Constructor privado
	 */
	private PropertiesServicesUtil(){
		
	}

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesServicesUtil.class);	

	/**
	 * Utileria para cargar el properties
	 */
	private static final ResourceBundle MSG =ResourceBundle.getBundle(BodegaPersonalServicesConstants.FILE_SERVICE_CONFIG);	
	
	/**
	 * Metodo que obtiene el valor del properties
	 * @param key
	 * @return
	 */
	public static String getMessage(String key){	
		String mensaje="";
		try{
			mensaje=MSG.getString(key);
		}catch(Exception e){
			LOG.info("No se encontro la propiedad con el key "+key);
			mensaje="";
		}
		return mensaje;
	}
}
