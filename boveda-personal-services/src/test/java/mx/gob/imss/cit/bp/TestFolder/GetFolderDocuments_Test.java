package mx.gob.imss.cit.bp.TestFolder;

import java.util.ArrayList;
import java.util.List;

import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.impl.CarpetaService;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetFolderDocuments_Test {
	
	private CarpetaService iCarpetaService;
	private static final Logger LOG = LoggerFactory.getLogger(GetFolderDocuments_Test.class);
	
	private TramiteTO tramiteTO;
	private ActorTO actorTO;
	private BaseObjectTO baseTO ,baseTOChildren;
	
	@Before
	public void init(){
		tramiteTO = new TramiteTO();
		actorTO = new ActorTO();
		baseTO = new BaseObjectTO();
		baseTOChildren = new BaseObjectTO();
		iCarpetaService = new CarpetaService();
	}
	
	
	@Test
    public void test_getFolderDocuments(){
		
		List<String> actores = new ArrayList<String>();
		actores.add("CAME100990");
		actores.add("actor2");		
		tramiteTO.setActores(actores);
		tramiteTO.setFecha("23/01/2015");
		tramiteTO.setFolio("123456");
		tramiteTO.setOrg("imss");
		tramiteTO.setTramite("solicitud");
		tramiteTO.setZona("patronal");
		
		actorTO.setId("CAME100990");
		actorTO.setOrg("imss");
		actorTO.setRol("rol");
		actorTO.setTipoId("tipoId");
		actorTO.setZona("zona");
		actorTO.setIsOwner("false");
		
		baseTO.setBorrado(false);
		baseTO.setCustomId("customId");
		baseTO.setFolder("true");
		baseTO.setId("id");
		baseTO.setName("name.txt");
		baseTO.setParentId("parentId");
		baseTO.setPath("path");

		try {
			iCarpetaService.getFolderDocuments(tramiteTO, actorTO, baseTO);
		} catch (BovedaPersonalServicesException bps) {
			LOG.error(bps.getMessage(), bps);	
		    }catch(Exception ex){
				LOG.error(ex.getMessage(), ex);
			}
	}
	

}
