package mx.gob.imss.cit.bp.ws.test.createDocument;


import static org.junit.Assert.*;
import junit.framework.Assert;
import mx.gob.imss.cit.bp.ws.CreateDocumentRequest;
import mx.gob.imss.cit.bp.ws.CreateDocumentResponse;
import mx.gob.imss.cit.bp.ws.DocumentRequest;
import mx.gob.imss.cit.bp.ws.DocumentResponse;
import mx.gob.imss.cit.bp.ws.DocumentoWSServiceImpl;
import mx.gob.imss.cit.bp.ws.DocumentoWSServiceImplService;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Actor;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Document;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Tramite;

import org.bouncycastle.util.encoders.Base64;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDocumentTest {
	
private static final Logger LOG = LoggerFactory.getLogger(CreateDocumentTest.class);
	
	private final DocumentoWSServiceImplService service = new DocumentoWSServiceImplService();

	private DocumentoWSServiceImpl port  = service.getDocumentoWSServiceImplPort();
	
	private final String content = "Este es mi documento";

	private final Base64 encoder = new Base64(); 

	private final CreateDocumentRequest request = new CreateDocumentRequest();

	private final DocumentRequest docRequest = new DocumentRequest();
	
	private Tramite tramite = null;
	
	private Document document = null;

	private Actor actor = null;
	
	@Before
	public void init(){
	
		tramite = new Tramite();
		tramite.setOrg("imss");
		tramite.setTramite("solicitud");
		tramite.setZona("zona");
		tramite.setFolio("123456");
		tramite.setFecha("12/04/1980");
		request.setTramite( tramite );
		
		document = new Document();
		document.setContent( new String( Base64.encode( content.getBytes() ) ) );
		document.setExt("txt");
		document.setName("prueba.txt");
		request.setDocument( document );
		
		actor = new Actor();
		actor.setId("id");
		actor.setIsOwner("false");
		actor.setOrg("org");
		actor.setRol("rol");
		actor.setTipoId("tipoId");
		actor.setZona("zona");	
		
		request.setActor( actor );
		
		docRequest.setTramite( tramite );
		docRequest.setObject( document );
		docRequest.setActor( actor );
	}
	
	@Test
	public void createDocumentTest() {
		CreateDocumentResponse createResponse = null;
		DocumentResponse getResponse = null;
		try {			
			createResponse = port.createDocument( request );
			getResponse = port.getDocument( docRequest );
			if( !createResponse.getResponse().isExitoso() ){
				LOG.error("Error: ", createResponse.getResponse().getExcepcionMensaje() );
			}
		} catch(Exception ex){
			LOG.error("Exception: ", ex.getMessage());
		}	  
		Assert.assertEquals( new String( Base64.encode( content.getBytes() ) ) , getResponse.getDocument().getContent() );
	}
	
	@Test
	public void createDocumenIsOwnertTest() throws Exception {
		
		
		LOG.info("Inicia Prueba");
		
		
		actor.setIsOwner("true");
		
		CreateDocumentResponse createResponse = null;
		DocumentResponse getResponse = null;
		try {			
			createResponse = port.createDocument( request );
			getResponse = port.getDocument( docRequest );
			if( !createResponse.getResponse().isExitoso() ){
				LOG.info("Error: "+ createResponse.getResponse().getExcepcionMensaje() );
			}
		} catch(Exception ex){
			LOG.error("Exception: "+ ex.getMessage());
		}	  
		Assert.assertEquals( new String( Base64.encode( content.getBytes() ) ) , getResponse.getDocument().getContent() );
		
	}

}
