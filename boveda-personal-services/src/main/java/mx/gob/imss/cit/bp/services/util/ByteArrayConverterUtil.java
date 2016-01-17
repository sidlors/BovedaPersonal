package mx.gob.imss.cit.bp.services.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bouncycastle.util.encoders.Base64;


/**
 * Clase de utileria para el manejo de inputStream y byte[] 
 * @author Admin
 *
 */
public final class ByteArrayConverterUtil {

	/**
	 * 
	 */
	public static final  int ZERO = 0;
	
	private ByteArrayConverterUtil(){}
	
	/**
	 * Metodo que convierte de byte[] a InputStream
	 * @param is
	 * @return
	 * @throws IOException
	 */	
	public static String executeInputStreamToByteArray(InputStream is) throws IOException{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		int reads = is.read(); 
		byte[] stream = null;
		
		while(reads != -1){ 
			baos.write(reads); 
			reads = is.read(); 
		} 
		
		stream = baos.toByteArray();
		return new String(Base64.encode(stream));
	}
	
	/**
	 * Metodo que convierte de InputStream a byte[]
	 * @param byteArray 
	 * @return InputStream
	 */
	public static InputStream executeByteArrayToInputStream(String byteArray){
		byte[] restoredBytes = Base64.decode(byteArray);
		return new ByteArrayInputStream(restoredBytes);
	}
	
	

	/**
	 * Metodo que convierte de string a byte[]
	 * @param byteArray
	 * @return
	 */
	public static byte[]  executeByteArrayToByte(String byteArray){
		return Base64.decode(byteArray);
	}

}