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

public class Error_Format_Date {
	
private static final Logger LOG = LoggerFactory.getLogger(Error_Format_Date.class);
	
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
		actores.add("CAME10091990");
		
		tramiteTO.setActores(actores);
		tramiteTO.setFecha("15/20/1999");
		tramiteTO.setFolio("12345");
		tramiteTO.setOrg("imss");
		tramiteTO.setTramite("solicitud");
		tramiteTO.setZona("zona");
		
		actorTO.setId("id");
		actorTO.setIsOwner("true");
		actorTO.setOrg("org");
		actorTO.setRol("rol");
		actorTO.setTipoId("tipoId");
		actorTO.setZona("zona");
		
		
		String content = "Este es mi documento";
		documentTO.setBorrado(false);
		documentTO.setContent(content);
		documentTO.setCustomId("customId");
		documentTO.setExt("pdf");
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
		
		//Valida que el error sea el formato de fecha		
		 Assert.assertSame(bps.getCode(), BovedaPersonalServicesCodeExceptionEnum.ERROR_FORMAT_DATE.getId());
	} catch(Exception ex){
		LOG.error("**** Exception ******");
		LOG.error("Error: ", ex.getMessage());
	}	
}

}
