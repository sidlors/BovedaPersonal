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

public class AddFolderActor_Test {

	private static final Logger LOG = LoggerFactory.getLogger(AddFolderActor_Test.class);
	private CarpetaService iCarpetaService;
	private ActorTO actorTO, newActorTO;
	private TramiteTO tramiteTO;
	private BaseObjectTO baseTO , baseTOChildren;
	
	@Before
	public void init(){
		iCarpetaService = new CarpetaService();
		actorTO = new ActorTO();
		newActorTO = new ActorTO();
		tramiteTO = new TramiteTO();
		baseTO = new BaseObjectTO();
		baseTOChildren = new BaseObjectTO();
	}
	
  @Test
  public void test_addfolderActor(){


		List<String> actores = new ArrayList<String>();
		actores.add("CAME100990");
		actores.add("MIRM800829");		
		
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
		
		newActorTO.setId("CANS880702");
		newActorTO.setOrg("imss");
		newActorTO.setRol("rol");
		newActorTO.setTipoId("tipoId");
		newActorTO.setZona("zona");
		newActorTO.setIsOwner("false");
		
		baseTO.setBorrado(false);
		baseTO.setCustomId("customId");
		baseTO.setFolder("true");
		baseTO.setId("id");
		baseTO.setName("name.txt");
		baseTO.setParentId("parentId");
		baseTO.setPath("path");
		
		
		try {
			iCarpetaService.addFolderActor(tramiteTO, baseTO, actorTO, newActorTO);
			
		} catch (BovedaPersonalServicesException bps) {
			LOG.error(bps.getMessage(), bps);
		} catch(Exception ex){
			LOG.error(ex.getMessage(), ex);
		}	
	}
}
