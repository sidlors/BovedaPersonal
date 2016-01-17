package mx.gob.imss.cit.bp.ws.documentows;

import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.gob.imss.cit.bp.integration.api.IDocumentoIntegrator;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataResponseTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;
import mx.gob.imss.cit.bp.ws.AddDocumentActorRequest;
import mx.gob.imss.cit.bp.ws.AddDocumentActorResponse;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsByDocRequest;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsByDocResponse;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsMetadataByDocRequest;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsMetadataByDocResponse;
import mx.gob.imss.cit.bp.ws.AllMetadataByMetadataRequest;
import mx.gob.imss.cit.bp.ws.AllMetadataByMetadataResponse;
import mx.gob.imss.cit.bp.ws.CreateDocumentRequest;
import mx.gob.imss.cit.bp.ws.CreateDocumentResponse;
import mx.gob.imss.cit.bp.ws.DeleteDocumentRequest;
import mx.gob.imss.cit.bp.ws.DeleteDocumentResponse;
import mx.gob.imss.cit.bp.ws.DocumentRequest;
import mx.gob.imss.cit.bp.ws.DocumentResponse;
import mx.gob.imss.cit.bp.ws.DocumentsByMetadataRequest;
import mx.gob.imss.cit.bp.ws.DocumentsByMetadataResponse;
import mx.gob.imss.cit.bp.ws.MetadataByDocRequest;
import mx.gob.imss.cit.bp.ws.MetadataByDocResponse;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Actor;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.BaseObject;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.BaseResponse;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Document;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Metadata;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Tramite;
import mx.gob.imss.cit.bp.ws.util.MapperUtil;

@WebService
public class DocumentoWSServiceImpl implements IDocumentoWSService {

	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private IDocumentoIntegrator documentoIntegrator;
	
	private static final Logger LOG = LoggerFactory.getLogger(DocumentoWSServiceImpl.class);

	@Override
	public CreateDocumentResponse createDocument(CreateDocumentRequest request) {
		LOG.info("");
		CreateDocumentResponse response = null;
		BaseResponseTO serviceResponse = null;
		BaseResponse base = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		Document document = request.getDocument();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		DocumentTO documentTO = MapperUtil.extractDocument(document);
		
		serviceResponse = documentoIntegrator.createDocument( tramiteTO, actorTO, documentTO);
		base =  MapperUtil.writeBaseResponse( serviceResponse );
		response = new CreateDocumentResponse();
		response.setResponse( base );
		return response;
	}

	@Override
	public DeleteDocumentResponse deleteDocument(DeleteDocumentRequest request) {
		DeleteDocumentResponse response = null;
		BaseResponseTO serviceResponse = null;
		BaseResponse base = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		
		serviceResponse = documentoIntegrator.deleteDocument( tramiteTO, actorTO, baseTO);
		base =  MapperUtil.writeBaseResponse( serviceResponse );
		response = new DeleteDocumentResponse();
		response.setResponse( base );
		return response;
	}

	@Override
	public DocumentResponse getDocument(DocumentRequest request) {
		DocumentResponse response = null;
		DocumentResponseTO serviceResponse = null;
		Document document = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		
		serviceResponse = documentoIntegrator.getDocument( tramiteTO, actorTO, baseTO);
		response = new DocumentResponse();

		DocumentTO temp = serviceResponse.getDocument();
		if( temp != null){
			document =  MapperUtil.writeDocument( temp );
			response.setDocument( document );
		}
		if(!serviceResponse.isExitoso()){
			response.setExcepcionCausa(serviceResponse.getExcepcionCausa());
			response.setExcepcionCodigo(serviceResponse.getExcepcionCodigo());
			response.setExcepcionMensaje(serviceResponse.getExcepcionMensaje());
		}
		response.setExitoso( serviceResponse.isExitoso() );
		return response;
	}

	@Override
	public MetadataByDocResponse getMetadataByDoc(MetadataByDocRequest request) {
		//FALTA SETTEAR LA VARIABLE EXITOSO EN NLOS OBJETOS RESPONSE
		MetadataByDocResponse response = null;
		MetadataResponseTO serviceResponse = null;
		Metadata metadata = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		
		serviceResponse = documentoIntegrator.getMetadataByDoc(tramiteTO, actorTO, baseTO );
		response = new MetadataByDocResponse();
		if(serviceResponse.isExitoso()){
			metadata =  MapperUtil.writeMetadata( serviceResponse.getMetadata() );
			response.setMetadata( metadata );
		}else{
			response.setExcepcionCausa(serviceResponse.getExcepcionCausa());
			response.setExcepcionCodigo(serviceResponse.getExcepcionCodigo());
			response.setExcepcionMensaje(serviceResponse.getExcepcionMensaje());
			response.setExitoso(serviceResponse.isExitoso());
		}
		return response;
	}

	@Override
	public AllDocumentVersionsByDocResponse getAllDocumentVersionsByDoc(
			AllDocumentVersionsByDocRequest request) {
		AllDocumentVersionsByDocResponse response = null;
		List<DocumentResponseTO> serviceResponse = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		
		serviceResponse = documentoIntegrator.getAllDocumentVersionsByDoc(tramiteTO, actorTO, baseTO);
		
		for( DocumentResponseTO fr : serviceResponse ){
			response = new AllDocumentVersionsByDocResponse();
			DocumentTO tempTO = fr.getDocument();
			if(fr.isExitoso()){
				Document temp = MapperUtil.writeDocument( tempTO );
				response.getDocument().add(temp);
			}else{
				response.setExcepcionCausa(fr.getExcepcionCausa());
				response.setExcepcionCodigo(fr.getExcepcionCodigo());
				response.setExcepcionMensaje(fr.getExcepcionMensaje());
				response.setExitoso(fr.isExitoso());
			}
		}
		return response;
	}

	@Override
	public AllDocumentVersionsMetadataByDocResponse getAllDocumentVersionsMetadataByDoc(
			AllDocumentVersionsMetadataByDocRequest request) {
		AllDocumentVersionsMetadataByDocResponse response = null;
		List<MetadataResponseTO> serviceResponse = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		serviceResponse = documentoIntegrator.getAllDocumentVersionsMetadataByDoc( tramiteTO, actorTO, baseTO );
		
		for(MetadataResponseTO tempTO : serviceResponse){
			response = new AllDocumentVersionsMetadataByDocResponse();
			if(tempTO.isExitoso()){
				MetadataTO metadataTO = tempTO.getMetadata();
				Metadata metadata = MapperUtil.writeMetadata( metadataTO );
				response.getMetadata().add( metadata );
			}else{
				response.setExcepcionCodigo(tempTO.getExcepcionCodigo());
				response.setExcepcionMensaje(tempTO.getExcepcionMensaje());
				response.setExitoso(tempTO.isExitoso());
			}
		}
		return response;
		
	}

	@Override
	public DocumentsByMetadataResponse findDocumentsByMetadata(
			DocumentsByMetadataRequest request) {
		DocumentsByMetadataResponse response = null;
		List<DocumentResponseTO> serviceResponse = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		Metadata metadata = request.getMetadata();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		MetadataTO metadataTO = MapperUtil.extractMetadata( metadata );
		
		serviceResponse = documentoIntegrator.findDocumentsByMetadata( tramiteTO, actorTO, baseTO, metadataTO );
		
		for( DocumentResponseTO fr : serviceResponse ){
			response = new DocumentsByMetadataResponse();
			if(fr.isExitoso()){
				DocumentTO tempTO = fr.getDocument();
				Document temp = MapperUtil.writeDocument( tempTO );
				response.getDocument().add(temp);
			}else{
				response.setExcepcionCausa(fr.getExcepcionCausa());
				response.setExcepcionCodigo(fr.getExcepcionCodigo());
				response.setExcepcionMensaje(fr.getExcepcionMensaje());
			}
		}
		return response;
	}

	@Override
	public AllMetadataByMetadataResponse getAllMetadataByMetadata(
			AllMetadataByMetadataRequest request) {
		AllMetadataByMetadataResponse response = null;
		List<MetadataResponseTO> serviceResponse = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		BaseObject baseObject = request.getObject();
		Metadata metadata = request.getMetadata();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		MetadataTO metadataTO = MapperUtil.extractMetadata( metadata );
		
		serviceResponse = documentoIntegrator.getAllMetadataByMetadata( tramiteTO, actorTO, baseTO, metadataTO );
		
		for(MetadataResponseTO tempTO : serviceResponse){
			response = new AllMetadataByMetadataResponse();
			if(tempTO.isExitoso()){
				MetadataTO metaTO = tempTO.getMetadata();
				Metadata meta = MapperUtil.writeMetadata( metaTO );
				response.getMetadata().add( meta );				
			}else{
				response.setExcepcionCausa(tempTO.getExcepcionCausa());
				response.setExcepcionCodigo(tempTO.getExcepcionCodigo());
				response.setExcepcionMensaje(tempTO.getExcepcionMensaje());
			}
		}
		return response;
	}

	@Override
	public AddDocumentActorResponse addDocumentActor(
			AddDocumentActorRequest request) {
		AddDocumentActorResponse response = null;
		BaseResponseTO to = null;
		BaseResponse base = null;
		
		Tramite tramite = request.getTramite();
		Actor actor = request.getActor();
		Actor newActor = request.getNewActor();
		BaseObject baseObject = request.getObject();
		
		TramiteTO tramiteTO = MapperUtil.extractTramite( tramite ); 
		ActorTO actorTO = MapperUtil.extractActor( actor ); 
		ActorTO newActorTO = MapperUtil.extractActor( newActor ); 
		BaseObjectTO baseTO = MapperUtil.extractBaseObject( baseObject );
		
		to = documentoIntegrator.addDocumentActor(tramiteTO, actorTO, baseTO, newActorTO);
		base =  MapperUtil.writeBaseResponse( to );
		response = new AddDocumentActorResponse();
		response.setResponse( base );
		return response;
	} 
}
