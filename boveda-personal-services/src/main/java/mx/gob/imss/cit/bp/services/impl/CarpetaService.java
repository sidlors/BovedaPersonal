package mx.gob.imss.cit.bp.services.impl;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.bp.services.ICarpetaService;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.services.util.FolderServiceHelper;
import mx.gob.imss.cit.bp.services.util.SessionProvider;
import mx.gob.imss.cit.bp.services.util.ValidationHelper;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.FolderResponseTO;
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.apache.chemistry.opencmis.client.api.Session;

/**
 * Implementacion de metodos referentes al manejo de las carpetas
 * 
 * @author admin
 * 
 */
@Remote(ICarpetaService.class)
@Stateless(name="CarpetaServiceEscritorio",mappedName="CarpetaServiceEscritorio")
//@Interceptors(BitacoraInterceptor.class)
public class CarpetaService implements ICarpetaService {


	/**
	 * /** 
	 * <br>
	 * <br> *************************** DESCRIPCION ***************************** 
	 * <br>CU: CrearFolder 
	 * <br>Metodo para crear un folder en ALFRESCO 
	 * <br>
	 * ************************* LISTA DE ERRORES ************************** 
	 * <br>
	 * <br>1.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
	 * <br>2.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO 
	 * <br>3.- Error al iniciar sesion en ALFRESCO: 1-ERROR_INICIAR_SESION 
	 * <br>4.- Error el identificador no es tipo folder: 13-ERROR_IDFOlDER_NO_TYPEFOLDER
	 * <br>5.- Error el folder ya existe: 12-ERROR_FOlDER_YA_EXISTE
	 * <br>5.- Error no existe folder: 4-ERROR_DOCUMENT_FOLDER_NOFOUND
	 * <br>7.- Error general : 11-ERROR_CARPETA_NO_CREADA
	 * <br>
	 * <br>************************** DISENO TECNICO *************************** 
	 * <br>
	 * <br>Validaciones 
	 * --------------------------------------------------------------------- 
	 * <br>
	 * <br> 1.- ValidacionObligatorios = VERDADERO 
	 * <br>SI usuarioTO.usuario = null OR usuarioTO.Password = null OR
	 * <br>carpetaTO.IdCarpeta = null carpetaTO.getNameCarpeta=null 
	 * <br>resultadoValidacion = FALSO 
	 * <br>
	 * <br>error 101-ERROR_PARAMETRO_OBLIGATORIO 
	 * <br> FIN SI 
	 * <br>
	 * <br>2.- validacionFormato = VERDADERO 
	 * <br>SI (formato de usuarioVO.usuario && usuarioVO.password &&
	 * <br> carpetaTO.getNameCarpeta) != (ALFANUMERICO - 1-50 posiciones) OR 
	 * <br>(formato de carpetaTO.IdCarpeta)!=(ER_ALFANUMERICO_CON_ESPECIALES_60) 
	 * <br>
	 * <br>resultadoValidacion = FALSO 
	 * <br>error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO 
	 * <br>FIN SI 
	 * <br>
	 * <br>Acciones 
	 * --------------------------------------------------------------------- 
	 * <br>
	 * <br>1.- Invocar el metodo executeCrearCarpeta enviando crearCarpetaRequest. 
	 * <BR>   valida a traves del objeto Session 
	 * <br>2.- Se obtiene un objeto Cmis apartir del Id de la carpeta. 
	 * <br>3.- Se valida que sea una carpeta y no un documento 
	 * <br>4.- Se castea el objeto a tipo folder para onbtener el path 
	 * <br>5.-  Se crea el map para las propiedades de la carpeta. 
	 * <br>6.- Se crea la carpeta. 
	 * <br>7.- Se regresa un objeto carpetaTO con el id y de newcarpeta. 
	 * <br>
	 * <br>Control de Errores 
	 * <br>--------------------------------------------------------------------- 
	 * <br>
	 * <br>1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no
	 * <br>se reciban todos los parametros obligatorios 
	 * <br>2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de
	 * <br>que se reciban parametros con formato erroneo. 
	 * <br>3.- Se genera un error 1-ERROR_INICIAR_SESION en caso de que se presente
	 * <br>un error al iniciar sesion en ALFRESCO 
	 * <br>4.- Se genera un error 4-ERROR_DOCUMENT_FOLDER_NOFOUND en caso de no
	 * <br>encontrar el folder 
	 * <br>6.- Se genera un error 12-ERROR_FOlDER_YA_EXISTE en caso de que la
	 * <br>carpeta ya exista
	 * <br>7.- Se genera un error 13-ERROR_IDFOlDER_NO_TYPEFOLDER en caso de que el
	 * <br>identificador no es un folder.
	 * <br>8.- Se genera un error 11-ERROR_CARPETA_NO_CREADA en caso de que se genere
	 * <br>un error general.
	 * 	  
	 *********************************************************************/
	@Override
	public BaseResponseTO createFolder(TramiteTO tramite, ActorTO actor,
			BaseObjectTO base) throws BovedaPersonalServicesException {
		BaseResponseTO response = null;
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1(tramite);
		ValidationHelper.validarObjeto1( base );
		ValidationHelper.validarActor1(actor);
		
		response = FolderServiceHelper.createFolder( session, tramite, base, actor);
		return response;
	}
	
	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>CU: ObtenerDocumentosPorCarpeta
	 *<br> Servicio para obtener contenido de una carpeta.
	 *<br> - En la petición ocupar como parámetro el identificador de la carpeta.
	 *<br> - En la respuesta se espera todo el contenido del folder, es decir, subcarpetas y documentos.
	 *<br> 
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> 2.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
	 *<br> 3.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> 4.- No existen resultados con los criterios de b\u00fasqueda : 20-ERROR_NO_RESULT_METADATA
	 *<br> 5.- Folder no encontrado	: 4-ERROR_DOCUMENT_FOLDER_NOFOUND
	 *<br> 6.- No fue posible Obtener Documentos Por Carpeta: 3-ERROR_DOCUMENT_NOFOLDER
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- ValidacionObligatorios = VERDADERO
	 *<br> SI usuarioTO.usuario = null OR  usuarioTO.password = null OR carpetaVO.idCarpeta
	 *<br>        resultadoValidacion = FALSO     
	 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
	 *<br> FIN SI
	 *<br> 
	 *<br> 2.- validacionFormato = VERDADERO
	 *<br> SI  (formato de usuarioTO.usuario && usuarioTO.password) != (ALFANUMERICO - 1-50 posiciones) AND 
	 *<br>	   (formato de carpetaTO.idCarpeta)!= (ALFANUMERICO con signos(/-:)  -60 posiciones )
	 *<br>       resultadoValidacion = FALSO    
	 *<br>       error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> FIN SI
     *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
	 *<br>	   valida a traves del objeto Session	 
	 *<br>  2. Se obtiene los documentos a partir de un nombre de un folder/carpeta.
	 *<br>  4. Se obtienen solo los documentos de la carpeta
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
	 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
	 *<br> 3.- Se genera un error 20-ERROR_NO_RESULT_METADATA en caso de que se presente un error en la busqueda de los documentos por carpeta
	 *<br> 4.- Se genera un error 4-ERROR_DOCUMENT_FOLDER_NOFOUND en caso de no encontrar el folder
	 *<br> 5.- Se genera un error 3-ERROR_DOCUMENT_NOFOLDER en caso de que ocurre un falla en el proceso de busqueda.
	 *<br>
	 *<br>*********************************************************************/	
	@Override
	public List<BaseObjectTO> getFolderDocuments(TramiteTO tramite,
			ActorTO actor, BaseObjectTO base)
			throws BovedaPersonalServicesException {
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite2(tramite);
		ValidationHelper.validarActor1(actor);
		ValidationHelper.validarObjeto2( base );
		
		return FolderServiceHelper.getFolderDocuments(session, tramite, base, actor);
	}
	
	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>CU: ObtenerObjetosPorCarpeta
	 *<br> Metodo para obtener los documentos de un folder
	 *<br> 
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> 2.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
	 *<br> 3.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> 4.- No existen resultados con los criterios de b\u00fasqueda : 20-ERROR_NO_RESULT_METADATA
	 *<br> 5.- Folder no encontrado	: 4-ERROR_DOCUMENT_FOLDER_NOFOUND
	 *<br> 6.- No fue posible Obtener Documentos Por Carpeta: 3-ERROR_DOCUMENT_NOFOLDER
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- ValidacionObligatorios = VERDADERO
	 *<br> SI usuarioTO.usuario = null OR  usuarioTO.password = null OR carpetaVO.idCarpeta
	 *<br>        resultadoValidacion = FALSO     
	 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
	 *<br> FIN SI
	 *<br> 
	 *<br> 2.- validacionFormato = VERDADERO
	 *<br> SI  (formato de usuarioTO.usuario && usuarioTO.password) != (ALFANUMERICO - 1-50 posiciones) AND 
	 *<br>	   (formato de carpetaTO.idCarpeta)!= (ALFANUMERICO con signos(/-:)  -60 posiciones )
	 *<br>       resultadoValidacion = FALSO    
	 *<br>       error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> FIN SI
     *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
	 *<br>	   valida a traves del objeto Session	 
	 *<br>  2. Se obtiene los documentos a partir de un nombre de un folder/carpeta.
	 *<br>  4. Se obtienen solo los documentos de la carpeta
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
	 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
	 *<br> 3.- Se genera un error 20-ERROR_NO_RESULT_METADATA en caso de que se presente un error en la busqueda de los documentos por carpeta
	 *<br> 4.- Se genera un error 4-ERROR_DOCUMENT_FOLDER_NOFOUND en caso de no encontrar el folder
	 *<br> 5.- Se genera un error 3-ERROR_DOCUMENT_NOFOLDER en caso de que ocurre un falla en el proceso de busqueda.
	 *<br>
	 *<br>*********************************************************************/	

	@Override
	public List<FolderTO> getFolderObjects(TramiteTO tramite, ActorTO actor, BaseObjectTO base) throws BovedaPersonalServicesException {		
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite2(tramite);
		ValidationHelper.validarActor2(actor);
		ValidationHelper.validarObjetoGetFolderObjects( base );
		
		return FolderServiceHelper.getFolderObjects(session, tramite, base, actor);
	} 
	
	@Override
	public List<FolderTO> getFolderDescendants(TramiteTO tramite,
			ActorTO actor, BaseObjectTO base)
			throws BovedaPersonalServicesException {
		List<FolderResponseTO> response = null;
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite2(tramite);
		ValidationHelper.validarActor2(actor);
		ValidationHelper.validarObjeto2( base );
		
		return FolderServiceHelper.getFolderDescendants(session, tramite, actor, base);
	}

	/*
	 * Servicio para obtener estructura raíz.
	 *	- En la petición se debe mandar algún parámetro que podamos generar/obtener en base a la información disponible del usuario.
	 *	- En la respuesta se esperan todas las carpetas y documentos que se encuentre en el primer nivel de su estructura de archivos.
	 * (non-Javadoc)
	 * @see mx.gob.imss.cit.bp.services.ICarpetaService#getUserFolder(mx.gob.imss.cit.bp.to.ActorTO, mx.gob.imss.cit.bp.to.BaseObjectTO)
	 */
	@Override
	public List<FolderTO> getUserFolder(TramiteTO tramite, ActorTO actor, BaseObjectTO base)
			throws BovedaPersonalServicesException {
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		//ValidationHelper.validarTramite2(tramite);
		ValidationHelper.validarActor2(actor);
		ValidationHelper.validarObjetoFolder2( base );
		
		return FolderServiceHelper.getUserFolder(session, tramite,  actor, base);
	}

	@Override
	public BaseResponseTO addFolderActor(TramiteTO tramite, BaseObjectTO base, ActorTO actor,
			ActorTO newActor ) throws BovedaPersonalServicesException {
		
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1( tramite );
		ValidationHelper.validarObjeto1( base );
		ValidationHelper.validarActor1( actor);
		ValidationHelper.validarActor1( newActor );
		
		return FolderServiceHelper.addFolderActor(session, tramite, base, actor, newActor);
	}

}