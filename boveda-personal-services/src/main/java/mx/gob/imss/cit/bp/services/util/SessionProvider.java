package mx.gob.imss.cit.bp.services.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.imss.cit.bp.exception.BovedaPersonalException;
import mx.gob.imss.cit.bp.services.constants.BodegaPersonalServicesConstants;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author mmiki
 * 
 * Clase para crear/obtener el objeto Session ALFRESCO
 *
 */
public final class SessionProvider {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SessionProvider.class);
	
	private static final  String user = PropertiesServicesUtil.getMessage(BodegaPersonalServicesConstants.KEY_ADMIN_USER_ALFRESCO);

	private static final  String pass = PropertiesServicesUtil.getMessage(BodegaPersonalServicesConstants.KEY_ADMIN_PASS_ALFRESCO);
	
	/**
	 * Factory para obtener los objetos session
	 */
	private static final SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
	
	/**
	 * SessionProvider, wrapper para Session
	 */
	private static SessionProvider sessionProv = null;
	
	/**
	 * Session global de alfresco
	 */
	private final Session sessionAlfresco;
	
	/**
	 * Constructor privado para hacerla singleton
	 * @throws  
	 */
	private SessionProvider() { 
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put(SessionParameter.USER, user);
		parameter.put(SessionParameter.PASSWORD, pass);
		parameter.put(SessionParameter.ATOMPUB_URL, PropertiesServicesUtil.getMessage(BodegaPersonalServicesConstants.KEY_URL_ALFRESCO));
		parameter.put(SessionParameter.BINDING_TYPE,BindingType.ATOMPUB.value());
		List<Repository> repositories = sessionFactory.getRepositories(parameter);	
		sessionAlfresco = repositories.get(0).createSession();
	}
	
	/**
	 * Metodo que crea el singleton SessionProvider
	 * @throws BovedaPersonalException 
	 * 
	 */
	public static SessionProvider getSessionProvider() throws BovedaPersonalServicesException{
		if(sessionProv == null){
			synchronized(SessionProvider.class){
				if(sessionProv == null){
					try{
						sessionProv = new SessionProvider();
					} catch(Exception e){
						throw BovedaPersonalServicesExceptionBuilder.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DE_CONEXION , e);
					}
				}
			}
		}
		return sessionProv;
	}

	/**
	 * Metodo que regresa el objeto Session instanciado
	 * 
	 * @return 
	 */
	public Session getSession() {
		return sessionAlfresco;
	}
}