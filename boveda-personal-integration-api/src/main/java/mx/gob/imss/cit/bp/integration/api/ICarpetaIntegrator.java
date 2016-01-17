package mx.gob.imss.cit.bp.integration.api;

import java.util.List;

import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectResponseTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.FolderResponseTO;
import mx.gob.imss.cit.bp.to.TramiteTO;


/**
 * Interface que contiene metodos referentes al manejo
 * de las carpetas en la capa de integrator
 * 
 * @author Admin
 * 
 */
public interface ICarpetaIntegrator {
	/**
	 * Metodo que crea la carpeta .
	 * @param crearCarpetaRequest lista de datos para crear una carpeta.
	 * @return crearCarpetaResponse  lista de respuesta con las carpetas creadas.
	 * @throws BovedaPersonalServicesException
	 */
    BaseResponseTO createFolder(TramiteTO tramite,  ActorTO actor, BaseObjectTO base);

   /**
	 * Metodo que regresa datos del contenido de una carpeta
	 * 
	 * @param usuarioVO contiene los datos de usuario para la sesion.
	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
	 * @return 
	 * @throws BovedaPersonalServicesException
	 */
    public List<FolderResponseTO> getUserFolder(TramiteTO tramite, ActorTO actor, BaseObjectTO base );

   /**
 	 * Metodo que regresa datos los documentos de una carpeta
 	 * 
 	 * @param usuarioVO contiene los datos de usuario para la sesion.
 	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
 	 * @return 
 	 * @throws BovedaPersonalServicesException
 	 */
   	List<DocumentResponseTO> getFolderDocuments(TramiteTO tramite, ActorTO actor, BaseObjectTO base);
    /**
 	 * Metodo que regresa todos los objetos dentro de una carpeta por
 	 * 
 	 * @param usuarioVO contiene los datos de usuario para la sesion.
 	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
 	 * @return 
 	 * @throws BovedaPersonalServicesException
 	 */
   	List<FolderResponseTO> getFolderObjects(TramiteTO tramite, ActorTO actor, BaseObjectTO document);
   
   /**
 	 * Metodo que regresa los descendientes de una carpeta por Id
 	 * 
 	 * @param usuarioVO contiene los datos de usuario para la sesion.
 	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
 	 * @return 
 	 * @throws BovedaPersonalServicesException
 	 */
   	List<FolderResponseTO> getFolderDescendants(TramiteTO tramite, ActorTO actor, BaseObjectTO base);

   /**
	 * Metodo que regresa los descendientes de una carpeta por path
	 * 
	 * @param usuarioVO contiene los datos de usuario para la sesion.
	 * @param carpetaVO contiene  el id y el nombre  de la carpeta que se creara
	 * @return 
	 * @throws BovedaPersonalServicesException
	 */
   	BaseResponseTO addFolderActor(TramiteTO tramite, BaseObjectTO base, ActorTO actor, ActorTO newActor );

}
