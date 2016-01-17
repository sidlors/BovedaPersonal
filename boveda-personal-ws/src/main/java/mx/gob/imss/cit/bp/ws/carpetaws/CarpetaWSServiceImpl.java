package mx.gob.imss.cit.bp.ws.carpetaws;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import mx.gob.imss.cit.bp.integration.api.ICarpetaIntegrator;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.FolderResponseTO;
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.TramiteTO;
import mx.gob.imss.cit.bp.ws.AddFolderActorRequest;
import mx.gob.imss.cit.bp.ws.AddFolderActorResponse;
import mx.gob.imss.cit.bp.ws.CreateFolderRequest;
import mx.gob.imss.cit.bp.ws.CreateFolderResponse;
import mx.gob.imss.cit.bp.ws.FolderDescendantsRequest;
import mx.gob.imss.cit.bp.ws.FolderDescendantsResponse;
import mx.gob.imss.cit.bp.ws.FolderDocumentsRequest;
import mx.gob.imss.cit.bp.ws.FolderDocumentsResponse;
import mx.gob.imss.cit.bp.ws.FolderObjectsRequest;
import mx.gob.imss.cit.bp.ws.FolderObjectsResponse;
import mx.gob.imss.cit.bp.ws.UserFolderRequest;
import mx.gob.imss.cit.bp.ws.UserFolderResponse;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Actor;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.BaseObject;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.BaseResponse;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Document;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Folder;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Tramite;
import mx.gob.imss.cit.bp.ws.util.MapperUtil;

@WebService
public class CarpetaWSServiceImpl implements ICarpetaWSService {

	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private ICarpetaIntegrator carpetaIntegrator;

	@Override
	public CreateFolderResponse createFolder(CreateFolderRequest request) {
		CreateFolderResponse response = null;
		BaseResponseTO to = null;
		BaseResponse base = null;

		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();

		TramiteTO tramiteTO = MapperUtil.extractTramite(tramite);
		ActorTO actorTO = MapperUtil.extractActor(actor);
		BaseObjectTO baseTO = MapperUtil.extractBaseObject(baseObject);

		to = carpetaIntegrator.createFolder(tramiteTO, actorTO, baseTO);
		base = MapperUtil.writeBaseResponse(to);
		response = new CreateFolderResponse();
		response.setResponse(base);
		return response;
	}

	@Override
	public UserFolderResponse getUserFolder(UserFolderRequest request) {
		UserFolderResponse response = null;

		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseResponse base = null;
		BaseObject baseObject = request.getObject();

		TramiteTO tramiteTO = MapperUtil.extractTramite(tramite);
		ActorTO actorTO = MapperUtil.extractActor(actor);
		BaseObjectTO baseTO = MapperUtil.extractBaseObject(baseObject);

		List<FolderResponseTO> serviceResponse = carpetaIntegrator
				.getUserFolder(tramiteTO, actorTO, baseTO);
		response = new UserFolderResponse();

		base = MapperUtil.writeBaseResponse(serviceResponse.get(0));
		if (base.isExitoso()) {

			for (FolderResponseTO fr : serviceResponse) {
				FolderTO tempTO = fr.getFolderTO();
				Folder temp = MapperUtil.writeFolder(tempTO);
				response.getFolder().add(temp);
			}
			response.setExitoso(true);

		} else {

			response.setExcepcionCausa(base.getExcepcionCausa());
			response.setExcepcionCodigo(base.getExcepcionCodigo());
			response.setExcepcionMensaje(base.getExcepcionMensaje());
			response.setExitoso(base.isExitoso());
		}

		return response;
	}

	@Override
	public FolderDocumentsResponse getFolderDocuments(
			FolderDocumentsRequest request) {
		FolderDocumentsResponse response = null;

		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		BaseResponse base = null;
		TramiteTO tramiteTO = MapperUtil.extractTramite(tramite);
		ActorTO actorTO = MapperUtil.extractActor(actor);
		BaseObjectTO baseTO = MapperUtil.extractBaseObject(baseObject);

		List<DocumentResponseTO> serviceResponse = carpetaIntegrator
				.getFolderDocuments(tramiteTO, actorTO, baseTO);
		response = new FolderDocumentsResponse();
		base = MapperUtil.writeBaseResponse(serviceResponse.get(0));
		if (base.isExitoso()) {
			for (DocumentResponseTO fr : serviceResponse) {
				DocumentTO tempTO = fr.getDocument();
				Document temp = MapperUtil.writeDocument(tempTO);
				response.getResponse().add(temp);
			}
		} else {
			response.setExcepcionCausa(base.getExcepcionCausa());
			response.setExcepcionCodigo(base.getExcepcionCodigo());
			response.setExcepcionMensaje(base.getExcepcionMensaje());
			response.setExitoso(base.isExitoso());

		}
		return response;
	}

	@Override
	public FolderObjectsResponse getFolderObjects(FolderObjectsRequest request) {
		FolderObjectsResponse response = null;

		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		BaseResponse base = null;
		TramiteTO tramiteTO = MapperUtil.extractTramite(tramite);
		ActorTO actorTO = MapperUtil.extractActor(actor);
		BaseObjectTO baseTO = MapperUtil.extractBaseObject(baseObject);

		List<FolderResponseTO> serviceResponse = carpetaIntegrator
				.getFolderObjects(tramiteTO, actorTO, baseTO);
		response = new FolderObjectsResponse();
		base = MapperUtil.writeBaseResponse(serviceResponse.get(0));
		if (base.isExitoso()) {
			for (FolderResponseTO fr : serviceResponse) {
				FolderTO tempTO = fr.getFolderTO();
				Folder temp = MapperUtil.writeFolder(tempTO);
				response.getObjectList().add(temp);
			}
			response.setExitoso(true);
		} else {
			response.setExcepcionCausa(base.getExcepcionCausa());
			response.setExcepcionCodigo(base.getExcepcionCodigo());
			response.setExcepcionMensaje(base.getExcepcionMensaje());
			response.setExitoso(base.isExitoso());

		}
		return response;
	}

	@Override
	public FolderDescendantsResponse getFolderDescendants(
			FolderDescendantsRequest request) {
		FolderDescendantsResponse response = null;
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		BaseResponse base = null;
		TramiteTO tramiteTO = MapperUtil.extractTramite(tramite);
		ActorTO actorTO = MapperUtil.extractActor(actor);
		BaseObjectTO baseTO = MapperUtil.extractBaseObject(baseObject);

		List<FolderResponseTO> serviceResponse = carpetaIntegrator
				.getFolderDescendants(tramiteTO, actorTO, baseTO);
		response = new FolderDescendantsResponse();
		base = MapperUtil.writeBaseResponse(serviceResponse.get(0));
		if (base.isExitoso()) {
			for (FolderResponseTO fr : serviceResponse) {
				FolderTO tempTO = fr.getFolderTO();
				Folder temp = MapperUtil.writeFolder(tempTO);
				response.getArbol().add(temp);

			}
			response.setExitoso(true);
		} else {

			response.setExcepcionCausa(base.getExcepcionCausa());
			response.setExcepcionCodigo(base.getExcepcionCodigo());
			response.setExcepcionMensaje(base.getExcepcionMensaje());
			response.setExitoso(base.isExitoso());

		}

		return response;
	}

	@Override
	public AddFolderActorResponse addFolderActor(AddFolderActorRequest request) {
		AddFolderActorResponse response = null;
		BaseResponseTO to = null;
		BaseResponse base = null;

		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		Actor newActor = request.getNewActor();
		BaseObject baseObject = request.getObject();

		TramiteTO tramiteTO = MapperUtil.extractTramite(tramite);
		ActorTO actorTO = MapperUtil.extractActor(actor);
		ActorTO newActorTO = MapperUtil.extractActor(newActor);
		BaseObjectTO baseTO = MapperUtil.extractBaseObject(baseObject);

		to = carpetaIntegrator.addFolderActor(tramiteTO, baseTO, actorTO,
				newActorTO);
		base = MapperUtil.writeBaseResponse(to);
		response = new AddFolderActorResponse();
		response.setResponse(base);
		return response;
	}
}
