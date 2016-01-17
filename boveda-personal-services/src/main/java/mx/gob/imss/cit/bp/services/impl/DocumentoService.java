package mx.gob.imss.cit.bp.services.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.bp.constant.BovedaConstants;
import mx.gob.imss.cit.bp.services.IDocumentoService;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.services.util.DocumentServiceHelper;
import mx.gob.imss.cit.bp.services.util.ServiceHelper;
import mx.gob.imss.cit.bp.services.util.SessionProvider;
import mx.gob.imss.cit.bp.services.util.ValidationHelper;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementacion de metodos referentes al manejo
 * de los documentos
 * @author admin
 *
 */
@Remote(IDocumentoService.class)
@Stateless(name="DocumentoServiceEscritorio",mappedName="DocumentoServiceEscritorio")
//@Interceptors(BitacoraInterceptor.class)
public class DocumentoService implements IDocumentoService{
	
/**
 * Logger
 */
private static final Logger LOG = LoggerFactory.getLogger(DocumentoService.class);


/**
 *<br>*************************** DESCRIPCION *****************************
 *<br>CU: ConsultarInfoDocumento
 *<br>Metodo que obtiene el detalle y/o propiedades de un  documento y por version.
 *<br> 
 *<br>************************* LISTA DE ERRORES **************************
 *<br>
 *<br> 1.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
 *<br> 2.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
 *<br> 3.- No existen resultados con los criterios de busqueda: 20-ERROR_NO_RESULT_METADATA
 *<br> 4.- Documento no encontrado : 6-ERROR_DOCUMENT_NO_FOUND
 *<br> 5.- No fue posible Consultar Info Documento: 7-ERROR_PROPERTIES_DOCUMENT
 *<br>
 *<br> 1. 
 *<br>************************** DISENO TECNICO ***************************
 *<br>
 *<br> Validaciones
 *<br>---------------------------------------------------------------------
 *<br>
 *<br> 1.- ValidacionObligatorios = VERDADERO
 *<br> SI documentMetadataRequest.usuario = null OR  documentMetadataRequest.password = null 
 *<br>			OR documentMetadataRequest.nombreArchivo
 *<br>        resultadoValidacion = FALSO     
 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
 *<br> FIN SI
 *<br> 
 *<br> 2.- validacionFormato = VERDADERO
 *<br> SI  (formato de documentMetadataRequest.usuario && usuarioVO.password) != (ALFANUMERICO - 1-50 posiciones)
 *<br> AND (documentMetadataRequest.nombreArchivo) != (ALFABUMERICO [a-zA-Z0-9\\-\\_\\.\\ ] - 1-60 posiciones)
 *<br> OR  (documentMetadataRequest.version) != (ALFABUMERICO [0-9.] - 2 enteros y 2 decimales)
 *<br>      resultadoValidacion = FALSO    
 *<br>      error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
 *<br> FIN SI
 *<br>
 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
	 *<br>	   valida a traves del objeto Session	 
	 *<br>  2. Se hace la busqueda del documento y se obtiene el objeto Cmis.
	 *<br>  4. Se busca la version del documento para obtener las propiedades del documento, 
	 *<br>		en caso de que el campo version fue proporcionada.
	 *<br>  5. Si el campo(version) de entrada fue proporcionada con el valor -1, se busca las propiedades 
	 *<br>	   del documento de la version mas reciente. 
	 *<br>  6. Se obtienen las propiedades del documento.
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
	 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
	 *<br> 5.- Se genera un error 20-ERROR_NO_RESULT_METADATA cuando no se documentos en la busqueda.
	 *<br> 3.- Se genera un error 6-ERROR_DOCUMENT_NO_FOUND en caso que no se encuentra el documento a buscar.
	 *<br> 4.- Se genera un error 7-ERROR_PROPERTIES_DOCUMENT en caso de no obtener el metada o propiedades del documento.
	 *<br> 
	 *<br>
	 *<br>*********************************************************************/
	@Override
	public MetadataTO getDocumentMetadata(TramiteTO tramite, ActorTO actor,
		BaseObjectTO document ) throws BovedaPersonalServicesException {
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1(tramite);
		ValidationHelper.validarActor1(actor);
		ValidationHelper.validarObjeto1( document );
		
		return DocumentServiceHelper.getDocumentMetadata(session, tramite, document, actor);		
	}

	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>CU: ConsultarInfoVersionesDocumento
	 *<br> Permite consultar la Meta-informacion de todas las versiones de un 
	 *<br> mismo documento a partir del nombre del archivo.
	 *<br> 
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> 2.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
	 *<br> 3.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> 4.- No existen resultados con los criterios de busqueda	: 20-ERROR_NO_RESULT_METADATA
	 *<br> 5.- Versiones vacias	s 									: 16-ERROR_VERSION_EMPTY
	 *<br> 6.- No se encontro el Documento			     			: 14-ERROR_NODOCUMENT			
	 *<br> 7.- Error en la busqueda de la informacion de las versiones: 15-ERROR_DOCUMENT_INFOVERSION	
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- ValidacionObligatorios = VERDADERO
	 *<br> SI metadataHistoryRequest.usuario = null OR  metadataHistoryRequest.password = null 
	 *<br>        resultadoValidacion = FALSO     
	 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
	 *<br> FIN SI
	 *<br> 
	 *<br> 2.- validacionFormato = VERDADERO
	 *<br> SI  (formato de metadataHistoryRequest.usuario && metadataHistoryRequest.password) != 
	 *<br>	   (ALFANUMERICO - 1-50 posiciones)  
	 *<br>      resultadoValidacion = FALSO    
	 *<br>      error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> FIN SI
     *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
	 *<br>	   valida a traves del objeto Session	 
	 *<br>  2. Se obtiene un objeto Cmis apartir del nombre del Documento.
	 *<br>  4. Se obtienen la informacion de las versiones asi como los metadata
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
	 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
	 *<br> 3.- Se genera un error 29-ERROR_NO_RESULT_METADATA en el caso de que no exista documentos en la busqueda.
	 *<br> 4.- Se genera un error 14-ERROR_NODOCUMENT en caso de que se presente un error en la busqueda de los documentos por carpeta
	 *<br> 5.- Se genera un error 15-ERROR_DOCUMENT_INFOVERSION en caso de no encontrar el folder
	 *<br> 6.- Se genera un error 16-ERROR_VERSION_EMPTY en caso de la carpeta que consultamos esta vacia	
	 *<br>
	 *<br>*********************************************************************/	
	@Override
	public List<MetadataTO> getAllDocumentMetadataVersions(TramiteTO tramite, ActorTO actor, BaseObjectTO document)
			throws BovedaPersonalServicesException {
		List<MetadataTO> results = null;
		Session session = null;
		Document temp = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1(tramite);
		ValidationHelper.validarActor1( actor );
		ValidationHelper.validarObjeto1( document );
		
		temp = DocumentServiceHelper.getDocumentInFolder(session, tramite, document, actor );
		results = new ArrayList<MetadataTO>();
		for(Document doc : temp.getAllVersions()){
			MetadataTO metadata = DocumentServiceHelper.createMetadataTO( doc );
			results.add( metadata );
		}
		return results;
	}
	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>CU: ConsultarTodasVersionesDocumento
	 *<br> Permite consultar todas las versiones de un mismo documento.
	 *<br> 
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> 1.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
	 *<br> 2.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> 3.- No se encontro informacion con los criterios de busqueda	: 20-ERROR_NO_RESULT_METADATA
	 *<br> 4.- Objeto no es un documento								: 14-ERROR_NODOCUMENT
	 *<br> 5.- Error en la extraccion de los documentos		     	    : 15-ERROR_DOCUMENT_INFOVERSION			
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- ValidacionObligatorios = VERDADERO
	 *<br> SI allDocumentVersionsRequest.usuario = null OR  allDocumentVersionsRequest.password = null
	 *<br>        resultadoValidacion = FALSO     
	 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
	 *<br> FIN SI
	 *<br> 
	 *<br> 2.- validacionFormato = VERDADERO
	 *<br> SI  (formato de allDocumentVersionsRequest.usuario && allDocumentVersionsRequest.password) != 
	 *<br>		(ALFANUMERICO - 1-50 posiciones) AND  
	 *<br>      resultadoValidacion = FALSO    
	 *<br>      error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> FIN SI
     *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
	 *<br>	   valida a traves del objeto Session	 
	 *<br>  2. Se realiza busqueda con los criterios de busqueda.
	 *<br>  3. Se obtiene el documento y  todas las versiones.
	 *<br>  4. Se retorna ls versiones del documento obtenido en la consulta.
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios.
	 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
	 *<br> 3.- Se genera un error 20-ERROR_NO_RESULT_METADATA en el caso no se encontro documento con los criterios de busqueda.
	 *<br> 4.- Se genera un error 14-ERROR_NODOCUMENT el objeto no es tipo de documento.
	 *<br> 5.- Se genera un error 15-ERROR_DOCUMENT_INFOVERSION ocurre un error general.
	 *<br>
	 *<br>*********************************************************************/	
	@Override
	public List<DocumentTO> getAllDocumentVersions(TramiteTO tramite, ActorTO actor, BaseObjectTO document)
			throws BovedaPersonalServicesException {
		Session session = null;
		Document temp = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1( tramite );
		ValidationHelper.validarActor1( actor );
		ValidationHelper.validarObjeto1( document );
		
		temp = DocumentServiceHelper.getDocumentInFolder(session, tramite, document, actor );
		return DocumentServiceHelper.createDocumentTOList( temp.getAllVersions() );		
	}
	
	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>CU: ConsultarInfoDocumentosPorMetaInformacion
	 *<br>Metodo que obtiene el detalle y/o propiedades los documentos de acuerdo
	 *<br>	al criterio de busqueda (usuario, tipo documento, nombre del documento, 
	 *<br>	fecha de creacion y modificacion). 
	 *<br>	
	 *<br> 
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> 1.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
	 *<br> 2.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> 3.- Tipo de documento no valido 22-ERROR_DOCUMENT_MIME_TYPE
	 *<br> 4.- No existen resultados con los criterios de busqueda : 20-ERROR_NO_RESULT_METADATA
	 *<br> 5.- Documento no encontrado: 6-ERROR_DOCUMENT_NO_FOUND
	 *<br> 6.- No se puede realizar el parseo de fecha: 10-ERROR_FORMAT_DATE	 
	 *<br> 7.- No se pudo realizar la consulta de documentos con los criterios de busqueda 21-ERROR_RETRIEVE_METADATA
	 *<br> 1. 
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- ValidacionObligatorios = VERDADERO
	 *<br> SI documentFromMetadataRequest.usuario = null OR  documentFromMetadataRequest.password = null 
	 *<br> OR documentFromMetadataRequest.usuarioUltimoModificador
	 *<br>        resultadoValidacion = FALSO     
	 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
	 *<br> FIN SI
	 *<br> 
	 *<br> 2.- validacionFormato = VERDADERO
	 *<br> SI  (formato de usuarioVO.usuario && usuarioVO.password ) != (ALFANUMERICO - 1-50 posiciones)
	 *<br> AND (documentFromMetadataRequest.usuarioUltimoModificador) != (ALFANUMERICO - 1-50 posiciones)
	 *<br> OR  (documentFromMetadataRequest.nombre) != (ALFABUMERICO [0-9] - No permitidos [\/:*?"<>|]-  1-200 posiciones)
	 *<br> OR  (documentFromMetadataRequest.tipoMimeContenidoStream) != (ALFABUMERICO [0-9./-+] - 1-100 posiciones)
	 *<br> OR  (documentFromMetadataRequest.fechaCreacion) != (Date [yy/mm/yyy])
	 *<br> OR  (documentFromMetadataRequest.fechaModificacion) != (Date [yy/mm/yyy])
	 *<br>      resultadoValidacion = FALSO    
	 *<br>      error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
	 *<br> FIN SI
     *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
	 *<br>	   valida a traves del objeto Session.	 
	 *<br>  2. Se arma la sentencia del query de acuerdo a los criterios de busqueda.
	 *<br>  3. Se ejecuta la sentencia para obtener los documentos.
	 *<br>  4. Se valida que el reultado no se nulo o vacio.
	 *<br>  5. Se extrae el documento de la lista de resultados. 
	 *<br>  6. Se obtienen las propiedades(metadata) de cada documento.
	 *<br>  7. Se agrega cada metada obtenido a la lista.
	 *<br>  8. Se retorna la lista.
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
	 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
	 *<br> 3.- Se genera un error 22-ERROR_DOCUMENT_MIME_TYPE cuando el tipo de documento no es valido.
	 *<br> 4.- Se genera un error 20-ERROR_NO_RESULT_METADATA en caso que no se encuentra resultados en las busqueda.
	 *<br> 5.- Se genera un error 6-ERROR_DOCUMENT_NO_FOUND en caso que no se encuentra el documento.
	 *<br> 6.- Se genera un error 10-ERROR_FORMAT_DATE cuando no se puede realizar el parse de fecha.
	 *<br> 7.- Se genera un error 21-ERROR_RETRIEVE_METADATA cuando no poder obtener o sucede un fallo en  la consulta
	 *<br>
	 *<br>*********************************************************************/
	@Override
	public List<DocumentTO> findDocumentsByMetadata(TramiteTO tramite, ActorTO actor, MetadataTO metadata)
			throws BovedaPersonalServicesException {
		Session session = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1( tramite );
		ValidationHelper.validarMetadatos1( metadata );
		ValidationHelper.validarActor1( actor );
		
		return DocumentServiceHelper.findDocumentsByMetadata(session, tramite, metadata, actor);
	}	

	@Override
	public DocumentTO getDocument(TramiteTO tramite, ActorTO actor,
			BaseObjectTO document) throws BovedaPersonalServicesException {
		Session session = null;
		Document doc = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1(tramite);
		ValidationHelper.validarObjeto1(document);
		ValidationHelper.validarActor1( actor);
		
		String docId = document.getId();
		
		if(docId != null && !docId.isEmpty() ){
			doc = ServiceHelper.getDocumentById(session, docId);
		} else {
			doc = DocumentServiceHelper.getDocumentInFolder(session, tramite, document, actor );
		}
		return DocumentServiceHelper.createDocumentTO( doc );	
	}
	
/**
 *<br>*************************** DESCRIPCION *****************************
 *<br>CU: Crea documento nuevo en alfresco
 *<br>Metodo que crea o actualiza un documento nuevo.
 *<br> 
 *<br>************************* LISTA DE ERRORES **************************
 *<br>
 *<br> 1.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
 *<br> 2.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
 *<br> 3.- Contetype inexistente : 31-ERROR_ESTE_CONTENTTYPE_NOEXISTE
 *<br> 4.- Documento Inexistente: 30-ERROR_DOCUMENTO_YAEXISTE
 *<br> 5.- Identificador no encontrado: 36-ERROR_ID_NO_FOUND
 *<br> 6.- No se puede crear el documento: 29-ERROR_DOCUMENTO_NOCREADO
 *<br>
 *<br> 1. 
 *<br>************************** DISENO TECNICO ***************************
 *<br>
 *<br> Validaciones
 *<br>---------------------------------------------------------------------
 *<br>
 *<br> 1.- ValidacionObligatorios = VERDADERO
 *<br> SI createDocumentRequest.usuario = null OR  createDocumentRequest.password = null 
 *		OR createDocumentRequest.idDocumento
 *<br>        resultadoValidacion = FALSO     
 *<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
 *<br> FIN SI
 *<br> 
 *<br> 2.- validacionFormato = VERDADERO
 *<br> SI  (formato de createDocumentRequest.usuario && createDocumentRequest.password) != 
 *<br>	   (ALFANUMERICO - 1-50 posiciones)
 *<br> AND (createDocumentRequest.idDocumento) != (ALFABUMERICO [a-zA-Z0-9\\-\\:\\/] - 1-60 posiciones)
 *<br>      resultadoValidacion = FALSO    
 *<br>      error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
 *<br> FIN SI
 *<br>
 *<br>Acciones
 *<br>---------------------------------------------------------------------
 *<br>
 *<br>	1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
 *<br>	   valida a traves del objeto Session	 
 *<br>  2. Se obtiene un objeto Cmis apartir del Id del documento.
 *<br>  3. Se valida que el objeto cmis no sea nulo.
 *<br>  4. Se busca valida si es una actualizacion o se va crear un documento nuevo.
 *<br>  5. Si el identificador del documento esta vacio, se crea un nuevo documento. 
 *<br>  6. Si el identificador del documento esta informado, se actualiza el documento 
 *<br>		existente.
 *<br>  7. Se retorna la respuesta de la creacion o actualizacion del documento.
 *<br>Control de Errores
 *<br>---------------------------------------------------------------------
 *<br>
 *<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
 *<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
 *<br> 3.- Se genera un error 31-ERROR_ESTE_CONTENTTYPE_NOEXISTE en caso de que el contettype no exista.
 *<br> 4.- Se genera un error 30-ERROR_DOCUMENTO_YAEXISTE en caso de que documento existe.
 *<br> 5.- Se genera un error 36-ERROR_ID_NO_FOUND identifocador del documento no exista.
 *<br> 6.- Se genera un error 29-ERROR_DOCUMENTO_NOCREADO cuando no se puede crear el documento.
 *<br> 
 *<br>
 *<br>*********************************************************************/
	@Override
	public BaseResponseTO createDocument(TramiteTO tramite, ActorTO actor, DocumentTO document) throws BovedaPersonalServicesException {
		BaseResponseTO response = null;
		Session session = null;
		Folder parent = null;
		ObjectId id = null;
		Map<String, Object> properties = null;
	
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1(tramite);
		ValidationHelper.validarDocumento1(document);
		ValidationHelper.validarActor1( actor);
		
		
		properties = new HashMap<String, Object>();
		ServiceHelper.addPlainProperties(properties, document.getName(), BovedaConstants.DOCUMENT_TYPE );
		ServiceHelper.addPropertiesMap(properties, tramite, actor, document );
		Document doc = DocumentServiceHelper.getDocumentInFolder(session, tramite, document, actor );
		ContentStream cs = DocumentServiceHelper.createContentStream( document );
		if( doc == null ){
			parent = DocumentServiceHelper.getDocumentFolder(session, tramite, document, actor );
			parent.createDocument(properties, cs, VersioningState.MAJOR);
			
			
			response = new BaseResponseTO();
			response.setExitoso( true );
		} else {
			id = doc.checkOut();
			doc = ServiceHelper.getDocumentById(session, id.getId() );
			doc.refresh();
			try {
				id = doc.checkIn(true, null, cs, " ");
				response = new BaseResponseTO();
				response.setExitoso( true );
			} catch ( Exception e ){
				doc.cancelCheckOut();
				throw BovedaPersonalServicesExceptionBuilder
				.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENT_VERSION_NOT_FOUND );
			}
		}
		return response;
	}

/**
*<br>*************************** DESCRIPCION *****************************
*<br>CU:Eliminar documento 
*<br>Metodo que elimina el docuemtno correspondiente al id de docuemnto 
*<br> 
*<br>************************* LISTA DE ERRORES **************************
*<br>
*<br> 1.- Parametros no recibidos: 101-ERROR_PARAMETRO_OBLIGATORIO 
*<br> 2.- Parametros incorrectos: 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
*<br> 3.- Documento no encontrado : 9-ERROR_DOCUMENT_TYPE
*<br> 4.- Objeto no es documento: 9-ERROR_DOCUMENT_TYPE
*<br> 5.- Documento inexistente: 6-ERROR_DOCUMENT_NO_FOUND
*<br> 6.- Falla al eliminar documento: 32-ERROR_ELDOCUEMENTO_NOSEPUDO_ELIMINAR
*<br>
*<br> 
*<br>************************** DISENO TECNICO ***************************
*<br>
*<br> Validaciones
*<br>---------------------------------------------------------------------
*<br>
*<br> 1.- ValidacionObligatorios = VERDADERO
*<br> SI deleteDocumentRequest.usuario = null OR  deleteDocumentRequest.password = null 
*<br>		OR deleteDocumentRequest.idDocumento
*<br>        resultadoValidacion = FALSO     
*<br>        error 101-ERROR_PARAMETRO_OBLIGATORIO
*<br> FIN SI
*<br> 
*<br> 2.- validacionFormato = VERDADERO
*<br> SI  (formato de deleteDocumentRequest.usuario && deleteDocumentRequest.password) != 
*<br>		(ALFANUMERICO - 1-50 posiciones)
*<br> AND (deleteDocumentRequest.idDocumento) != (ALFABUMERICO [a-zA-Z0-9\\-\\:\\/] - 1-60 posiciones) 
*<br>      resultadoValidacion = FALSO    
*<br>      error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO
*<br> FIN SI
*<br>
*<br>
*<br>Acciones
*<br>---------------------------------------------------------------------
*<br>
*<br> 1. Se invoca el metodo getSesion del SessionProvider para obtener una sesion
*<br>	   valida a traves del objeto Session	 
*<br> 2. Se obtiene un objeto Cmis apartir del Id del documento.
*<br> 3. Se valida que el objeto cmis no sea nulo y que sea de typo documento.
*<br> 4. Si el paso tres es correcto se elimina el documento 
*<br> 5 .Si se elimina el docuento se retorna un String 
*<br>
*<br>Control de Errores
*<br>---------------------------------------------------------------------
*<br>
*<br> 1.- Se genera un error 101-ERROR_PARAMETRO_OBLIGATORIO en caso de que no se reciban todos los parametros obligatorios 
*<br> 2.- Se genera un error 102-ERROR_PARAMETRO_FORMATO_INCORRECTO en caso de que se reciban parametros con formato erroneo. 
*<br> 3.- Se genera un error 9-ERROR_DOCUMENT_TYPE cuando el documento no es tipo documento.
*<br> 4.- Se genera un error 6-ERROR_DOCUMENT_NO_FOUND cuando no se puede encontrar el documento.
*<br> 5.- Se genera un error 32-ERROR_ELDOCUEMENTO_NOSEPUDO_ELIMINAR cuando no se puede eliminar el documento.
*<br> 
*<br>
*<br>*********************************************************************/
	@Override
	public BaseResponseTO deleteDocument(TramiteTO tramite, ActorTO actor,
			BaseObjectTO document) throws BovedaPersonalServicesException {
		BaseResponseTO response = null;
		Session session = null;
		Document doc = null;
		Map<String, Object> properties = null;
		
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1( tramite );
		ValidationHelper.validarObjeto1( document );
		ValidationHelper.validarActor1( actor);
		
		
		String docId = document.getId();
		
		if(docId != null && !docId.isEmpty() ){
			doc = ServiceHelper.getDocumentById(session, docId,Boolean.valueOf(actor.getIsOwner()));
		} else {
			doc = DocumentServiceHelper.getDocumentInFolder(session, tramite, document, actor );
		}
		
		properties = new HashMap<String, Object>();
		if(Boolean.valueOf(actor.getIsOwner())){
//			properties.put(BovedaConstants.IMSS_USER_BORRADO, Boolean.TRUE);
			properties.put(BovedaConstants.IMSS_USER_BORRADO, Boolean.TRUE);
		}else{
			properties.put(BovedaConstants.IMSS_BORRADO, Boolean.TRUE);
		}
		
		if(doc == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO);
		}else{
			doc.updateProperties(properties);
		}
		
		response = new BaseResponseTO();
		response.setExitoso(true);
		return response;
	}

	@Override
	public List<MetadataTO> getAllMetadataByMetadata(TramiteTO tramite,
			ActorTO actor, MetadataTO metadata)
			throws BovedaPersonalServicesException {
		
		Session session = SessionProvider.getSessionProvider().getSession();
		
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1( tramite );
		ValidationHelper.validarActor1( actor );
		ValidationHelper.validarMetadatos1( metadata );
		
		return DocumentServiceHelper.getAllMetadataByMetadata(session, tramite, metadata, actor);
	}	

	@Override
	public BaseResponseTO addActor(TramiteTO tramite, BaseObjectTO document,
			ActorTO actor, ActorTO newActor)
			throws BovedaPersonalServicesException {
		Session session = null;
		session = SessionProvider.getSessionProvider().getSession();
		if(session == null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}
		
		ValidationHelper.validarTramite1( tramite );
		ValidationHelper.validarObjeto1( document );
		ValidationHelper.validarActor1( actor );
		ValidationHelper.validarActor1( newActor );
		
		DocumentServiceHelper.addActor(session, tramite, document, actor, newActor);
		return null;
	}	
}
