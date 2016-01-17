package mx.gob.imss.cit.bp.services;

import java.util.List;

import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

/**
 * Interface que contiene metodos referentes al manejo
 * de documentos
 * 
 * @author Admin
 * 
 */
public interface IDocumentoService {

	/**
	 *  Metodo que crea/acualiza un documento.
	 * 
	 * @param createDocumentRequest   contiene los datos para crear o actualiza documento.
	 * @return createDocumentResponse id documento creado o mensaje de update con exito
	 * @throws BovedaPersonalServicesException
	 */	
	BaseResponseTO  createDocument( TramiteTO tramite,  ActorTO actor, DocumentTO doc ) throws BovedaPersonalServicesException;
	/**
	 * Metodo que elimina un documento logico.
	 * @param deleteDocumentRequest contiene los datos de usuario para la sesion.
	 * @return deleteDocumentResponse contiene mensaje de exito
	 * @throws BovedaPersonalServicesException
	 */
	BaseResponseTO deleteDocument( TramiteTO tramite,  ActorTO actor, BaseObjectTO base)throws BovedaPersonalServicesException;
	/**
	 * obtiene un documento apartir del 
	 * @param documentPathRequest contiene el path del documento.
	 * @return documentByPathResponse contiene el documento.
	 * @throws BovedaPersonalServicesException
	 */	
	DocumentTO getDocument( TramiteTO tramite,  ActorTO actor, BaseObjectTO document ) throws BovedaPersonalServicesException;
	/**
	 * Metodo que obtiene el detalle y/o propiedades de un documento y por version.
	 * 
	 * @param documentMetadataRequest contiene los datos para obtener el documento.
	 * @return  lDocumentMetadataDocumentResponse contiene las propiedades del documento
	 * @throws BovedaPersonalServicesException
	 */
	MetadataTO getDocumentMetadata( TramiteTO tramite,  ActorTO actor, BaseObjectTO document) throws BovedaPersonalServicesException;
	/**
	 * Metodo que obtiene las versiones del documento.
	 * 
	 * @param allDocumentVersionsRequest contiene los datos de usuario para la sesion.
	 * @return allDocumentVersionsResponse contiene los documentos
	 * @throws BovedaPersonalServicesException
	 */	
	List<DocumentTO> getAllDocumentVersions( TramiteTO tramite,  ActorTO actor, BaseObjectTO document) throws BovedaPersonalServicesException;
	/**
	 * Metodo que obtiene los metadatas de las versiones de un documento
	 * 
	 * @param metadataHistoryRequest lista que contiene nombres de los documentos a consultar.
	 * @return lMetadataHistoryDocumentoResponse contiene lista de metadinformacion de los documentos.
	 * @throws BovedaPersonalServicesException
	 */
	List<MetadataTO> getAllDocumentMetadataVersions( TramiteTO tramite,  ActorTO actor, BaseObjectTO document) throws BovedaPersonalServicesException;
	/**
	 * Metodo que obtiene el detalle y/o propiedades los documentos de acuerdo al criterio de busqueda 
	 * 			(usuario, tipo documento, nombre del documento, fecha de creacion y modificacion).
	 * @param documentFromMetadataRequest contien datos para buscar los documentos.
	 * @return lDocumentFromMetadataDocumentoResponse regresa la lista de metadata de los documentos.
	 * @throws BovedaPersonalServicesException
	 */
	List<DocumentTO> findDocumentsByMetadata(  TramiteTO tramite,  ActorTO actor, MetadataTO metadata ) throws BovedaPersonalServicesException;
	/**
	 * Metodo que obtiene solo los metadatas de los documentos de un folder
	 * 
	 * @param allMetadataFromFolderRequest  contiene los datos para busqueda de documentos en una carpeta.
	 * @return lAllMetadataFromFolderDocumentResponse contiene lista de documentos con metadata
	 * @throws BovedaPersonalServicesException	
	 **/ 
	List<MetadataTO> getAllMetadataByMetadata( TramiteTO tramite,  ActorTO actor, MetadataTO metadata ) throws BovedaPersonalServicesException;
	/**
	 * Metodo que agrega un actor a un documento
	 * 
	 * @param allMetadataFromFolderRequest  contiene los datos para busqueda de documentos en una carpeta.
	 * @return lAllMetadataFromFolderDocumentResponse contiene lista de documentos con metadata
	 * @throws BovedaPersonalServicesException	
	 **/ 
	BaseResponseTO addActor(TramiteTO tramite, BaseObjectTO document, ActorTO actor, ActorTO newActor) throws BovedaPersonalServicesException;
}