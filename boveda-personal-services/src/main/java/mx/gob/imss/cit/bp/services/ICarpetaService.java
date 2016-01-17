package mx.gob.imss.cit.bp.services;


import java.util.List;

import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.FolderResponseTO;
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.TramiteTO;



/**
 * Interface que contiene metodos referentes al manejo
 * de las carpetas
 * 
 * @author Admin
 * 
 */
public interface ICarpetaService {

	
	/**
	 * Metodo que crea la carpeta .
	 * @param crearCarpetaRequest lista de datos para crear una carpeta.
	 * @return crearCarpetaResponse  lista de respuesta con las carpetas creadas.
	 * @throws BovedaPersonalServicesException
	 */
    BaseResponseTO createFolder(TramiteTO tramite,  ActorTO actor, BaseObjectTO base) throws BovedaPersonalServicesException;

   /**
	 * Metodo que regresa datos del contenido de una carpeta
	 * 
	 * @param usuarioVO contiene los datos de usuario para la sesion.
	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
	 * @return 
	 * @throws BovedaPersonalServicesException
	 */
	public List<FolderTO> getUserFolder(TramiteTO tramite, ActorTO actor, BaseObjectTO base)
			throws BovedaPersonalServicesException;

   /**
 	 * Metodo que regresa datos los documentos de una carpeta
 	 * 
 	 * @param usuarioVO contiene los datos de usuario para la sesion.
 	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
 	 * @return 
 	 * @throws BovedaPersonalServicesException
 	 */
   	List<BaseObjectTO> getFolderDocuments(TramiteTO tramite, ActorTO actor, BaseObjectTO base) throws BovedaPersonalServicesException;
    /**
 	 * Metodo que regresa todos los objetos dentro de una carpeta por
 	 * 
 	 * @param usuarioVO contiene los datos de usuario para la sesion.
 	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
 	 * @return 
 	 * @throws BovedaPersonalServicesException
 	 */
   	List<FolderTO> getFolderObjects(TramiteTO tramite, ActorTO actor, BaseObjectTO document) throws BovedaPersonalServicesException;
   
   /**
 	 * Metodo que regresa los descendientes de una carpeta por Id
 	 * 
 	 * @param usuarioVO contiene los datos de usuario para la sesion.
 	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
 	 * @return 
 	 * @throws BovedaPersonalServicesException
 	 */
   	List<FolderTO> getFolderDescendants(TramiteTO tramite, ActorTO actor, BaseObjectTO document) throws BovedaPersonalServicesException;

   /**
	 * Metodo que regresa los descendientes de una carpeta por path
	 * 
	 * @param usuarioVO contiene los datos de usuario para la sesion.
	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
	 * @return 
	 * @throws BovedaPersonalServicesException
	 */
   	BaseResponseTO addFolderActor(TramiteTO tramite, BaseObjectTO base, ActorTO actor, ActorTO newActor ) throws BovedaPersonalServicesException;

}
