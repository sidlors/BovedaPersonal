package mx.gob.imss.cit.bp.integration.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.bp.integration.api.ICarpetaIntegrator;
import mx.gob.imss.cit.bp.services.ICarpetaService;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.util.ServiceHelper;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.FolderResponseTO;
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que integra los servicios referentes al manejo de carpetas
 * 
 * @author admin
 *
 */
@Remote(ICarpetaIntegrator.class)
@Stateless(name="CarpetaIntegratorEscritorio",mappedName="CarpetaIntegratorEscritorio")
public class CarpetaIntegrator implements ICarpetaIntegrator{

	/**
	 * Referencia al CarpetaService
	 */
	@EJB
	private ICarpetaService iCarpetaService;
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CarpetaIntegrator.class);
	
	@Override
	public BaseResponseTO createFolder(TramiteTO tramite, ActorTO actor, BaseObjectTO base) {
		BaseResponseTO response = null;
		try {	
			response = iCarpetaService.createFolder( tramite, actor, base );
		} catch (BovedaPersonalServicesException e) {
			response = new BaseResponseTO();
//			response.setExcepcionCausa( e.getCause() );
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
		} catch (Exception e) {
			response = new BaseResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}
		
	
	@Override
	public List<FolderResponseTO> getUserFolder(TramiteTO tramite,
			ActorTO actor, BaseObjectTO base ) {
		List<FolderResponseTO> results = null;
		List<FolderTO> folderList = null;
		try{
			folderList = iCarpetaService.getUserFolder(tramite, actor, base);
			results = ServiceHelper.fillFolderResponseTOList( folderList );
		} catch (BovedaPersonalServicesException e) {
			results = new ArrayList<FolderResponseTO>();
			FolderResponseTO response = new FolderResponseTO();
//			response.setExcepcionCausa( e.getCause());
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
			results.add(response);
		} catch (Exception e) {
			FolderResponseTO response = new FolderResponseTO();
//			response.setExcepcionCausa( e.getCause() );
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add(response);
		}
		return results;
	}
	
	@Override
	public List<DocumentResponseTO> getFolderDocuments(TramiteTO tramite,
			ActorTO actor, BaseObjectTO base) {
		List<DocumentResponseTO> results = null;
		List<BaseObjectTO> documentList = null;
		try{
			documentList = iCarpetaService.getFolderDocuments(tramite, actor, base);
			results = ServiceHelper.fillDocumentResponseTOListFromBaseTO( documentList );
		} catch (BovedaPersonalServicesException e) {
			results = new ArrayList<DocumentResponseTO>();
			DocumentResponseTO response = new DocumentResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
			results.add(response);
		} catch (Exception e) {
			DocumentResponseTO response = new DocumentResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add(response);
		}
		return results;
	}

	@Override
	public List<FolderResponseTO> getFolderObjects(TramiteTO tramite,
			ActorTO actor, BaseObjectTO base) {
		List<FolderResponseTO> results = null;
		List<FolderTO> folderList = null;
		try {
			folderList = iCarpetaService.getFolderObjects(tramite, actor, base);
			results = ServiceHelper.fillFolderResponseTOList( folderList );
		} catch (BovedaPersonalServicesException e) {
			results = new ArrayList<FolderResponseTO>();
			FolderResponseTO response = new FolderResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
			results.add(response);
		} catch (Exception e) {
			FolderResponseTO response = new FolderResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add(response);
		}
		return results;	
	}

	

	@Override
	public List<FolderResponseTO> getFolderDescendants(TramiteTO tramite,
			ActorTO actor, BaseObjectTO base) {
		List<FolderResponseTO> results = null;
		List<FolderTO> folderList = null;
		try {
			folderList = iCarpetaService.getFolderDescendants(tramite, actor, base);
			results = ServiceHelper.fillFolderResponseTOList( folderList );
		} catch (BovedaPersonalServicesException e) {
			results = new ArrayList<FolderResponseTO>();
			FolderResponseTO response = new FolderResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
			results.add(response);
		} catch (Exception e) {
			FolderResponseTO response = new FolderResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
			results.add(response);
		}
		return results;	
	}

	@Override
	public BaseResponseTO addFolderActor(TramiteTO tramite, BaseObjectTO base,
			ActorTO actor, ActorTO newActor) {
		BaseResponseTO response = null;
		try {
			response = iCarpetaService.addFolderActor(tramite, base, actor, newActor);
			response.setExitoso(true);
		} catch (BovedaPersonalServicesException e) {
			response = new BaseResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionCodigo( e.getCode() );
			response.setExcepcionMensaje( e.getDescription());
			response.setExitoso(false);
		} catch (Exception e) {
			response = new BaseResponseTO();
//			response.setExcepcionCausa( e.getCause().toString() );
			response.setExcepcionMensaje( e.getMessage());
			response.setExitoso(false);
		}
		return response;
	}
}
