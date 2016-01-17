import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.util.ValidationHelper;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationHelperTestFolioVacio {
	
private static final Logger LOG = LoggerFactory.getLogger(ValidationHelperTestFolioVacio.class);
	
	private ValidationHelper validationHelper;
	private TramiteTO tramiteTO = null;
	
	@Before
	public void init(){
		tramiteTO = new TramiteTO();
		tramiteTO.setOrg("imss");
		tramiteTO.setZona("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
		tramiteTO.setTramite("solicitud");
		tramiteTO.setFecha("10/02/1945");
		tramiteTO.setFolio("");

		List<String> actores = new ArrayList<String>();
		actores.add("CAME100990");
		tramiteTO.setActores(actores);
	}
	
	
	@Test
	public void testZona() {
		try {			
			ValidationHelper.validarTramite1( tramiteTO );
	} catch (BovedaPersonalServicesException bps) {
		LOG.error("**** BovedaPersonalServicesException ******");
		LOG.error(bps.getMessage());	
		 Assert.assertSame( 101, bps.getCode());
	} catch(Exception ex){
		LOG.error("**** Exception ******");
		LOG.error("Error: ", ex.getMessage());
	}	
}


}
