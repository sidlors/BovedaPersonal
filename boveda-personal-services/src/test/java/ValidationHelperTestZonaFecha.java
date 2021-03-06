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

public class ValidationHelperTestZonaFecha {
	
private static final Logger LOG = LoggerFactory.getLogger(ValidationHelperTestZonaFecha.class);
	
	private ValidationHelper validationHelper;
	private TramiteTO tramiteTO = null;
	
	@Before
	public void init(){
		tramiteTO = new TramiteTO();
		tramiteTO.setOrg("imss");
		tramiteTO.setZona("zam");
		tramiteTO.setTramite("solicitud");
		tramiteTO.setFecha("2015-01-27T16:17:57+00:00");
		tramiteTO.setFolio("432156");

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
		 Assert.assertSame( 102, bps.getCode());
	} catch(Exception ex){
		LOG.error("**** Exception ******");
		LOG.error("Error: ", ex.getMessage());
	}	
}


}
