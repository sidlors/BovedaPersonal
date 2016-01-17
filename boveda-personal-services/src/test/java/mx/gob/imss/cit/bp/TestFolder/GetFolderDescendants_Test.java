package mx.gob.imss.cit.bp.TestFolder;

import java.util.ArrayList;
import java.util.List;

import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.impl.CarpetaService;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetFolderDescendants_Test {

	private CarpetaService iCarpetaService;
	private static final Logger LOG = LoggerFactory.getLogger(GetFolderDescendants_Test.class);
	
	private TramiteTO tramiteTO;
	private ActorTO actorTO;
	private DocumentTO documentTO;
	private BaseObjectTO baseTO;
	
	
	@Before
	public void init(){
		tramiteTO = new TramiteTO();
		actorTO = new ActorTO();
		baseTO = new BaseObjectTO();
		iCarpetaService = new CarpetaService();
	}	
	
	
	@Test
    public void test_getDescendantsByFolderId(){
		
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
		
		baseTO.setBorrado(false);
		baseTO.setCustomId("customId");
		baseTO.setFolder("true");
		baseTO.setId("id");
		baseTO.setName("name");
		baseTO.setParentId("parentId");
		baseTO.setPath("path");
		
							
		try {
			iCarpetaService.getFolderDescendants(tramiteTO, actorTO, baseTO);
		} catch (BovedaPersonalServicesException bps) {
			LOG.error("Error: ", bps);
        }catch(Exception ex){
			LOG.error(ex.getMessage(), ex);
		}
		
	}

}
