package mx.gob.imss.cit.bp.integration.api;

import java.util.List;

import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataResponseTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;



/**
 * Interface que contiene metodos referentes al manejo
 * de documentos en la capa de integrator
 * 
 * @author Admin
 * 
 */
public interface IDocumentoIntegrator {
	/**
	 *  Metodo que crea/acualiza un documento.
	 * 
	 * @param createDocumentRequest   contiene los datos para crear o actualiza documento.
	 * @return createDocumentResponse id documento creado o mensaje de update con exito
	 * @throws BovedaPersonalServicesException
	 */	
	BaseResponseTO  createDocument(TramiteTO tramite, ActorTO actor, DocumentTO document );
	/**
	 * Metodo que elimina logicamente un documento.
	 * @param deleteDocumentRequest contiene los datos de usuario para la sesion.
	 * @return deleteDocumentResponse contiene mensaje de exito
	 * @throws BovedaPersonalServicesException
	 */
	BaseResponseTO deleteDocument(TramiteTO tramite, ActorTO actor, BaseObjectTO document);
	/**
	 * obtiene un documento apartir de los datos del tramite
	 * @param documentPathRequest contiene el path del documento.
	 * @return documentByPathResponse contiene el documento.
	 * @throws BovedaPersonalServicesException
	 */	
	DocumentResponseTO getDocument(TramiteTO tramite, ActorTO actor, BaseObjectTO document);
	/**
	 * Metodo que obtiene el detalle y/o propiedades de un documento y por version.
	 * 
	 * @param documentMetadataRequest contiene los datos para obtener el documento.
	 * @return  lDocumentMetadataDocumentResponse contiene las propiedades del documento
	 * @throws BovedaPersonalServicesException
	 */
	MetadataResponseTO getMetadataByDoc(TramiteTO tramite, ActorTO actor, BaseObjectTO document); 
	/**
	 * Metodo que obtiene las versiones del documento.
	 * 
	 * @param allDocumentVersionsRequest contiene los datos de usuario para la sesion.
	 * @return allDocumentVersionsResponse contiene los documentos
	 * @throws BovedaPersonalServicesException
	 */	
	List<DocumentResponseTO> getAllDocumentVersionsByDoc(TramiteTO tramite, ActorTO actor, BaseObjectTO document);
	/**
	 * Metodo que obtiene los metadatas de las versiones de un documento
	 * 
	 * @param metadataHistoryRequest lista que contiene nombres de los documentos a consultar.
	 * @return lMetadataHistoryDocumentoResponse contiene lista de metadinformacion de los documentos.
	 * @throws BovedaPersonalServicesException
	 */
	List<MetadataResponseTO> getAllDocumentVersionsMetadataByDoc(TramiteTO tramite, ActorTO actor, BaseObjectTO document ); 
	/**
	 * Metodo que obtiene el detalle y/o propiedades los documentos de acuerdo al criterio de busqueda 
	 * 			(usuario, tipo documento, nombre del documento, fecha de creacion y modificacion).
	 * @param documentFromMetadataRequest contien datos para buscar los documentos.
	 * @return lDocumentFromMetadataDocumentoResponse regresa la lista de metadata de los documentos.
	 * @throws BovedaPersonalServicesException
	 */
	List<DocumentResponseTO> findDocumentsByMetadata(TramiteTO tramite, ActorTO actor, BaseObjectTO document, MetadataTO metadata);
	/**
	 * Metodo que obtiene solo los metadatos de los documentos de un folder
	 * 
	 * @param allMetadataFromFolderRequest  contiene los datos para busqueda de documentos en una carpeta.
	 * @return lAllMetadataFromFolderDocumentResponse contiene lista de documentos con metadata
	 * @throws BovedaPersonalServicesException	
	 **/ 
	List<MetadataResponseTO> getAllMetadataByMetadata(TramiteTO tramite, ActorTO actor, BaseObjectTO document, MetadataTO metadata);
	/**
	 * Metodo que obtiene agrega un actor a determinado documento.
	 * 
	 * @param AddDocumentActorRequest  contiene los datos del documento y actor.
	 * @return AddDocumentActorResponse contien la respuesta de la solicitud
	 * @throws BovedaPersonalServicesException	
	 **/ 
	BaseResponseTO addDocumentActor(TramiteTO tramite, ActorTO actor, BaseObjectTO document, ActorTO newActor);
}