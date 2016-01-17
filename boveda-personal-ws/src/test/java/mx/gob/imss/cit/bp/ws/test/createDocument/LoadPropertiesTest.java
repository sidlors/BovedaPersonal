package mx.gob.imss.cit.bp.ws.test.createDocument;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoadPropertiesTest {
	
	
	private static final Logger logger = Logger
			.getLogger(LoadPropertiesTest.class);



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Properties prop = new Properties();
		String ip = "";
		String port="";
		String context="";
	 
		try {
	 
		  InputStream inputStream = 
				  LoadPropertiesTest.class.getClassLoader().getResourceAsStream("enviroment.properties");
	 
		  prop.load(inputStream);
		  ip = prop.getProperty("deploy.ip");
		  port = prop.getProperty("deploy.port");
		  context = prop.getProperty("deploy.context");
		  
		  logger.info(ip);
		  logger.info(port);
		  logger.info(context);
	 
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	 
		
	}

	@Test
	public void test() {
		boolean actual=true;
		assertEquals(true, actual);
	}

}
