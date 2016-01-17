package mx.gob.imss.cit.bp.TestDocument.CreateDocument;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.impl.DocumentoService;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Error_Properties_Document {
	
private static final Logger LOG = LoggerFactory.getLogger(Error_Document_No_Found.class);
	
	private DocumentoService iDocumentService;
	private TramiteTO tramiteTO;
	private ActorTO actorTO;
	private  DocumentTO documentTO;
	
	
	@Before
	public void init(){
		iDocumentService = new DocumentoService();
		tramiteTO = new TramiteTO();
		actorTO = new ActorTO();
		documentTO = new DocumentTO();
	}
	
	
	@Test
	public void test_createDocument() {
		
		List<String> actores = new ArrayList<String>();
		actores.add("CAME100990");
	
		tramiteTO.setActores(actores);
		tramiteTO.setFecha("10/02/1945");
		tramiteTO.setFolio("123456");
		tramiteTO.setOrg("imss");
		tramiteTO.setTramite("solicitud");
		tramiteTO.setZona("zona");
		
		actorTO.setId("MIRM800829");
		actorTO.setIsOwner("true");
		actorTO.setOrg("imss");
		actorTO.setRol("medico");
		actorTO.setTipoId("ife");
		actorTO.setZona("zona");
		
		
		
		String content = "Este es mi documento";
		documentTO.setBorrado(false);
		documentTO.setContent(content);
		documentTO.setCustomId("customId");
		documentTO.setExt("txt");
		documentTO.setFolder("false");
		documentTO.setId("id");
		documentTO.setMimeType("mimeType");
		documentTO.setName("name");
		documentTO.setParentId("parentId");
		documentTO.setPath("path");
	
   try {			
		iDocumentService.createDocument(tramiteTO, actorTO, documentTO);
	} catch (BovedaPersonalServicesException bps) {
		LOG.error("**** BovedaPersonalServicesException ******");
		LOG.error(bps.getMessage());
		
		//No fue posible Consultar Info Documento:.		
		 Assert.assertSame(bps.getCode(), BovedaPersonalServicesCodeExceptionEnum.ERROR_PROPERTIES_DOCUMENT.getId());
	} catch(Exception ex){
		LOG.error("**** Exception ******");
		LOG.error("Error: ", ex.getMessage());
	}	
}


}
