package mx.gob.imss.cit.bp.integration.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.bp.integration.api.IDocumentoIntegrator;
import mx.gob.imss.cit.bp.services.IDocumentoService;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataResponseTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que integra los servicios referentes al manejo de documentos
 * 
 * @author admin
 *
 */

@Remote(IDocumentoIntegrator.class)
@Stateless(name="DocumentoIntegratorEscritorio",mappedName="DocumentoIntegratorEscritorio")
public class DocumentoIntegrator implements IDocumentoIntegrator {
	
	/**
	 * Referencia al DocumentoService
	 */
	@EJB
	private IDocumentoService iDocumentoService;
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DocumentoIntegrator.class);
	
	@Override
	public BaseResponseTO createDocument(TramiteTO tramite, ActorTO actor, DocumentTO doc) {
		BaseResponseTO response = null;
		try {	
			response = iDocumentoService.createDocument(tramite, actor, doc);
			if(response == null){
				response = new BaseResponseTO();
				response.setExitoso(true);
			}
		} catch (BovedaPersonalServicesException e) {
			response = new BaseResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
		} catch (Exception e) {
			response = new BaseResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}
	
	@Override
	public BaseResponseTO deleteDocument(TramiteTO tramite, ActorTO actor, BaseObjectTO base) {
		BaseResponseTO response = null;
		try {	
			response = iDocumentoService.deleteDocument(tramite, actor, base);
		} catch (BovedaPersonalServicesException e) {
			response = new BaseResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
		} catch (Exception e) {
			response = new BaseResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}
	@Override
	public DocumentResponseTO getDocument(TramiteTO tramite, ActorTO actor, BaseObjectTO base) {
		DocumentResponseTO response = null;
		try {	
			DocumentTO document = iDocumentoService.getDocument(tramite, actor, base);
			response = new DocumentResponseTO();
			if(document != null){
				response.setDocument( document );
				response.setExitoso(true);
			}else{
				throw BovedaPersonalServicesExceptionBuilder
				   .build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO);
			}
			
		} catch (BovedaPersonalServicesException e) {
			response = new DocumentResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
		} catch (Exception e) {
			response = new DocumentResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}
	
	@Override
	public MetadataResponseTO getMetadataByDoc(TramiteTO tramite, ActorTO actor,
			BaseObjectTO document) {
		MetadataResponseTO response = null;
		try {		
			MetadataTO metadata = iDocumentoService.getDocumentMetadata(tramite, actor, document);
			response = new MetadataResponseTO();
			response.setMetadata(metadata);
		} catch (BovedaPersonalServicesException e) {
			response = new MetadataResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
		} catch (Exception e) {
			response = new MetadataResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}

	@Override
	public List<DocumentResponseTO> getAllDocumentVersionsByDoc(
			TramiteTO tramite, ActorTO actor, BaseObjectTO document) {
		List<DocumentResponseTO> results = new ArrayList<DocumentResponseTO>();
		try {	
			List<DocumentTO> documentList = iDocumentoService.getAllDocumentVersions(tramite, actor, document);
			for(DocumentTO doc : documentList){
				DocumentResponseTO temp = new DocumentResponseTO();
				temp.setDocument( doc );
				results.add( temp );
			}
		} catch (BovedaPersonalServicesException e) {
			DocumentResponseTO response = new DocumentResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
			results.add( response );
		} catch (Exception e) {
			DocumentResponseTO response = new DocumentResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add( response );
		}
		return results;
	}

	@Override
	public List<MetadataResponseTO> getAllDocumentVersionsMetadataByDoc(
			TramiteTO tramite, ActorTO actor, BaseObjectTO document) {
		List<MetadataResponseTO> results = new ArrayList<MetadataResponseTO>();
		try {	
			List<MetadataTO> metadataList = iDocumentoService.getAllDocumentMetadataVersions(tramite, actor, document);
			for(MetadataTO metadata : metadataList){
				MetadataResponseTO temp = new MetadataResponseTO();
				temp.setMetadata( metadata );
				results.add( temp );
			}
		} catch (BovedaPersonalServicesException e) {
			MetadataResponseTO response = new MetadataResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
			results.add( response );
		} catch (Exception e) {
			MetadataResponseTO response = new MetadataResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add( response );
		}
		return results;
	}

	@Override
	public List<DocumentResponseTO> findDocumentsByMetadata(TramiteTO tramite,
			ActorTO actor, BaseObjectTO document, MetadataTO metadata) {
		List<DocumentResponseTO> results = new ArrayList<DocumentResponseTO>();
		try {	
			List<DocumentTO> documentList = iDocumentoService.findDocumentsByMetadata(tramite, actor, metadata);
			for(DocumentTO doc : documentList){
				DocumentResponseTO temp = new DocumentResponseTO();
				temp.setDocument( doc );
				results.add( temp );
			}
		} catch (BovedaPersonalServicesException e) {
			DocumentResponseTO response = new DocumentResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
			results.add( response );
		} catch (Exception e) {
			DocumentResponseTO response = new DocumentResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add( response );
		}
		return results;
	}

	@Override
	public List<MetadataResponseTO> getAllMetadataByMetadata(TramiteTO tramite,
			ActorTO actor, BaseObjectTO document, MetadataTO metadata) {
		List<MetadataResponseTO> results = new ArrayList<MetadataResponseTO>();
		try {	
			List<MetadataTO> metadataList = iDocumentoService.getAllMetadataByMetadata(tramite, actor, metadata);
			for(MetadataTO md : metadataList){
				MetadataResponseTO temp = new MetadataResponseTO();
				temp.setMetadata( md );
				results.add( temp );
			}
		} catch (BovedaPersonalServicesException e) {
			MetadataResponseTO response = new MetadataResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
			results.add( response );
		} catch (Exception e) {
			MetadataResponseTO response = new MetadataResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add( response );
		}
		return results;
	}

	@Override
	public BaseResponseTO addDocumentActor(TramiteTO tramite, ActorTO actor,
			BaseObjectTO document, ActorTO newActor) {
		BaseResponseTO response = null;
		try {
			response = iDocumentoService.addActor(tramite, document, actor, newActor);
		} catch (BovedaPersonalServicesException e) {
			response = new BaseResponseTO();
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription() );
			response.setExitoso(false);
		} catch (Exception e) {
			response = new BaseResponseTO();
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}
}	

